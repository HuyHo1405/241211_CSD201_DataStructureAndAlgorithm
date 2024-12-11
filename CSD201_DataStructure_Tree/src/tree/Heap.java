package tree;

import java.util.LinkedList;
import java.util.Queue;

public class Heap<T extends Comparable<T>> {

    //template
    private TreeNode<T> root;
    private int size = 0;

    //constructor
    public Heap() {
        this.root = null;
    }

    //getters
    public int size() {
        return size;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getLeft() {
        return root.getLeft();
    }

    public TreeNode getRight() {
        return root.getRight();
    }

    public TreeNode getParent(TreeNode node) {
        //exception
        if (node == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (node == root) {
            return null;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();//BFS
        queue.add(root);
        TreeNode current;

        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.getLeft() == node || current.getRight() == node) {
                return current;//found parent
            }
            if (current.getLeft() != null) {
                queue.add(current.getLeft());//add left
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());//add right
            }
        }

        return null;//not found parent
    }

    //setters
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void setLeft(TreeNode left) {
        this.root.setLeft(left);
    }

    public void setRight(TreeNode right) {
        this.root.setRight(right);
    }

    //basic method
    ///insert
    public void insert(T data) {
        //exception
        if (data == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root == null) {
            root = new TreeNode(data);
            size++;
            return;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();//BFS
        queue.add(root);
        TreeNode newNode = new TreeNode(data);

        while (!queue.isEmpty()) {//all the internal node include root is full
            TreeNode curr = queue.poll();
            if (curr.getLeft() == null) {//not full node -> add the new node to the first empty
                curr.setLeft(newNode);
                break;//stop finding
            } else if (curr.getRight() == null) {
                curr.setRight(newNode);
                break;//stop finding
            }

            queue.add(curr.getLeft());//if not continue
            queue.add(curr.getRight());
        }
        size++;
        heapifyUp(newNode);//check the Heap structure
    }

    private void heapifyUp(TreeNode node) {
        while (node != null) {//avoid null pointer
            TreeNode parent = getParent(node);//get parent

            //check parent
            if (parent == null) {//parent == null -> at root
                return;
            }

            //compare parent and child
            if (parent.getData().compareTo(node.getData()) < 0) {
                T temp = (T) parent.getData();//swap if child > parent
                parent.setData(node.getData());
                node.setData(temp);

                node = parent;//update the child to compare the upper
            } else {
                return;//the child is at the correct position
            }
        }
    }

    ///extract
    public T extract() {
        //special case - empty heap
        if (root == null) {
            return null;
        }
        
        //special case - single element
        if (root.isLeaf()) {
            T rootData = root.getData();
            root = null;
            size--;
            return rootData;
        }

        //normal case: swap root with the last node then remove last
        T rootData = root.getData();                            
        
        TreeNode lastParent = getLastParent(root, 1);
        if (!lastParent.isFull()) {   
            root.setData((T) lastParent.getLeft().getData());
            lastParent.setLeft(null);
        } else {
            root.setData((T) lastParent.getRight().getData());
            lastParent.setRight(null);       
        }                                                       
        
        size--;                               
        heapifyDown(root);//check heap structure         
        
        return rootData;     
    }

    private void heapifyDown(TreeNode root) {
        //base case
        if (root.isLeaf()) {
            return;
        }

        //recursive condition
        TreeNode childToSwap;
        TreeNode left = root.getLeft();
        TreeNode right = root.getRight();

        if (!root.isFull()) {//find the childToSwap
            childToSwap = left; 
        } else {
            childToSwap = (left.getData().compareTo(right.getData()) > 0)?
                    left : right;
        }

        if(childToSwap.getData().compareTo(root.getData()) > 0){
            T temp = (T) root.getData();
            root.setData(childToSwap.getData());
            childToSwap.setData(temp);

            heapifyDown(childToSwap);//recursive in the affected subtree
        }
        
    }

    private TreeNode getLastParent(TreeNode root, int currentIndex) {
        //base case
        if (root == null || currentIndex * 2 > size) {//not found
            return null;
        }
        
        if (size == 2 * currentIndex || size == 2 * currentIndex + 1) {//found last parent
            return root;
        }

        //recursize condition
        TreeNode left = getLastParent(root.getLeft(), 2 * currentIndex);
        TreeNode right = getLastParent(root.getRight(), 2 * currentIndex + 1);

        if (left != null || right != null) {
            return (left != null) ? left : right;//found at left or right
        } else {
            return null;//not found
        }
    }

    ///peek
    public T peek(){
        return root.getData();
    }
     
    ///is empty
    public boolean isEmpty(){
        return root == null;
    }
    
    ///replace
    public void replace(T data){
        //exception
        if(data == null){
            throw new IllegalArgumentException();
        }
        
        //
        root.setData(data);
        
        //special case
        if(root.isLeaf()){
            return;
        }
        
        //normal case
        heapifyDown(root);
    }
    
}
