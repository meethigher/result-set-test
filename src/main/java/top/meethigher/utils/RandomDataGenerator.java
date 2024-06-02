package top.meethigher.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 模拟数据生成器
 *
 * @author chenchuancheng
 * @since 2024/06/02 16:48
 */
@Slf4j
@RequiredArgsConstructor
public class RandomDataGenerator {

    private final JdbcTemplate jdbcTemplate;

    private final int batchInsert;

    private final String[] fieldArray;

    private final String tableName;

    public void generateData(long num) {
        StringBuilder insertBuilder = new StringBuilder("insert into ")
                .append(tableName)
                .append(" (")
                .append(String.join(",", fieldArray))
                .append(") values (")
                .append(String.join(",", Collections.nCopies(fieldArray.length, "?")))
                .append(")");
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (long i = 0; i < num; i++) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (String key : fieldArray) {
                switch (key) {
                    case "name":
                        map.put(key, RandomName.create());
                        break;
                    case "money":
                        map.put(key, ThreadLocalRandom.current().nextDouble(0, Integer.MAX_VALUE));
                        break;
                    case "snapshot_time":
                        long l = ThreadLocalRandom.current().nextLong(1706561012236l, 1716561012236l);
                        map.put(key, TimeUtils.format(new Date(l), "yyyy-MM-dd HH:mm:ss"));
                        break;
                    default:
                }
            }
            mapList.add(map);
            if (mapList.size() >= batchInsert) {
                batchUpdate(insertBuilder, mapList);
                mapList.clear();
            }
        }
        if (!mapList.isEmpty()) {
            batchUpdate(insertBuilder, mapList);
            mapList.clear();
        }
    }

    private void batchUpdate(StringBuilder insertBuilder, List<Map<String, Object>> mapList) {
        jdbcTemplate.batchUpdate(insertBuilder.toString(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Map<String, Object> tMap = mapList.get(i);
                ArrayList<String> list = new ArrayList<>(tMap.keySet());
                for (int i1 = 0; i1 < list.size(); i1++) {
                    Object o = tMap.get(list.get(i1));
                    ps.setObject(i1 + 1, o);
                }
            }

            @Override
            public int getBatchSize() {
                return mapList.size();
            }
        });
        log.info("batch update size is {}", mapList.size());
    }

}
