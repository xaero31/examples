package xaero.collection;

import java.util.*;

/**
 * Test for check time to create a collection by different ways
 */
public class CollectionTimeToCreateTest {
    public static void main(String[] args) {
        System.out.println("test start");
        final Integer[] ints = getIntegerArray();

        createWithConstructor(ints);
        createWithCollections(ints);

        // and vise versa
        createWithCollections(ints);
        createWithConstructor(ints);
    }

    private static void createWithConstructor(Integer[] ints) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 99999; i++) {
            new ArrayList<>(Arrays.asList(ints));
        }
        System.out.println("constructor list execute time: " + (System.currentTimeMillis() - start));
    }

    private static void createWithCollections(Integer[] ints) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 99999; i++) {
            final List<Integer> collectionsList = new ArrayList<>();
            Collections.addAll(collectionsList, ints);
        }
        System.out.println("collections list execute time: " + (System.currentTimeMillis() - start));
    }

    private static Integer[] getIntegerArray() {
        final Integer[] ints = new Integer[99999];
        final Random random = new Random();
        for (int i = 0; i < 99999; i++) {
            ints[i] = random.nextInt();
        }
        return ints;
    }
}