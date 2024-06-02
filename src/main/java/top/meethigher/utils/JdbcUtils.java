package top.meethigher.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * jdbc工具类
 *
 * @author chenchuancheng
 * @since 2024/06/02 17:30
 */
@Slf4j
public class JdbcUtils {


    private final String ddlTemplate = "CREATE TABLE {tableName} ( " +
            " \"name\" varchar(255) NULL, " +
            " \"money\" float8 NULL, " +
            " snapshot_time varchar NULL " +
            "); " +
            "CREATE INDEX {tableName}_snapshot_time_idx ON {tableName} USING btree (snapshot_time);";


    private final String[] fieldArray = new String[]{
            "name",
            "money",
            "snapshot_time"
    };

    private final String tableName = "query";

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private final RandomDataGenerator randomDataGenerator;

    private final int num = 30000_0000;

    public JdbcUtils() {
        this("jdbc:postgresql://10.0.0.9/resultset","postgres","postgres");
    }

    public JdbcUtils(String jdbcUrl, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(30);
        this.dataSource = new HikariDataSource(config);
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.randomDataGenerator = new RandomDataGenerator(jdbcTemplate, 5000, fieldArray, tableName);
        preflight();
    }

    private void preflight() {
        StringBuilder tableExistBuilder = new StringBuilder("select count(*) from pg_tables where schemaname='public' and tablename='")
                .append(tableName).append("'");
        Long aLong = getJdbcTemplate().queryForObject(tableExistBuilder.toString(), Long.class);
        if (aLong == null || aLong == 0) {
            getJdbcTemplate().update(getDdlTemplate().replace("{tableName}", tableName));
            randomDataGenerator.generateData(num);
            log.info("create table {}, and generate {} random data", tableName, num);
        } else {
            log.info("table {} already exist", tableName);
        }
    }

    public String getDdlTemplate() {
        return ddlTemplate;
    }

    public String[] getFieldArray() {
        return fieldArray;
    }

    public String getTableName() {
        return tableName;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public RandomDataGenerator getRandomDataGenerator() {
        return randomDataGenerator;
    }
}
