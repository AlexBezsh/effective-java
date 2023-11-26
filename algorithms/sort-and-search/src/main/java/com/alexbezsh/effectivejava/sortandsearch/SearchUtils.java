package com.alexbezsh.effectivejava.sortandsearch;

import com.alexbezsh.effectivejava.binarysearch.BinarySearch;
import com.alexbezsh.effectivejava.sort.BubbleSort;
import com.alexbezsh.effectivejava.sort.InsertionSort;
import com.alexbezsh.effectivejava.sort.MergeSort;
import com.alexbezsh.effectivejava.sort.QuickSort;
import java.util.Arrays;

public final class SearchUtils {

    private SearchUtils() {
    }

    public static int mergeSortAndBinarySearch(int[] array, int value) {
        MergeSort.sort(array);
        return BinarySearch.iterative(array, value);
    }

    public static int insertionSortAndBinarySearch(int[] array, int value) {
        InsertionSort.sort(array);
        return BinarySearch.iterative(array, value);
    }

    public static int bubbleSortAndBinarySearch(int[] array, int value) {
        BubbleSort.sort(array);
        return BinarySearch.iterative(array, value);
    }

    public static int quickSortAndBinarySearch(int[] array, int value) {
        QuickSort.sort(array);
        return BinarySearch.iterative(array, value);
    }

    public static int defaultSortAndBinarySearch(int[] array, int value) {
        Arrays.sort(array);
        return BinarySearch.iterative(array, value);
    }

}
