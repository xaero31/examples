package xaero.sort;

import xaero.sort.impl.InsertionSort;
import xaero.sort.impl.MergeSort;
import xaero.sort.impl.QuickSort;

import java.util.Arrays;
import java.util.Random;

public class SortTest {

    // private static Sort sorter = new QuickSort();
    // private static Sort sorter = new MergeSort();
    private static Sort sorter = new InsertionSort();

    public static void main(String[] args) {
        int[] array = getArray(10000);
        System.out.println("start array: " + Arrays.toString(array));

        long start = System.currentTimeMillis();
        sorter.sort(array);
        long end = System.currentTimeMillis();

        System.out.println("sorted array: " + Arrays.toString(array));
        System.out.println("sort time: " + (end - start) + " ms");
    }

    private static int[] getArray(int size) {
        Random random = new Random();
        int[] ints = new int[size];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(ints.length);
        }

        return ints;
    }
}
