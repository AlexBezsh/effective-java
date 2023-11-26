package com.alexbezsh.effectivejava.cache.guava;

import com.alexbezsh.effectivejava.cache.AbstractCacheTest;
import com.alexbezsh.effectivejava.cache.common.CachedObject;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GuavaCacheTest extends AbstractCacheTest {

    @BeforeEach
    void setUp() {
        testedInstance = GuavaCache.<String, CachedObject>builder()
            .removalListener(i -> {})
            .build();
    }

    @Test
    void cacheConstructorShouldThrowExceptionIfMaxSizeInvalid() {
        assertThrows(RuntimeException.class,
            () -> GuavaCache.<String, CachedObject>builder()
                .maxSize(-1)
                .build());
    }

    @Test
    void cacheConstructorShouldThrowExceptionIfRetentionPeriodInvalid() {
        assertThrows(RuntimeException.class,
            () -> GuavaCache.<String, CachedObject>builder()
                .retentionPeriod(Duration.ofSeconds(-1))
                .build());
    }

}