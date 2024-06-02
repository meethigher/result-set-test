package top.meethigher.utils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ResultSet查询
 *
 * @author chenchuancheng
 * @since 2024/06/02 16:43
 */
@Slf4j
@RequiredArgsConstructor
public class ResultSetQueryPerformer {

    private final JdbcUtils jdbcUtils;

    private final MemoryMonitor memoryMonitor;

    public void startPlan1(String startTime, String endTime) {
        Thread thread = new Thread(() -> {
            plan1(startTime, endTime);
        });
        thread.setDaemon(false);
        thread.setName("navicat");
        thread.start();
    }

    public void startPlan2(String startTime, String endTime) {
        Thread thread = new Thread(() -> {
            plan2(startTime, endTime);
        });
        thread.setDaemon(false);
        thread.setName("dbeaver");
        thread.start();
    }

    /**
     * 方案一：
     * 使用select * from table where order by 进行查询，但是使用默认方式
     */
    private void plan1(String startTime, String endTime) {
        StringBuilder queryBuilder = new StringBuilder("select * from ")
                .append(jdbcUtils.getTableName())
                .append(" where ")
                .append(jdbcUtils.getFieldArray()[2]).append(" >= ? and ").append(jdbcUtils.getFieldArray()[2])
                .append(" <= ? order by ").append(jdbcUtils.getFieldArray()[2]).append(" asc");
        long start = System.currentTimeMillis();
        long startUsedMemory = memoryMonitor.getUsedMemory();
        try (Connection connection = jdbcUtils.getJdbcTemplate().getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(queryBuilder.toString());
            ps.setObject(1, startTime);
            ps.setObject(2, endTime);
            ResultSet rs = ps.executeQuery();
            log.info("plan1 consumed {}, {}", TimeUtils.humanizedFormat(System.currentTimeMillis(), start),
                    memoryMonitor.convertBytes(memoryMonitor.getUsedMemory() - startUsedMemory));
        } catch (Exception ignore) {
        }
    }

    /**
     * 方案二：
     * 使用select * from table where order by 进行查询，但是使用参数调优
     */
    private void plan2(String startTime, String endTime) {
        StringBuilder queryBuilder = new StringBuilder("select * from ")
                .append(jdbcUtils.getTableName())
                .append(" where ")
                .append(jdbcUtils.getFieldArray()[2]).append(" >= ? and ").append(jdbcUtils.getFieldArray()[2])
                .append(" <= ? order by ").append(jdbcUtils.getFieldArray()[2]).append(" asc");
        long start = System.currentTimeMillis();
        long startUsedMemory = memoryMonitor.getUsedMemory();
        try (Connection connection = jdbcUtils.getJdbcTemplate().getDataSource().getConnection()) {
            //对于postgresql，只有关闭事务，setFetchSize才会生效
            connection.setAutoCommit(false);
            //对于postgresql，后面的两个参数其实也就是默认值时使用的
            PreparedStatement ps = connection.prepareStatement(queryBuilder.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ps.setFetchSize(1000);
            ps.setFetchDirection(ResultSet.FETCH_FORWARD);
            ps.setObject(1, startTime);
            ps.setObject(2, endTime);
            ResultSet rs = ps.executeQuery();
            log.info("plan2 consumed {}, {}", TimeUtils.humanizedFormat(System.currentTimeMillis(), start),
                    memoryMonitor.convertBytes(memoryMonitor.getUsedMemory() - startUsedMemory));
        } catch (Exception ignore) {
        }
    }


}
