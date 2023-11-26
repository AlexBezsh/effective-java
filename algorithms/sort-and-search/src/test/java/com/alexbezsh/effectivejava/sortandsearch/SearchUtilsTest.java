package com.alexbezsh.effectivejava.sortandsearch;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.bubbleSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.defaultSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.insertionSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.mergeSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.quickSortAndBinarySearch;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchUtilsTest {

    @ParameterizedTest
    @MethodSource("searchArgs")
    void mergeSortAndBinarySearchTest(int[] array, int value, int expected) {
        int actual = mergeSortAndBinarySearch(array, value);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("searchArgs")
    void insertionSortAndBinarySearchTest(int[] array, int value, int expected) {
        int actual = insertionSortAndBinarySearch(array, value);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("searchArgs")
    void bubbleSortAndBinarySearchTest(int[] array, int value, int expected) {
        int actual = bubbleSortAndBinarySearch(array, value);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("searchArgs")
    void quickSortAndBinarySearchTest(int[] array, int value, int expected) {
        int actual = quickSortAndBinarySearch(array, value);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("searchArgs")
    void defaultSortAndBinarySearchTest(int[] array, int value, int expected) {
        int actual = defaultSortAndBinarySearch(array, value);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> searchArgs() {
        return Stream.of(
            Arguments.of(new int[] {}, 0, -1),
            Arguments.of(new int[] {1}, 1, 0),
            Arguments.of(new int[] {1, 2, 3}, 2, 1),
            Arguments.of(new int[] {3, 1, 2}, 2, 1),
            Arguments.of(new int[] {1, 2, 3, 4, 5, 6, -1}, 1, 1),
            Arguments.of(new int[] {1, 2, 3, 4, 5, 6, -1}, -2, -1)
        );
    }

}