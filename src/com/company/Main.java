package com.company;


public class Main<T extends Comparable<T>> {
    private T[] arr;
    private int size = 0;
    private static final int capacity = 10;

    public Main() {
        arr = (T[]) new Comparable[capacity];
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size() == 0;
    }

    public T get(int index) {
        return (T) arr[index];
    }

    public T getMin() {
        return get(0);
    }

    public void insert(T val) {
        if (arr.length == size()) {
            increaseBuffer((int) (1.5 * size()));
        }
        arr[size++] = val;
        bubbleUp();
    }

    private void bubbleUp() {
        int child = size() - 1;
        int parent = (child - 1) / 2;
        while (parent >= 0 && arr[child].compareTo(arr[parent]) < 0) {
            swap(child, parent);
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    private void increaseBuffer(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        T[] arr2 = (T[]) new Comparable[newCapacity];
        for (int i = 0; i < size(); i++) {
            arr2[i] = arr[i];
        }
        arr = arr2;
    }

    private void swap(int child, int parent) {
        T temp = arr[child];
        arr[child] = arr[parent];
        arr[parent] = temp;
    }

    public T extractMin() {
        if (empty()) {
            throw new IllegalArgumentException("Heap is empty");
        }
        T removedItem = arr[0];
        swap(size() - 1, 0);
        arr[size() - 1] = null;
        size--;
        bubbleDown();
        return removedItem;
    }

    private void bubbleDown() {
        int parent = 0;
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        int index = compareAndPick(leftChild, rightChild);

        while (index != -1) {
            swap(index, parent);
            parent = index;
            index = compareAndPick(2 * index + 1, (2 * index) + 2);
        }
    }

    private int compareAndPick(int leftChild, int rightChild) {
        if (leftChild >= capacity || arr[leftChild] == null) {
            return -1;
        } else if ((arr[leftChild].compareTo(arr[rightChild]) <= 0) || (arr[rightChild] == null)) {
            return leftChild;
        }
        return rightChild;
    }
}