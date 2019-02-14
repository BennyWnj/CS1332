/**
 * Your implementation of an ArrayList.
 *
 * @author Bingyao(Benny) Wang
 * @userid bwang404
 * @GTID 903406074
 * @version 1.0
 */
public class ArrayList<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * The initial capacity of the array list.
     *
     * DO NOT CHANGE THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /**
     *Doubles the capacity of the backing array
     *
     *
     */
    private void doubleArray() {
        T[] temp = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            temp[i] = backingArray[i];
        }
        backingArray = temp;
    }

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object array to T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the element to the index specified.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Adding to index {@code size} should be amortized O(1),
     * all other adds are O(n).
     *
     * @param index the index where you want the new element
     * @param data the data to add to the list
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index > size || index < 0) {
            throw new java.lang.IndexOutOfBoundsException("Index is out of"
                    + "bounds");
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot insert null"
                    + "data into data structure");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
//            if (size == backingArray.length) {
//                doubleArray();
//            }
//            for (int i = size - 1; i > index - 1; i--) {
//                backingArray[i + 1] = backingArray[i];
//            }
//            backingArray[index] = data;
            if (size == backingArray.length) {
                T[] temp = (T[]) new Object[INITIAL_CAPACITY * 2];
                for (int i = 0; i < index; i++) {
                    temp[i] = backingArray[i];
                }
                temp[index] = data;
                for (int i = index + 1; i < size + 1; i++) {
                    temp[i] = backingArray[i - 1];
                }
                backingArray = temp;
            } else {
                for (int i = size; i > index - 1; i--) {
                    backingArray[i] = backingArray[i - 1];
                }
                backingArray[index] = data;
            }
            size++;
        }
    }

    /**
     * Adds the given data to the front of your array list.
     *
     * Remember that this add may require elements to be shifted.
     * 
     * Must be O(n).
     *
     * @param data the data to add to the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot insert null"
                    + "data at the front");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i + 1] = backingArray[i];
            }
            temp[0] = data;
            backingArray = temp;
        } else {
            for (int i = size - 1; i >= 0; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[0] = data;
        }
        size++;
    }

    /**
     * Adds the given data to the back of your array list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot insert null"
                    + "data at the end");
        }
        if (size == backingArray.length) {
            doubleArray();
        }
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the element at {@code index}.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * This method should be O(1) for index {@code size - 1} and O(n) in 
     * all other cases.
     *
     * @param index the index of the element
     * @return the object that was formerly at that index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new java.lang.IndexOutOfBoundsException("Index is out of"
                    + "bounds");
        }
        T data = backingArray[index];
        if (index == 0) {
            removeFromFront();
        } else if (index == size) {
            removeFromBack();
        } else {
            for (int i = index + 1; i < size; i++) {
                backingArray[i - 1] = backingArray[i];
            }
            backingArray[size - 1] = null;
            size--;
        }
        return data;
    }

    /**
     * Removes and returns the first element in the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data from the front of the list or null if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T data = backingArray[0];
        for (int i = 1; i < size; i++) {
            backingArray[i - 1] = backingArray[i];
        }
        backingArray[size - 1] = null;
        size--;
        return data;
    }

    /**
     * Removes and returns the last element in the list.
     * 
     * Must be O(1).
     *
     * @return the data from the back of the list or null if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T data = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return data;
    }

    /**
     * Returns the element at the given index.
     *
     * Must be O(1).
     *
     * @param index the index of the element
     * @return the data stored at that index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new java.lang.IndexOutOfBoundsException("Index is out of"
                    + "bounds");
        }
        return backingArray[index];
    }

    /**
     * Finds the index at which the given data is located in the ArrayList.
     *
     * If there are multiple instances of the data in the ArrayList, then return
     * the index of the last instance.
     *
     * Be sure to think carefully about whether value or reference equality
     * should be used.
     *
     * Must be O(n), but consider which end of the ArrayList to start from.
     *
     * @param data the data to find the last index of
     * @return the last index of the data or -1 if the data is not in the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public int lastIndexOf(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot contain null");
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (backingArray[i].equals(data)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Returns a boolean value representing whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list. Resets the backing array to a new array of the initial
     * capacity.
     *
     * Must be O(1).
     */
    public void clear() {
        T[] temp = (T[]) new Object[INITIAL_CAPACITY];
        backingArray = temp;
        size = 0;
    }

    /**
     * Returns the size of the list as an integer.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the backing array for this list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array for this list
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
