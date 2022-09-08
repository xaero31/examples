package xaero.sort.impl;

import xaero.sort.Sort;

public class InsertionSort implements Sort {

    @Override
    public void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        var key = 1;

        while (key < arr.length) {
            for (int i = key; i > 0; i--) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i - 1);
                } else {
                    break;
                }
            }
            key++;
        }
    }

    private void swap(int[] arr, int left, int right) {
        final var temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
