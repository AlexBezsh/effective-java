package com.alexbezsh.effectivejava.sortandsearch;

import java.util.Random;
import java.util.function.Function;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.bubbleSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.defaultSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.insertionSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.mergeSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.quickSortAndBinarySearch;
import static java.lang.System.arraycopy;
import static java.lang.System.currentTimeMillis;

public class PerformanceDemo {

    private static final int TEST_VALUE = 12;

    public static void main(String[] args) {
        int arrayLength = 100_000;
        int[] array = initArray(arrayLength);
        array[new Random().nextInt(arrayLength)] = TEST_VALUE;
        System.out.printf("Array length: %s%n%n", arrayLength);

        System.out.println("Merge sort + binary search");
        int[] arr1 = getCopy(array);
        performanceDemo(i -> mergeSortAndBinarySearch(arr1, i));
        System.out.println();

        System.out.println("Insertion sort + binary search");
        int[] arr2 = getCopy(array);
        performanceDemo(i -> insertionSortAndBinarySearch(arr2, i));
        System.out.println();

        System.out.println("Bubble sort + binary search");
        int[] arr3 = getCopy(array);
        performanceDemo(i -> bubbleSortAndBinarySearch(arr3, i));
        System.out.println();

        System.out.println("Quick sort + binary search");
        int[] arr4 = getCopy(array);
        performanceDemo(i -> quickSortAndBinarySearch(arr4, i));
        System.out.println();

        System.out.println("Default Java sort + binary search");
        int[] arr5 = getCopy(array);
        performanceDemo(i -> defaultSortAndBinarySearch(arr5, i));
    }

    private static void performanceDemo(Function<Integer, Integer> function) {
        long start = currentTimeMillis();
        function.apply(TEST_VALUE);
        long end = currentTimeMillis();
        System.out.printf("Time consumed: %s ms%n", end - start);
    }

    private static int[] initArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static int[] getCopy(int[] array) {
        int[] result = new int[array.length];
        arraycopy(array, 0, result, 0, array.length);
        return result;
    }

}
