package LinkedList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable> implements Iterable<T>{

    //template
    private DataNode<T> head;
    private DataNode<T> tail;
    private int size = 0;

    //constructor
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    //getters
    public DataNode<T> getHead() {
        return head;
    }

    public DataNode<T> getTail() {
        return tail;
    }

    //setters
    public void setHead(DataNode<T> head) {
        this.head = head;
    }

    public void setTail(DataNode<T> tail) {
        this.tail = tail;
    }

    //other methods
    ///add
    public void addFirst(T data) {
        DataNode newNode = new DataNode();//new node
        newNode.setData(data);

        //special case
        if (tail == null) {
            tail = newNode;
        }

        //normal case
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public void addLast(T data) {
        DataNode newNode = new DataNode();//new node
        newNode.setData(data);

        if (isEmpty()) {//special case
            head = newNode;
        } else {//normal case
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public void insertAt(int index, T data) {
        //exception
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        //special case
        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size()) {
            addLast(data);
            return;
        }

        //normal case
        DataNode newNode = new DataNode();//new node
        newNode.setData(data);

        DataNode pre = null;//declaration
        DataNode p = head;

        for (int i = 0; i < index; i++) {//get the previous index
            pre = p;
            p = p.getNext();
        }

        pre.setNext(newNode);//add the new node
        newNode.setNext(p);
        size++;
    }

    ///remove
    public void removeFirst() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //special case
        if (size() == 1) {
            head = null;
            tail = null;
            size = 0;
            return;
        }

        //normal case
        head = head.getNext();
        size--;
    }

    public void removeLast() {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //special case
        if (size() == 1) {
            head = null;
            tail = null;
            size = 0;
            return;
        }

        //normal case
        DataNode pre = null;
        DataNode p = head;

        while (p.getNext() != null) {
            pre = p;
            p = p.getNext();
        }

        pre.setNext(null);
        tail = pre;
        size--;
    }

    public void removeAt(int index) {
        //exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        //special case
        if (index == 0) {
            removeFirst();
        }

        if (index == size() - 1) {
            removeLast();
        }

        //normal case
        DataNode pre = null;
        DataNode p = head;

        for (int i = 0; i < index; i++) {
            pre = p;
            p = p.getNext();
        }

        pre.setNext(p.getNext());
        size--;
    }

    public void remove(T data) {
        int index = indexOf(data);//find the data index

        //exception
        if (index == -1) {
            throw new NoSuchElementException();
        }

        //normal case
        removeAt(index);
    }

    ///search
    public boolean contains(T data) {
        //special case
        if (isEmpty()) {
            return false;
        }

        //normal case
        DataNode p = head;

        while (p != null) {
            if (data.equals(p.getData())) {
                return true;
            }
            p = p.getNext();
        }
        return false;
    }

    public int indexOf(T data) {
        //normal case
        DataNode p = head;

        for (int i = 0; p != null; i++) {
            if (data.equals(p.getData())) {
                return i;
            }
            p = p.getNext();
        }

        return -1;

    }

    public T get(int index) {
        //exception
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        //special case
        if (index == 0) {
            return head.getData();
        }

        //normal case
        DataNode p = head;

        for (int i = 0; i < index; i++) {
            p = p.getNext();
        }

        return (T) p.getData();
    }

    ///special access
    public T getFirst() {
        //special case
        if (isEmpty()) {
            return null;
        }

        //normal case
        return head.getData();
    }

    public T getLast() {
        //special case
        if (isEmpty()) {
            return null;
        }

        //normal case
        return tail.getData();
    }

    ///size
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    ///reverse and merge
    public void reverse() {
        //special case
        if (isEmpty() || size == 1) {
            return;
        }

        //normal case
        DataNode t = head;//swap head and tail
        head = tail;
        tail = t;

        DataNode pre = null;
        DataNode p = tail;
        DataNode next = p.getNext();

        while (p != null) {
            p.setNext(pre);//reverse the pointer

            pre = p;//move to the next node
            p = next;
            next = next != null ? next.getNext() : null;//no update if null
        }
    }

    public void merge(LinkedList<T> otherList) {
        //exception
        if (otherList == null) {
            throw new NoSuchElementException();
        }

        //special case
        if (otherList.isEmpty()) {
            return;
        }

        if (this.isEmpty()) {
            this.head = otherList.head;
            this.tail = otherList.tail;
            this.size = otherList.size;
            return;
        }

        //normal case
        LinkedList<T> newList = new LinkedList<>();//create a new list
        int newListSize = this.size + otherList.size;//calculate size

        DataNode p1 = this.head;//declaration
        DataNode p2 = otherList.head;

        while (p1 != null && p2 != null) {//merging
            if (p1.compareTo(p2) <= 0) {
                newList.addLast((T) p1.getData());
                p1 = p1.getNext();
            } else {
                newList.addLast((T) p2.getData());
                p2 = p2.getNext();
            }
        }

        if (p1 != null) {//if p1 remain
            newList.tail.setNext(p1);
            newList.tail = this.tail;
        }

        if (p2 != null) {//if p2 remain
            newList.tail.setNext(p2);
            newList.tail = otherList.tail;
        }

        this.head = newList.head;//copy the new list to this list
        this.tail = newList.tail;
        this.size = newListSize;
    }

    //sort - using merge sort for optimization: O(nlogn)
    public void sort() {
        //special case
        if(isEmpty() || size == 1){
            return;
        }
        
        //normal case
        this.head = mergeSort(this.head);//merge sort
        
        DataNode p = head;
        while(p.getNext() != null){//get the last node
            p = p.getNext();
        }
        
        this.tail = p;//update the tail
    }
    
    private DataNode<T> getMiddle(DataNode<T> head){
        //special case
        if(head == null || head.getNext() == null){
            return head;
        }
        
        //normal case
        DataNode fast = head;//using fast and slow pointer to get the middle
        DataNode slow = head;//fast go twice as fast as slow 
        
        while(fast.getNext() != null && fast.getNext().getNext() != null){
            fast = fast.getNext().getNext();
            slow.getNext();
        }
        
        return slow;
    }
    
    private DataNode<T> mergeSortedList(DataNode<T> list1, DataNode<T> list2){
        //special case
        if(list1 == null){
            return list2;
        }
        
        if(list2 == null){
            return list1;
        }
        
        //normal case
        DataNode result;
        
        if(list1.compareTo(list2) <= 0){
            result = list1;
            result.setNext(mergeSortedList(list1.getNext(), list2));
        }else{
            result = list2;
            result.setNext(mergeSortedList(list1, list2.getNext()));
        }
        
        return result;
    }
    
    private DataNode<T> mergeSort(DataNode<T> head){
        //special case
        if(head == null || head.getNext() == null){
            return head;
        }
        
        //normal case
        DataNode middle = getMiddle(head);//get middle to divide left and right
        DataNode nextOfMiddle = middle.getNext();
        
        middle.setNext(null);//seperate the list
        
        DataNode left = mergeSort(head);//devide and conquer
        DataNode right = mergeSort(nextOfMiddle);
        
        return mergeSortedList(left, right);//return the result
    }
    
    //remove duplicated
    public void removeDuplicated() {
        //special case
        if(isEmpty() || size == 1){
            return;
        }
        
        //normal case
        HashSet<T> seen = new HashSet<>();//using hash set contains
        
        DataNode p = head;
        DataNode pre = null;
        
        while(p != null){
            if(seen.contains((T)p.getData())){
                pre.setNext(p.getNext());//remove the duplicted node
                
                if(p == tail){//update the tail if last node
                    tail = pre;
                }
            }else{//no duplicted node, updated seen and pre
                seen.add((T) p.getData());
                pre = p;
            }
            p = p.getNext();//next node
        }
    }

    ///print
    public void printList() {
        DataNode p = head;
        while (p != null) {
            System.out.print(p.getData() + " -> ");
            p = p.getNext();
        }
        System.out.println("null");
    }

    ///iterable 
    @Override
    public Iterator<T> iterator() {
        return new linkedListIterator();
    }
    
    private class linkedListIterator implements Iterator<T>{
        private DataNode<T> p = head;
        
        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            T data = p.getData();
            p = p.getNext();
            return data;
        }
        
    }
    
    
}
