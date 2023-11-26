package com.alexbezsh.effectivejava.cache.common;

import java.time.Duration;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.validateMaxSize;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.validateRetentionPeriod;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CacheUtilsTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 123, Integer.MAX_VALUE})
    void validateMaxSizeTest(int maxSize) {
        assertDoesNotThrow(() -> validateMaxSize(maxSize));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE})
    void validateMaxSizeShouldThrowException(int maxSize) {
        String expectedMessage = "Max cache size can't be negative";

        RuntimeException actual = assertThrows(RuntimeException.class,
            () -> validateMaxSize(maxSize));

        assertEquals(expectedMessage, actual.getMessage());
    }

    @ParameterizedTest
    @MethodSource("validRetentionPeriods")
    void validateRetentionPeriodTest(Duration retentionPeriod) {
        assertDoesNotThrow(() -> validateRetentionPeriod(retentionPeriod));
    }

    static Stream<Arguments> validRetentionPeriods() {
        return Stream.of(
            Arguments.of(Duration.ofNanos(1)),
            Arguments.of(Duration.ofMinutes(1)),
            Arguments.of(Duration.ofDays(1))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidRetentionPeriods")
    void validateRetentionPeriodShouldThrowException(Duration retentionPeriod) {
        String expectedMessage = "Retention period can't be negative";

        RuntimeException actual = assertThrows(RuntimeException.class,
            () -> validateRetentionPeriod(retentionPeriod));

        assertEquals(expectedMessage, actual.getMessage());
    }

    static Stream<Arguments> invalidRetentionPeriods() {
        return Stream.of(
            Arguments.of(Duration.ofNanos(-1)),
            Arguments.of(Duration.ofMinutes(-1)),
            Arguments.of(Duration.ofDays(-1))
        );
    }

}
