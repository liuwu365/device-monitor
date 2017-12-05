package com.device.util;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.utils.SystemTimer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: DDos攻击
 * @User: liuwu_eva@163.com
 * @Date: 2017-06-02 下午 2:31
 */
@Data
@Accessors(chain = true)
public class RateLimiterWrap {
    private static Logger log = LoggerFactory.getLogger(RateLimiterWrap.class);

    /**
     * 频率控制
     */
    private RateLimiter rateLimiter = null;//RateLimiter.create(3);

    /**
     * 本阶段已经收到多少次警告
     */
    private AtomicInteger warnCount = new AtomicInteger();

    /**
     * 总共已经收到多少次警告
     */
    private AtomicInteger allWarnCount = new AtomicInteger();

    /**
     * 本阶段最多警告多次数
     */
    private int maxWarnCount = 20;

    /**
     * 一共最多警告多次数
     */
    private int maxAllWarnCount = maxWarnCount * 10;

    /**
     * 上一次警告时间
     */
    private long lastWarnTime = SystemTimer.currentTimeMillis();

    /**
     * 警告清零时间间隔，即如果有这么长时间没有收到警告，则把前面的警告次数清零
     */
    private int warnClearInterval = 1000 * 60 * 60 * 2;

    /**
     * @param permitsPerSecond  QPS
     * @param warnClearInterval 清理本阶段警告的时间间隔，参考值1000 * 60 * 60 * 2，单位为ms
     * @param maxWarnCount      本阶段最多警告多次数，参考值10
     * @param maxAllWarnCount   一共最多警告多次数
     */
    public RateLimiterWrap(int permitsPerSecond, int warnClearInterval, int maxWarnCount, int maxAllWarnCount) {
        this.rateLimiter = RateLimiter.create(permitsPerSecond);
        this.warnClearInterval = warnClearInterval;
        this.maxWarnCount = maxWarnCount;
        this.maxAllWarnCount = maxAllWarnCount;
    }

    /**
     * @return 0位置：根据QPS获取执行锁, false: 没拿到锁<br>
     * 1位置：根据警告次数获取执行锁, false: 没拿到锁<br>
     */
    public boolean[] tryAcquire() {
        boolean ret = rateLimiter.tryAcquire();
        if (!ret) {
            synchronized (this) {
                long nowTime = SystemTimer.currentTimeMillis();
                if ((nowTime - lastWarnTime) > warnClearInterval) {
                    warnCount.set(0);
                }
                lastWarnTime = SystemTimer.currentTimeMillis();
                int wc = warnCount.incrementAndGet();
                int awc = allWarnCount.incrementAndGet();

                if (wc > maxWarnCount || awc > maxAllWarnCount) {
                    return new boolean[]{false, false};
                }
                return new boolean[]{false, true};
            }
        } else {
            return new boolean[]{true, true};
        }

    }


}
