package com.alexbezsh.effectivejava.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_CACHE_SIZE;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_RETENTION_PERIOD;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.validateMaxSize;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.validateRetentionPeriod;
import static java.lang.System.nanoTime;

@Slf4j
public class GuavaCache<K, V> implements com.alexbezsh.effectivejava.cache.common.Cache<K, V> {

    private static final RemovalListener<Object, Object> DEFAULT_REMOVAL_LISTENER =
        n -> log.info("Entry removed. Key: {}. Value: {}. Reason: {}.",
            n.getKey(), n.getValue(), n.getCause());

    private final Cache<K, V> cache;

    private final AtomicLong insertionsCounter = new AtomicLong(0L);
    private final AtomicLong totalInsertionTime = new AtomicLong(0L);

    @Builder
    @SuppressWarnings("AvoidInlineConditionals")
    public GuavaCache(int maxSize, Duration retentionPeriod,
                      RemovalListener<K, V> removalListener) {
        validateMaxSize(maxSize);
        validateRetentionPeriod(retentionPeriod);

        cache = CacheBuilder.newBuilder()
            .maximumSize(maxSize == 0 ? DEFAULT_CACHE_SIZE : maxSize)
            .expireAfterAccess(Optional.ofNullable(retentionPeriod)
                .orElse(DEFAULT_RETENTION_PERIOD))
            .removalListener(
                Optional.<RemovalListener<? super K, ? super V>>ofNullable(removalListener)
                    .orElse(DEFAULT_REMOVAL_LISTENER))
            .recordStats()
            .build();
    }

    @Override
    public V get(K key) {
        return Optional.ofNullable(key)
            .map(cache::getIfPresent)
            .orElse(null);
    }

    @Override
    public void put(K key, V value) {
        if (key != null && value != null) {
            long start = nanoTime();
            cache.put(key, value);
            totalInsertionTime.addAndGet(nanoTime() - start);
            insertionsCounter.incrementAndGet();
        }
    }

    @Override
    public double getAverageInsertionTime() {
        return (double) totalInsertionTime.get() / insertionsCounter.get();
    }

    @Override
    public long getEvictionsCount() {
        return cache.stats().evictionCount();
    }

}
