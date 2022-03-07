package xaero.sort.impl;

import xaero.sort.Sort;

public class ShakeSort implements Sort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j + 1 < arr.length - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int buffer = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = buffer;
                }
            }

            for (int j = arr.length - 2 - i; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int buffer = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = buffer;
                }
            }
        }
    }
}
