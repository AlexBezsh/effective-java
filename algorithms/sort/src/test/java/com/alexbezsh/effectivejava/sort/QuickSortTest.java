package com.alexbezsh.effectivejava.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static com.alexbezsh.effectivejava.sort.QuickSort.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest extends SortUtilTest {

    @ParameterizedTest
    @MethodSource("sortArgs")
    void sortTest(int[] array, int[] expected) {
        sort(array);

        assertArrayEquals(expected, array);
    }

}
