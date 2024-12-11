package Stack_array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

    //template
    private final T[] array;
    private int top;
    private int currentSize;

    //constructor
    public Stack(int maxSize) {
        this.array = (T[]) new Object[maxSize];
        this.top = -1;
        this.currentSize = 0;
    }

    //getters
    public int getTop() {
        return top;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    //setter
    public void setTop(int top) {
        this.top = top;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    //other methods
    ///push
    public void push(T item) {
        //exception
        if (isFull()) {
            throw new IllegalStateException();
        }

        //normal case
        array[++top] = item;
        currentSize++;
    }

    ///pop
    public T pop() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //normal case
        T popItem = array[top];
        array[top] = null;
        top--;
        currentSize--;
        return popItem;
    }

    ///peek
    public T peek() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //normal case
        return array[top];
    }

    ///is empty
    public boolean isEmpty() {
        return currentSize == 0;
    }

    ///size
    public int size() {
        return currentSize;
    }

    ///clear
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        top = -1;
        currentSize = 0;
    }

    ///is full
    public boolean isFull() {
        return currentSize == array.length;
    }

    ///iterator
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {

        private int index = top;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[index--];
        }

    }
}
