package com.alexbezsh.effectivejava.sort;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QuickSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    @SuppressWarnings("CyclomaticComplexity")
    private static void sort(int[] array, int left, int right) {
        if (right - left > 0) {
            int temp;
            int l = left;
            int r = right;
            int pivot = array[r--];
            while (l <= r) {
                if (array[l] > pivot && array[r] < pivot) {
                    temp = array[l];
                    array[l] = array[r];
                    array[r] = temp;
                    l++;
                    r--;
                } else if (array[l] > pivot) {
                    r--;
                } else {
                    l++;
                }
            }

            temp = array[l];
            array[l] = array[right];
            array[right] = temp;

            sort(array, left, l - 1);
            sort(array, l + 1, right);
        }
    }

}
