
package stack_linkedList;

import LinkedList.LinkedList;
import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<T extends Comparable<T>> implements Iterable<T>{
    //template
    private LinkedList<T> list;
    private int size = 0;
    
    //constructor
    public Stack() {
        this.list = new LinkedList<>();
    }
    
    //getters
    
    //setters
    
    //other methods
    ///push
    public void push(T item){
        list.addFirst(item);
        size++;
    }
    
    ///pop
    public T pop(){
        //exception
        if(isEmpty()){
            throw new EmptyStackException();
        }
        
        //normal case
        T item = list.getFirst();
        list.removeFirst();
        size--;
        return item;
    }

    ///peek
    public T peek(){
        //exception
        if(isEmpty()){
            throw new EmptyStackException();
        }
        
        //normal case
        return list.getFirst();
    }

    ///is empty
    public boolean isEmpty(){
        return list.isEmpty();
    }

    ///size
    public int size(){
        return size;
    }

    ///clear
    public void clear(){
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
