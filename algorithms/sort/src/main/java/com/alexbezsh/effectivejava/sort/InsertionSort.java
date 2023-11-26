package com.alexbezsh.effectivejava.sort;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InsertionSort {

    public static void sort(int[] array) {
        int value;
        for (int i = 1, j = i; i < array.length; i++, j = i) {
            value = array[i];
            while (j > 0 && array[j - 1] > value) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = value;
        }
    }

}
