package Main;

import Queue_Array.Queue;

public class Test {
    
    public static void main(String[] args) {
        Queue<Integer> a = new Queue<>(4);
        
        a.enqueue(1);
        a.enqueue(2);
        a.enqueue(3);
        a.enqueue(4);
        a.dequeue();
        a.enqueue(5);
        System.out.println(a.size());
        for (Integer x : a) {
            System.out.println(x);
        }
    }
}
