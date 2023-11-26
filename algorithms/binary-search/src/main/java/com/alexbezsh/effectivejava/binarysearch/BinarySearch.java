package com.alexbezsh.effectivejava.binarysearch;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BinarySearch {

    public static int iterative(int[] sortedArray, int value) {
        if (sortedArray.length == 0) {
            return -1;
        }
        int start = 0;
        int end = sortedArray.length - 1;
        int middleIndex = middle(start, end);
        while (sortedArray[middleIndex] != value) {
            if ((end - start) <= 0) {
                return -1;
            }
            if (sortedArray[middleIndex] > value) {
                end = middleIndex - 1;
            } else {
                start = middleIndex + 1;
            }
            middleIndex = middle(start, end);
        }
        return middleIndex;
    }

    public static int recursive(int[] sortedArray, int value) {
        if (sortedArray.length == 0) {
            return -1;
        }
        return recursive(sortedArray, value, 0, sortedArray.length - 1);
    }

    private static int recursive(int[] sortedArray, int value, int start, int end) {
        int middleIndex = middle(start, end);
        if (sortedArray[middleIndex] == value) {
            return middleIndex;
        }
        if ((end - start) <= 0) {
            return -1;
        }
        if (sortedArray[middleIndex] > value) {
            return recursive(sortedArray, value, start, middleIndex - 1);
        }
        return recursive(sortedArray, value, middleIndex + 1, end);
    }

    private int middle(int start, int end) {
        return (end + start) / 2;
    }

}
