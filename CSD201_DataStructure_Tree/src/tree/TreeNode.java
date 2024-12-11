
package tree;

public class TreeNode<T extends Comparable<T>> {
    //template
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    //constructors
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    
    //getters
    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }
    
    //setters
    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
    
    //other methods
    public boolean isLeaf(){
        return (left == null && right == null);
    }

    public boolean isFull(){
        return (left != null && right != null);
    }
    
    public int compareTo(TreeNode<T> other){
        return this.data.compareTo(other.data);
    }
    
    @Override
    public String toString() {
        return 
                "{" + 
                data + ", " + 
                left + ", " + 
                right + 
                '}';
    }
    
    
    
    
    
}