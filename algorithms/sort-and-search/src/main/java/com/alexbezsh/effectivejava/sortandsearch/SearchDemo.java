package com.alexbezsh.effectivejava.sortandsearch;

import java.util.Arrays;
import java.util.function.Function;
import static com.alexbezsh.effectivejava.sort.MergeSort.sort;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.bubbleSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.defaultSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.insertionSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.mergeSortAndBinarySearch;
import static com.alexbezsh.effectivejava.sortandsearch.SearchUtils.quickSortAndBinarySearch;

public class SearchDemo {

    public static void main(String[] args) {
        int[] array = testArray();
        System.out.println("Array: " + Arrays.toString(array));
        sort(array);
        System.out.printf("Sorted: %s%n%n", Arrays.toString(array));

        System.out.println("Merge sort + binary search");
        searchDemo(i -> mergeSortAndBinarySearch(testArray(), i));
        System.out.println();

        System.out.println("Insertion sort + binary search");
        searchDemo(i -> insertionSortAndBinarySearch(testArray(), i));
        System.out.println();

        System.out.println("Bubble sort + binary search");
        searchDemo(i -> bubbleSortAndBinarySearch(testArray(), i));
        System.out.println();

        System.out.println("Quick sort + binary search");
        searchDemo(i -> quickSortAndBinarySearch(testArray(), i));
        System.out.println();

        System.out.println("Default Java sort + binary search");
        searchDemo(i -> defaultSortAndBinarySearch(testArray(), i));
    }

    private static int[] testArray() {
        return new int[] {344, 1, -23, 45, 0, 19, 454, 45, 4, -22, 91};
    }

    private static void searchDemo(Function<Integer, Integer> function) {
        System.out.println("Searching for 1. Expected: 3. Actual: " + function.apply(1));
        System.out.println("Searching for -23. Expected: 0. Actual: " + function.apply(-23));
        System.out.println("Searching for 91. Expected: 8. Actual: " + function.apply(91));
    }

}
