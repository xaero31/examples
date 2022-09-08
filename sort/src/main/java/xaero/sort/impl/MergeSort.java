package xaero.sort.impl;

import xaero.sort.Sort;

import static java.lang.System.arraycopy;

public class MergeSort implements Sort {

    @Override
    public void sort(int[] arr) {
        arraycopy(mergeSort(arr), 0, arr, 0, arr.length);
    }

    private int[] mergeSort(int[] array) {
        return mergeSort(array, 0, array.length - 1);
    }

    private int[] mergeSort(int[] array, int left, int right) {
        if (right - left < 1) {
            return new int[]{array[left]};
        }

        final var middle = left + (right - left) / 2;
        final var leftArray = mergeSort(array, left, middle);
        final var rightArray = mergeSort(array, middle + 1, right);

        return merge(leftArray, rightArray);
    }

    private int[] merge(int[] leftArray, int[] rightArray) {
        final var resultArray = new int[leftArray.length + rightArray.length];

        var resultPointer = 0;
        var leftPointer = 0;
        var rightPointer = 0;

        while (leftPointer < leftArray.length && rightPointer < rightArray.length) {
            resultArray[resultPointer++] = leftArray[leftPointer] < rightArray[rightPointer] ?
                    leftArray[leftPointer++] :
                    rightArray[rightPointer++];
        }

        while (leftPointer < leftArray.length) {
            resultArray[resultPointer++] = leftArray[leftPointer++];
        }

        while (rightPointer < rightArray.length) {
            resultArray[resultPointer++] = rightArray[rightPointer++];
        }

        return resultArray;
    }
}
