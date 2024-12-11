package Main;

import LinkedList.LinkedList;

public class Test {

    public static void main(String[] args) {
        LinkedList<Integer> listA = new LinkedList<>();
        listA.addFirst(80);
        listA.addFirst(40);
        listA.addFirst(40);
        listA.addFirst(30);
        listA.addFirst(0);
        listA.addFirst(80);
        listA.printList();
        
        listA.removeDuplicated();
        listA.printList();
    }
}
