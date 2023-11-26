package com.alexbezsh.effectivejava.binarysearch;

import java.util.Arrays;
import java.util.function.Function;

public class Demo {

    public static void main(String[] args) {
        int[] array = new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110};
        System.out.printf("Array: %s%n%n", Arrays.toString(array));

        System.out.println("Iterative search");
        searchDemo(value -> BinarySearch.iterative(array, value));
        System.out.println();

        System.out.println("Recursive search");
        searchDemo(value -> BinarySearch.recursive(array, value));
    }

    private static void searchDemo(Function<Integer, Integer> function) {
        System.out.println("Searching for 8. Expected: -1. Actual: " + function.apply(8));
        System.out.println("Searching for 10. Expected: 0. Actual: " + function.apply(10));
        System.out.println("Searching for 70. Expected: 6. Actual: " + function.apply(70));
        System.out.println("Searching for 110. Expected: 10. Actual: " + function.apply(110));
    }

}
