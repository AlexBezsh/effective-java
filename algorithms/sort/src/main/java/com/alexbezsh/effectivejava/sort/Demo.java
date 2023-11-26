package com.alexbezsh.effectivejava.sort;

import java.util.Arrays;
import java.util.function.Consumer;

public class Demo {

    public static void main(String[] args) {
        System.out.println("Merge sort");
        sortDemo(MergeSort::sort);
        System.out.println();

        System.out.println("Insertion sort");
        sortDemo(InsertionSort::sort);
        System.out.println();

        System.out.println("Bubble sort");
        sortDemo(BubbleSort::sort);
        System.out.println();

        System.out.println("Quick sort");
        sortDemo(QuickSort::sort);
        System.out.println();

        System.out.println("Default Java sort");
        sortDemo(Arrays::sort);
    }

    private static void sortDemo(Consumer<int[]> consumer) {
        int[] array = {344, 1, -23, 45, 0, 19, 454, 45, 4, -22, 91};
        System.out.println("Initial array: " + Arrays.toString(array));
        consumer.accept(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }

}
