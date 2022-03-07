package xaero.sort.impl;

import xaero.sort.Sort;

public class QuickSort implements Sort {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (right - left <= 0) {
            return;
        }

        int current = left;
        int wall = left;
        int pivot = right;

        while (current < pivot) {
            if (arr[current] < arr[pivot]) {
                swap(arr, current, wall);
                wall++;
            }

            current++;
        }

        swap(arr, wall, pivot);
        sort(arr, left, wall - 1);
        sort(arr, wall + 1, right);
    }

    private void swap(int[] arr, int left, int right) {
        int buffer = arr[right];
        arr[right] = arr[left];
        arr[left] = buffer;
    }
}
