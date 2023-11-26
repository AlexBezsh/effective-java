package com.alexbezsh.effectivejava.cache.common;

import java.time.Duration;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheUtils {

    public static final int DEFAULT_CACHE_SIZE = 100_000;
    public static final Duration DEFAULT_RETENTION_PERIOD = Duration.ofSeconds(5);

    public static void validateMaxSize(int maxSize) {
        if (maxSize < 0) {
            throw new RuntimeException("Max cache size can't be negative");
        }
    }

    public static void validateRetentionPeriod(Duration retentionPeriod) {
        if (retentionPeriod != null && retentionPeriod.toNanos() <= 0) {
            throw new RuntimeException("Retention period can't be negative");
        }
    }

}
