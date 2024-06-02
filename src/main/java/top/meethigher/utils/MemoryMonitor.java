package top.meethigher.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 内存监控器
 *
 * @author chenchuancheng
 * @since 2024/05/27 06:49
 */
public class MemoryMonitor {

    private long totalMemory;//总共可用内存，与-Xms参数有关
    private long freeMemory;//实际未用内存
    private long usedMemory;//实际已用内存
    private long maxMemory;//限制最大内存，与-Xmx参数有关
    private final long period;

    public MemoryMonitor(long period) {
        this.period = period;
    }

    public MemoryMonitor() {
        this.period = 500L;
    }

    public String convertBytes(long bytes) {
        double size = bytes;
        String unit = "B";

        if (size >= 1024) {
            size /= 1024;
            unit = "KB";
            if (size >= 1024) {
                size /= 1024;
                unit = "MB";
                if (size >= 1024) {
                    size /= 1024;
                    unit = "GB";
                    if (size >= 1024) {
                        size /= 1024;
                        unit = "TB";
                        if (size >= 1024) {
                            size /= 1024;
                            unit = "PB";
                        }
                    }
                }
            }
        }

        return String.format("%.2f %s", size, unit);
    }

    public void start() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Runtime runtime = Runtime.getRuntime();
                totalMemory = runtime.totalMemory(); // 总共可用内存,与-Xms参数有关
                freeMemory = runtime.freeMemory(); // 实际未用内存
                usedMemory = totalMemory - freeMemory; // 已用内存
                maxMemory = runtime.maxMemory(); // 最大内存,与-Xmx参数有关
            }
        }, 0L, period);
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public long getPeriod() {
        return period;
    }
}