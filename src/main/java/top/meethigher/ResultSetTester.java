package top.meethigher;

import top.meethigher.simple.startup.log.SimpleApplication;
import top.meethigher.utils.JdbcUtils;
import top.meethigher.utils.MemoryMonitor;
import top.meethigher.utils.ResultSetQueryPerformer;

/**
 * 测试启动入口
 *
 * @author chenchuancheng
 * @since 2024/06/02 16:42
 */
public class ResultSetTester extends SimpleApplication {

    private final String startTime = "2024-05-20 00:00:00";

    private final String endTime = "2024-05-20 23:59:59";

    @Override
    public void run() throws Exception {
        MemoryMonitor memoryMonitor = new MemoryMonitor(500);
        memoryMonitor.start();
        JdbcUtils jdbcUtils = new JdbcUtils("jdbc:postgresql://10.0.0.9/resultset", "postgres", "postgres");
        ResultSetQueryPerformer performer = new ResultSetQueryPerformer(jdbcUtils, memoryMonitor);
        performer.startPlan1(startTime, endTime);
        performer.startPlan2(startTime, endTime);
    }

    public static void main(String[] args) throws Exception {
        runApp(ResultSetTester.class, args);
    }
}
