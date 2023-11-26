package com.alexbezsh.effectivejava.cache.custom;

import com.alexbezsh.effectivejava.cache.common.Cache;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_CACHE_SIZE;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_RETENTION_PERIOD;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.validateMaxSize;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.validateRetentionPeriod;
import static com.alexbezsh.effectivejava.cache.custom.RemovalReason.EXPIRED;
import static com.alexbezsh.effectivejava.cache.custom.RemovalReason.SIZE;
import static java.lang.System.nanoTime;

@Slf4j
public class CustomCache<K, V> implements Cache<K, V> {

    private static final RemovalListener<Object, Object> DEFAULT_REMOVAL_LISTENER =
        i -> log.info("Entry removed. Key: {}. Value: {}. Reason: {}.",
            i.getKey(), i.getValue(), i.getReason());

    private final int maxSize;
    private final long retentionPeriod;
    private final Map<K, CachedValue> cache;
    private final RemovalListener<? super K, ? super V> removalListener;

    private final AtomicLong cacheSize = new AtomicLong(0L);
    private final ReentrantLock cleanUpLock = new ReentrantLock();
    private final AtomicLong evictionsCounter = new AtomicLong(0L);
    private final AtomicLong insertionsCounter = new AtomicLong(0L);
    private final AtomicLong totalInsertionTime = new AtomicLong(0L);

    @Builder
    @SuppressWarnings("AvoidInlineConditionals")
    public CustomCache(int maxSize, Duration retentionPeriod,
                       RemovalListener<K, V> removalListener) {
        validateMaxSize(maxSize);
        validateRetentionPeriod(retentionPeriod);

        this.maxSize = maxSize == 0 ? DEFAULT_CACHE_SIZE : maxSize;
        this.retentionPeriod = Optional.ofNullable(retentionPeriod)
            .orElse(DEFAULT_RETENTION_PERIOD).toNanos();
        this.removalListener =
            Optional.<RemovalListener<? super K, ? super V>>ofNullable(removalListener)
                .orElse(DEFAULT_REMOVAL_LISTENER);
        this.cache = new ConcurrentHashMap<>(1 + (int) (this.maxSize / 0.75));
    }

    @Override
    public V get(K key) {
        return Optional.ofNullable(key)
            .map(cache::get)
            .map(v -> {
                if (isExpired(v)) {
                    evict(key, v, EXPIRED);
                    return null;
                }
                return v.readValue();
            })
            .orElse(null);
    }

    @Override
    public void put(K key, V value) {
        if (key != null && value != null) {
            long start = nanoTime();
            boolean inserted = false;
            cleanUpLock.lock();
            if (cacheSize.get() >= maxSize) {
                cleanUpCache();
                insert(key, value);
                inserted = true;
            }
            cleanUpLock.unlock();
            if (!inserted) {
                insert(key, value);
            }
            addInsertionStats(start);
        }
    }

    @Override
    public double getAverageInsertionTime() {
        return (double) totalInsertionTime.get() / insertionsCounter.get();
    }

    @Override
    public long getEvictionsCount() {
        return evictionsCounter.get();
    }

    private void insert(K key, V value) {
        if (cache.put(key, new CachedValue(value)) == null) {
            cacheSize.incrementAndGet();
        }
    }

    private void cleanUpCache() {
        CachedValue value;
        long readsCounter;
        long minReads = Long.MAX_VALUE;
        Map.Entry<K, CachedValue> leastAccessed = null;
        for (Map.Entry<K, CachedValue> entry : cache.entrySet()) {
            value = entry.getValue();
            if (isExpired(value)) {
                evict(entry.getKey(), value, EXPIRED);
            } else {
                readsCounter = value.getReadsCounter();
                if (minReads > readsCounter) {
                    minReads = readsCounter;
                    leastAccessed = entry;
                }
            }
        }
        if (leastAccessed != null) {
            evict(leastAccessed.getKey(), leastAccessed.getValue(), SIZE);
        }
    }

    private boolean isExpired(CachedValue value) {
        return value.getLastAccessedTime() + retentionPeriod <= nanoTime();
    }

    private void evict(K key, CachedValue value, RemovalReason reason) {
        if (cache.remove(key) != null) {
            cacheSize.decrementAndGet();
            evictionsCounter.incrementAndGet();
            removalListener.removed(new RemovalInfo<>(key, value.value, reason));
        }
    }

    private void addInsertionStats(long insertionStart) {
        totalInsertionTime.addAndGet(nanoTime() - insertionStart);
        insertionsCounter.incrementAndGet();
    }

    @AllArgsConstructor
    private class CachedValue {

        private final AtomicLong readsCounter = new AtomicLong(0L);
        private final AtomicLong lastAccessed = new AtomicLong(nanoTime());

        private final V value;

        public V readValue() {
            readsCounter.incrementAndGet();
            lastAccessed.set(nanoTime());
            return value;
        }

        public long getReadsCounter() {
            return readsCounter.get();
        }

        public long getLastAccessedTime() {
            return lastAccessed.get();
        }

    }

}
