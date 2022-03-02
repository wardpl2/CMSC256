package cmsc256;

import java.util.Arrays;

public class ArrayBasedStack<T> implements StackInterface<T> {

    private T[] data;
    private int topOfStack;
    private static final int INITIAL_CAPACITY = 5;

    public ArrayBasedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Array initial size error.");
        }
        clear();
    }

    public ArrayBasedStack() {
        data = (T[]) new Object[INITIAL_CAPACITY];
    }

    private void expandArray() {
        //reinitialize data to itself but with twice the length
        data = Arrays.copyOf(data,data.length * 2);
    }

    private boolean isArrayFull() {
        return topOfStack >= data.length - 1;
    }

    @Override
    public void push(T newEntry) {
        //check if the array is full...
        if (isArrayFull()) {
            //...and expand it if it is
            expandArray();
        }
        //increase the topOfStack and then add the newEntry there
        topOfStack++;
        data[topOfStack] = newEntry;
    }

    @Override
    public T pop() {
        //throw an exception if the array is empty
        if (isEmpty()) {
            throw new EmptyStackException("Array is empty.");
        } else {
            T temp = data[topOfStack]; //save the current topOfStack element...
            data[topOfStack] = null; // set it to null...
            topOfStack--; // make the new topOfStack the previous element...
            return temp; // and return the saved element.
        }
    }

    @Override
    public T peek() {
        //throw and exception if the array is empty
        if (isEmpty()) {
            throw new EmptyStackException("Array is empty.");
        }
        return data[topOfStack];
    }

    @Override
    public boolean isEmpty() {
        return topOfStack <= 0;
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[INITIAL_CAPACITY]; //Create a new array of initial capacity...
        data = tempStack; //and set the data array to the temp array
        topOfStack = -1; //Return the topOfStack back to before the beginning of the array
    }
}
