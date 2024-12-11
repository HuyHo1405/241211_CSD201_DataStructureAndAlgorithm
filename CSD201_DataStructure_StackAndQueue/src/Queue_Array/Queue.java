package Queue_Array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {

    //template
    private final T[] array;
    private int first;
    private int last;
    private int currentSize;

    //constructor
    public Queue(int maxSize) {
        this.array = (T[]) new Object[maxSize];
        this.first = -1;
        this.last = -1;
        this.currentSize = 0;
    }

    //getters
    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    //setters
    public void setFirst(int first) {
        this.first = first;
    }

    public void setLast(int last) {
        this.last = last;
    }

    //other methods
    ///enqueue - not yet
    public void enqueue(T item) {
        //exception
        if (isFull()) {
            throw new IllegalStateException();
        }

        //normal case
        if (isEmpty()) {
            first++;
        }
        last = (last + 1) % array.length;
        currentSize++;
        array[last] = item;

    }

    ///dequeue
    public T dequeue() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //normal case
        T outItem = array[first];
        array[first] = null;
        currentSize--;
        if(isEmpty()){
            first = -1;
            last = -1;
        }else{
            first = (first + 1) % array.length;
        }
        return outItem;
    }

    ///peek
    public T peek() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //normal case
        return array[first];
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
        first = -1;
        last = -1;
        currentSize = 0;
    }

    ///is full
    public boolean isFull() {
        return currentSize == array.length;
    }

    ///iterator
    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

        private int index = isEmpty()? -1: first;
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < currentSize;
        }

        @Override
        public T next() {
            //exception
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            //normal case
            T temp = array[index];
            index = (index + 1) % array.length;
            count++;
            return temp;
        }

    }

}
