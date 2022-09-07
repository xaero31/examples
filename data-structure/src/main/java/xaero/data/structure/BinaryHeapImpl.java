package xaero.data.structure;

import java.util.Arrays;

public class BinaryHeapImpl implements BinaryHeap {

    private int[] values = new int[100];
    private int endPointer = 0;

    @Override
    public void push(int value) {
        values[endPointer++] = value;
        balanceUp(getParent(endPointer - 1));
    }

    @Override
    public int pop() {
        if (endPointer <= 0) {
            return -1;
        }

        final var value = values[0];

        swap(0, --endPointer);
        balanceDown(0);

        return value;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChild(int index) {
        final var leftChild = (index * 2) + 1;
        return leftChild < endPointer ? leftChild : -1;
    }

    private int getRightChild(int index) {
        final var rightChild = (index * 2) + 2;
        return rightChild < endPointer ? rightChild : -1;
    }

    private void swap(int firstIndex, int secondIndex) {
        final var first = values[firstIndex];
        final var second = values[secondIndex];
        values[firstIndex] = second;
        values[secondIndex] = first;
    }

    private void balanceUp(int index) {
        if (index < 0) {
            return;
        }

        final var leftChild = getLeftChild(index);
        if (leftChild > 0 && values[leftChild] < values[index]) {
            swap(index, leftChild);
        }

        final var rightChild = getRightChild(index);
        if (rightChild > 0 && values[rightChild] < values[index]) {
            swap(index, rightChild);
        }

        final var parent = getParent(index);
        if (parent >= 0 && parent != index) {
            balanceUp(parent);
        }
    }

    private void balanceDown(int index) {
        if (index < 0) {
            return;
        }

        final var toBalanceChild = findBalanceDownChild(getLeftChild(index), getRightChild(index));
        if (toBalanceChild > 0 && values[toBalanceChild] < values[index]) {
            swap(index, toBalanceChild);
            balanceDown(toBalanceChild);
        }
    }

    private int findBalanceDownChild(int leftChild, int rightChild) {
        if (leftChild < 0 || rightChild < 0 || values[leftChild] < values[rightChild]) {
            return leftChild;
        } else {
            return rightChild;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(values, endPointer));
    }
}
