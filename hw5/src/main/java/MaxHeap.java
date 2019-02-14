import java.util.ArrayList;
/**
 * Your implementation of a max heap.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>> {

    // DO NOT ADD OR MODIFY THESE INSTANCE/CLASS VARIABLES.
    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;

    /**
     * Creates a Heap with an initial capacity of INITIAL_CAPACITY
     * for the backing array.
     *
     * Use the constant field provided. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * The data in the backingArray should be in the same order as it appears
     * in the passed in ArrayList before you start the Build Heap Algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Found null in the list");
            }
            backingArray[i + 1] = data.get(i);
        }
        size = data.size();
        for (int i = size / 2; i > 0; i--) {
            DownHeap(backingArray[i], i);
        }
    }

    /**
     * Downheap helper
     *
     * @param data data passed in
     * @param index the index of the data
     */
    private void DownHeap(T data, int index) {
        if (index > size / 2) {
            return;
        }
        T max;
        int in;
        if (backingArray[index * 2] != null
                && backingArray[index * 2 + 1] != null) {
            if (backingArray[index * 2].
                    compareTo(backingArray[index * 2 + 1]) > 0) {
                max = backingArray[index * 2];
                in = index * 2;
            } else {
                max = backingArray[index * 2 + 1];
                in = index * 2 + 1;
            }
        } else {
            max = backingArray[index * 2];
            in = index * 2;
        }
        if (max.compareTo(data) > 0) {
            swap(in, index);
        }
        DownHeap(backingArray[in], in);
    }

    /**
     * swap helper
     *
     * @param in1 index of the first data
     * @param in2 index of the second data
     */
    private void swap(int in1, int in2) {
        T temp = backingArray[in1];
        backingArray[in1] = backingArray[in2];
        backingArray[in2] = temp;
    }

    /**
     * Adds an item to the heap. If the backing array is full and you're trying
     * to add a new item, then double its capacity.
     *
     * @throws IllegalArgumentException if the item is null
     * @param item the item to be added to the heap
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't add null to the heap");
        }
        if (size == backingArray.length - 1) {
            T[] temp = (T[]) new Comparable[2 * size];
            for (int i = 1; i < size + 1; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size + 1] = item;
        UpHeap(item, size + 1);
        size++;
    }

    /**
     * Upheap helper
     *
     * @param data the data passed in
     * @param index the index of the data
     */
    private void UpHeap(T data, int index) {
        if (index == 1) {
            return;
        }
        if (data.compareTo(backingArray[index / 2]) < 0) {
            return;
        } else {
            swap(index, index / 2);
        }
        UpHeap(data, index / 2);
    }

    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the removed item
     */
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Heap is empty");
        }
        T data = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        DownHeap(backingArray[1], 1);
        size--;
        return data;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element, null if the heap is empty
     */
    public T getMax() {
        return size == 0 ? null : backingArray[1];
    }

    /**
     * Returns if the heap is empty or not.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap and rests the backing array to a new array of capacity
     * {@code INITIAL_CAPACITY}.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the heap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the heap
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}