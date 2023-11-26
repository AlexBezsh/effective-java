package com.alexbezsh.effectivejava.binarysearch;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @MethodSource("searchArgs")
    @ParameterizedTest(name = "[{index}] {3}")
    void iterativeSearchTest(int[] array, int value, int expected, String name) {
        assertEquals(expected, BinarySearch.iterative(array, value));
    }

    @MethodSource("searchArgs")
    @ParameterizedTest(name = "[{index}] {3}")
    void recursiveSearchTest(int[] array, int value, int expected, String name) {
        assertEquals(expected, BinarySearch.recursive(array, value));
    }

    static Stream<Arguments> searchArgs() {
        int bigArrayLength = 1_000_000;
        int[] bigArray = new int[bigArrayLength];
        for (int i = 0; i < bigArrayLength; i++) {
            bigArray[i] = i;
        }
        return Stream.of(
            Arguments.of(new int[] {}, 2, -1, "no elements, no hit"),
            Arguments.of(new int[] {1}, 2, -1, "one element, no hit"),
            Arguments.of(new int[] {1}, 1, 0, "one element, hit"),
            Arguments.of(new int[] {1, 2}, 3, -1, "two elements, no hit"),
            Arguments.of(new int[] {1, 2}, 2, 1, "two elements, hit"),
            Arguments.of(new int[] {1, 2, 3}, 4, -1, "three elements, no hit"),
            Arguments.of(new int[] {1, 2, 3}, 1, 0, "three elements, hit"),
            Arguments.of(new int[] {1, 2, 3}, 2, 1, "three elements, hit"),
            Arguments.of(new int[] {1, 2, 3}, 3, 2, "three elements, hit"),
            Arguments.of(bigArray, bigArrayLength, -1, "big array, no hit"),
            Arguments.of(bigArray, 1, 1, "big array, hit"),
            Arguments.of(bigArray, 123_456, 123_456, "big array, hit"),
            Arguments.of(bigArray, 999_999, 999_999, "big array, hit")
        );
    }

}
