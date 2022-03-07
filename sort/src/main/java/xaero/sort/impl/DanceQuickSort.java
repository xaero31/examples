package xaero.sort.impl;

import xaero.sort.Sort;

public class DanceQuickSort implements Sort {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (right - left <= 0) {
            return;
        }

        int start = left;
        int end = right;

        boolean isPivotLeft = true;
        while (left < right) {
            if (arr[right] < arr[left]) {
                swap(arr, left, right);
                isPivotLeft = !isPivotLeft;
            }

            if (isPivotLeft) {
                right--;
            } else {
                left++;
            }
        }

        sort(arr, start, left - 1);
        sort(arr, left + 1, end);
    }

    private void swap(int[] arr, int left, int right) {
        int buffer = arr[right];
        arr[right] = arr[left];
        arr[left] = buffer;
    }
}
