package com.alexbezsh.effectivejava.cache.custom;

import com.alexbezsh.effectivejava.cache.AbstractCacheTest;
import com.alexbezsh.effectivejava.cache.common.CachedObject;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_CACHE_SIZE;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_RETENTION_PERIOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomCacheTest extends AbstractCacheTest {

    @BeforeEach
    void setUp() {
        testedInstance = CustomCache.<String, CachedObject>builder()
            .removalListener(i -> {})
            .build();
    }

    @Test
    void cacheConstructorShouldThrowExceptionIfMaxSizeInvalid() {
        assertThrows(RuntimeException.class,
            () -> CustomCache.<String, CachedObject>builder()
                .maxSize(-1)
                .build());
    }

    @Test
    void cacheConstructorShouldThrowExceptionIfRetentionPeriodInvalid() {
        assertThrows(RuntimeException.class,
            () -> CustomCache.<String, CachedObject>builder()
                .retentionPeriod(Duration.ofSeconds(-1))
                .build());
    }

    @Test
    void shouldEvictExpiredRecords() throws InterruptedException {
        for (int i = 0; i < DEFAULT_CACHE_SIZE; i++) {
            String key = String.valueOf(i);
            testedInstance.put(key, new CachedObject(key));
        }
        assertEquals(0, testedInstance.getEvictionsCount());

        Thread.sleep(DEFAULT_RETENTION_PERIOD.toMillis());
        testedInstance.put("key", new CachedObject("value"));

        assertEquals(DEFAULT_CACHE_SIZE, testedInstance.getEvictionsCount());
    }

}
