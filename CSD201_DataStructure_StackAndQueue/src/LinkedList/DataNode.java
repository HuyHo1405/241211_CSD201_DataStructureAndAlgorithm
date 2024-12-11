
package LinkedList;

public class DataNode <T extends Comparable>{
    //template
    private T data;
    private DataNode<T> next;
    
    //constructor
    public DataNode() {
        this.data = null;
        this.next = null;
    }
    
    //getters
    public T getData() {
        return data;
    }

    public DataNode<T> getNext() {
        return next;
    }
    
    //setters
    public void setData(T data) {
        this.data = data;
    }

    public void setNext(DataNode<T> next) {
        this.next = next;
    }
    
    //toString
    @Override
    public String toString() {
        return data + "";
    }
    
    //compareTo
    public int compareTo(DataNode<T> other){
        return this.data.compareTo(other.getData());
    }

    //behaviours

    
    
    
}

    
