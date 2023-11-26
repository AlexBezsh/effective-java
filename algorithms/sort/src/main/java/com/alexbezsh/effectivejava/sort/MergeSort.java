package com.alexbezsh.effectivejava.sort;

import java.util.Arrays;
import lombok.experimental.UtilityClass;
import static java.lang.Integer.MAX_VALUE;

@UtilityClass
public class MergeSort {

    public static void sort(int[] array) {
        sortMerge(array, 0, array.length - 1);
    }

    private static void sortMerge(int[] array, int start, int end) {
        if (end - start > 0) {
            int middle = (end + start) / 2;
            sortMerge(array, start, middle);
            sortMerge(array, middle + 1, end);
            merge(array, start, middle, end);
        }
    }

    @SuppressWarnings("AvoidInlineConditionals")
    private static void merge(int[] array, int start, int middle, int end) {
        int[] left = Arrays.copyOfRange(array, start, middle + 1);
        int[] right = Arrays.copyOfRange(array, middle + 1, end + 1);
        int totalSize = left.length + right.length;

        int firstNum;
        int secondNum;
        int nextLeftIndex = 0;
        int nextRightIndex = 0;
        int nextOriginalArrayIndex = start;
        for (int i = 1; i <= totalSize; i++) {
            firstNum = nextLeftIndex == left.length ? MAX_VALUE : left[nextLeftIndex];
            secondNum = nextRightIndex == right.length ? MAX_VALUE : right[nextRightIndex];
            if (firstNum < secondNum) {
                array[nextOriginalArrayIndex] = firstNum;
                nextLeftIndex++;
            } else {
                array[nextOriginalArrayIndex] = secondNum;
                nextRightIndex++;
            }
            nextOriginalArrayIndex++;
        }
    }

}
