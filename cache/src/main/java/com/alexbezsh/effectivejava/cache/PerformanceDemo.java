package com.alexbezsh.effectivejava.cache;

import com.alexbezsh.effectivejava.cache.common.Cache;
import com.alexbezsh.effectivejava.cache.common.CachedObject;
import com.alexbezsh.effectivejava.cache.custom.CustomCache;
import com.alexbezsh.effectivejava.cache.guava.GuavaCache;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static java.lang.System.currentTimeMillis;

public class PerformanceDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Guava cache");
        cacheDemo(GuavaCache.<String, CachedObject>builder().removalListener(i -> {
        }).build());
        System.out.println();

        System.out.println("Custom cache");
        cacheDemo(CustomCache.<String, CachedObject>builder().removalListener(i -> {
        }).build());
    }

    private static void cacheDemo(Cache<String, CachedObject> cache) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long start = currentTimeMillis();
        for (int a = 0; a < 10; a++) {
            executor.submit(() -> {
                for (int i = 0; i < 100_100; i++) {
                    String key = String.valueOf(i);
                    cache.get(key);
                    cache.put(key, new CachedObject(key));
                }
            });
        }
        executor.shutdown();
        if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Time consumed: " + (currentTimeMillis() - start));
            System.out.println("Evictions count: " + cache.getEvictionsCount());
            System.out.printf("Average insertion time: %.2f ns%n", cache.getAverageInsertionTime());
        } else {
            System.out.println("Timeout elapsed");
        }
    }

}
