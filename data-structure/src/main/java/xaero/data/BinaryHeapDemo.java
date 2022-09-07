package xaero.data;

import xaero.data.structure.BinaryHeap;
import xaero.data.structure.BinaryHeapImpl;

import java.util.Random;

/**
 * demo of binary heap with size == 100
 */
public class BinaryHeapDemo {

    public static void main(String[] args) {
        final BinaryHeap binaryHeap = new BinaryHeapImpl();
        final var random = new Random();

        for (int i = 0; i < 100; i++) {
            binaryHeap.push(Math.abs(random.nextInt(500)));
        }

        System.out.println("initial heap: " + binaryHeap);

        final var stringBuilder = new StringBuilder("pop values: ");
        for (int i = 0; i < 50; i++) {
            stringBuilder.append(binaryHeap.pop()).append(" ");
        }

        System.out.println(stringBuilder);
        System.out.println("remaining heap: " + binaryHeap);
    }
}
