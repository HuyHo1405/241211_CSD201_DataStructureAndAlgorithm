package tree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    //template
    private TreeNode<T> root;

    //constructor
    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(T data) {
        this.root = new TreeNode<>(data);
    }

    //getters
    public TreeNode<T> getRoot() {
        return root;
    }

    public TreeNode<T> getLeft() {
        return root.getLeft();
    }

    public TreeNode<T> getRight() {
        return root.getRight();
    }

    //setters
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public void setLeft(TreeNode<T> left) {
        this.root.setLeft(left);
    }

    public void setRight(TreeNode<T> right) {
        this.root.setRight(right);
    }

    //basic method
    ///insert
    public void insert(T data) {
        //exception
        if (data == null) {
            throw new IllegalArgumentException();
        }

        //special case - empty tree
        if (isEmpty()) {
            this.root = new TreeNode<>(data);
            return;
        }

        //normal case
        insertHelper(root, data);//call helper for recursion
    }

    private void insertHelper(TreeNode root, T data) {
        //there are 2 case for recursion left or right side
        if (root.getData().compareTo(data) > 0) {
            //base case - left side
            if (root.getLeft() == null) {
                root.setLeft(new TreeNode<>(data));
                return;
            }

            //recursive condition
            insertHelper(root.getLeft(), data);
        } else {
            //base case - right side
            if (root.getRight() == null) {
                root.setRight(new TreeNode(data));
                return;
            }

            //recursive condition
            insertHelper(root.getRight(), data);
        }
    }

    ///delete
    public void delete(T data) {
        //exception
        if (data == null) {
            throw new IllegalArgumentException();
        }

        //normal case
        root = deleteHelper(data, root);
    }

    private TreeNode deleteHelper(T data, TreeNode root) {
        //base case
        if (root == null) {///empty tree or not found
            return null;
        }

        if (root.getData().equals(data)) {///remove the node and update the new tree
            if (root.isLeaf()) {          ////Case 1: is leaf
                return null;
            } else if (root.isFull()) {    ////Case 2: is a full tree
                TreeNode minRight = getMin(root.getRight());
                minRight.setLeft(root.getLeft());
                return root.getRight();
            } else {                      ////Case 3: is a tree with 1 child
                return (root.getLeft() != null)
                        ? root.getLeft()
                        : root.getRight();
            }
        }

        //recursive condition - move down to the left/right subtree
        if (root.getData().compareTo(data) > 0) {
            root.setLeft(deleteHelper(data, root.getLeft()));///left
        } else {
            root.setRight(deleteHelper(data, root.getRight()));///right
        }

        //return the updated tree
        return root;
    }

    private TreeNode getMin(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            return root;
        }

        //normal case
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    ///search
    public TreeNode search(T data) {
        //exception
        if (data == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (isEmpty()) {
            return null;
        }

        //normal case
        return searchTail(root, data);
    }

    private TreeNode searchTail(TreeNode root, T data) {
        //base case
        if (root == null) {//if the node not inside the tree
            return null;
        }

        if (root.getData().equals(data)) {//if the node inside the tree
            return root;
        }

        //recursive condition
        return root.getData().compareTo(data) > 0
                ? searchTail(root.getLeft(), data)
                : searchTail(root.getRight(), data);
    }

    ///is empty
    public boolean isEmpty() {
        return (root == null);
    }

    //traverse
    ///example method for traverse
    private void doSomething(TreeNode node) {
        if (node != null) {//print the node
            T data = (T) node.getData();
            System.out.print(data + " ");
        }
    }

    ///pre-order
    public void preorderTraversal(TreeNode root) {
        //special case
        if (root == null) {
            return;
        }

        if (root.isLeaf()) {
            doSomething(root);
            return;
        }

        //normal case
        doSomething(root);
        preorderTraversal(root.getLeft());
        preorderTraversal(root.getRight());
    }

    ///in-order
    public void inorderTraversal(TreeNode root) {
        //special case
        if (root == null) {
            return;
        }

        if (root.isLeaf()) {
            doSomething(root);
            return;
        }

        //normal case
        inorderTraversal(root.getLeft());
        doSomething(root);
        inorderTraversal(root.getRight());
    }

    ///post-order
    public void postorderTraversal(TreeNode root) {
        //special case
        if (root == null) {
            return;
        }

        if (root.isLeaf()) {
            doSomething(root);
            return;
        }

        //normal case
        postorderTraversal(root.getLeft());
        postorderTraversal(root.getRight());
        doSomething(root);
    }

    ///level order
    public void levelOrderTraversal(TreeNode root) {
        //special case
        if (root == null) {
            return;
        }

        if (root.isLeaf()) {
            doSomething(root);
            return;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode peek = queue.peek();
            if (peek.getLeft() != null) {
                queue.add(peek.getLeft());
            }

            if (peek.getRight() != null) {
                queue.add(peek.getRight());
            }

            doSomething(queue.remove());
        }
    }

    //tree method
    ///height
    public int getHeight(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }

        //normal case
        return 1 + Math.max(
                getHeight(root.getLeft()), 
                getHeight(root.getRight())
        );
    }

    ///get max/min value
    public T getMinValue(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            return (T) root.getData();
        }

        //normal case
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return (T) root.getData();
    }

    public T getMaxValue(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            return (T) root.getData();
        }

        //normal case
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return (T) root.getData();
    }

    ///is balanced
    public boolean isBalanced(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            return true;
        }

        //normal case
        int left = getHeight(root.getLeft());
        int right = getHeight(root.getRight());
        return (left - right <= 1 && left - right >= -1);
    }

    ///is complete tree
    public boolean isCompleteTree(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            return true;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();//traverse by level
        queue.add(root);
        boolean foundNull = false;//flag

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            if(node == null){//found null -> null value to the end
                foundNull = true;//set flag
            }else{
                if(foundNull)return false;//if found non-null value
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
        }
        return true;
    }

    ///is full tree
    public boolean isFullTree(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case - base case
        if (root.isLeaf()) {
            return true;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!node.isFull() && !node.isLeaf()) {
                return false;
            }
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return true;
    }

    ///is perfect tree
    public boolean isPerfectTree(TreeNode root) {
        return isCompleteTree(root) && isFullTree(root);
    }

    //advance methods
    ///mirror
    public TreeNode mirror(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            return root;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            //
            TreeNode temp = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(temp);

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return root;
    }

    ///print/count leaf node
    public void printLeafNode(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            doSomething(root);
            return;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.isLeaf()) {
                doSomething(node);
            }
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public int countLeafNode(TreeNode root) {
        //exception
        if (root == null) {
            throw new IllegalArgumentException();
        }

        //special case
        if (root.isLeaf()) {
            return 1;
        }

        //normal case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.isLeaf()) {
                count++;
            }
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return count;
    }

    ///path to node
    public void pathToNode(TreeNode root, TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        pathToNodeHelper(root, (T) node.getData(), queue);
        while (!queue.isEmpty()) {
            doSomething(queue.poll());
        }
    }

    private void pathToNodeHelper(TreeNode root, T data, Queue<TreeNode> queue) {
        //base case
        if (root == null) {
            throw new IllegalArgumentException();
        }

        if (root.getData().equals(data)) {
            queue.add(root);
            return;
        }

        //recursive condition
        queue.add(root);
        if (root.getData().compareTo(data) > 0) {
            pathToNodeHelper(root.getLeft(), data, queue);
        } else {
            pathToNodeHelper(root.getRight(), data, queue);
        }
    }

    //không biết làm
    ///find closest ancestor of 2 nodes
    public TreeNode findLCA(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        //tha ko biết làm :,)
        throw new NoSuchMethodError();
    }

    ///find diameter of 2 node
    public TreeNode diameter(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        //như hàm trên nên chịu :,)
        throw new NoSuchMethodError();
    }
}
