/**
 * Your implementation of a circular singly linked list.
 *
 * @author Bingyao Wang
 * @userid bwang404
 * @GTID 903406074
 * @version 1.0
 */
public class SinglyLinkedList<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index the requested index for the new element
     * @param data the data for the new element
     * @throws IndexOutOfBoundsException if index is negative or
     * index > size
     * @throws IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null value");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            LinkedListNode<T> temp = new LinkedListNode<>(data, curr.getNext());
            curr.setNext(temp);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to front");
        }
        if (head == null) {
            head = new LinkedListNode<>(data);
            head.setNext(head);
        } else {
            LinkedListNode<T> temp;
            temp = new LinkedListNode<>(head.getData(), head.getNext());
            head.setNext(temp);
            head.setData(data);
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to back");
        } else {
            addToFront(data);
            head = head.getNext();
        }
    }

    /**
     * Removes and returns the element from the index specified.
     *
     * Removing from index 0 should be O(1), all other cases are O(n).
     *
     * @param index the requested index to be removed
     * @return the data formerly located at index
     * @throws IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            return removeFromFront();
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            T data = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return data;
        }
    }

    /**
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the front, null if empty list
     */
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            size--;
            T data = head.getData();
            head = null;
            return data;
        }
        T data = head.getData();
        head.setData(head.getNext().getData());
        head.setNext(head.getNext().getNext());
        size--;
        return data;
    }

    /**
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(n) for all cases.
     *
     * @return the data formerly located at the back, null if empty list
     */
    public T removeFromBack() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            size--;
            T data = head.getData();
            head = null;
            return data;
        } else {
            return removeAtIndex(size - 1);
        }
    }

    /**
     * Removes the last copy of the given data from the list.
     *
     * Must be O(n) for all cases.
     *
     * @param data the data to be removed from the list
     * @return the removed data occurrence from the list itself (not the data
     * passed in), null if no occurrence
     * @throws IllegalArgumentException if data is null
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Do not contain null");
        }
        int index = -1;
        LinkedListNode<T> curr = head;
        LinkedListNode<T> temp = null;
        for (int i = 0; i < size; i++) {
            if (curr.getData().equals(data)) {
                index = i;
                temp = curr;
            }
            curr = curr.getNext();
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == -1) {
            return null;
        } else {
            T rmdata = temp.getData();
            temp.setData(temp.getNext().getData());
            temp.setNext(temp.getNext().getNext());
            if (index == size - 1) {
                head = temp;
            }
            size--;
            return rmdata;
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * Getting index 0 should be O(1), all other cases are O(n).
     *
     * @param index the index of the requested element
     * @return the object stored at index
     * @throws IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            return head.getData();
        }
        LinkedListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length {@code size} holding all of the objects in
     * this list in the same order
     */
    public Object[] toArray() {
        Object[] res = new Object[size];
        LinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            res[i] = curr.getData();
            curr = curr.getNext();
        }
        return res;
    }

    /**
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list of all data.
     *
     * Must be O(1) for all cases.
     */
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * Returns the number of elements in the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}