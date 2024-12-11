package Queue_LinkedList;

import LinkedList.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T extends Comparable<T>> implements Iterable<T> {

    //template
    private LinkedList<T> list;
    private int size;

    //constructor
    public Queue() {
        this.list = new LinkedList<>();
        this.size = 0;
    }

    //getters
    //setters
    public void setSize(int size) {
        this.size = size;
    }

    //other methods
    ///enqueue
    public void enqueue(T item) {
        list.addLast(item);
        size++;
    }

    ///dequeue
    public T dequeue() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //normal case
        T outItem = list.getFirst();
        list.removeFirst();
        size--;
        return outItem;
    }

    ///peek
    public T peek() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //normal case
        return list.getFirst();
    }

    ///is empty
    public boolean isEmpty() {
        return size == 0;
    }

    ///size
    public int size() {
        return size;
    }

    ///clear
    public void clear() {
        list.setHead(null);
        list.setTail(null);
        list.setSize(0);
        size = 0;
    }

    ///iterator
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
