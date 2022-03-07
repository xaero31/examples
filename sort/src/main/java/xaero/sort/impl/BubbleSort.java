package xaero.sort.impl;

import xaero.sort.Sort;

public class BubbleSort implements Sort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j + 1 < arr.length - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int buffer = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = buffer;
                }
            }
        }
    }
}
