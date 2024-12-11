
package test;

import tree.BinarySearchTree;
import tree.Heap;
import tree.TreeNode;

public class Test {
    
//    public static void main(String[] args) {
//        BinarySearchTree<Integer> t = new BinarySearchTree<>();
//        t.insert(10);
//        t.insert(5);
//        t.insert(15);
//        t.insert(1);
//        t.insert(19);
//        t.insert(11);
//        t.insert(9);
//        System.out.print("Tree: ");
//        System.out.println(t.getRoot());
//        System.out.println("--search--");
//        System.out.println(t.search(2));
//        System.out.println(t.search(10));
//        System.out.println(t.search(3));
//        System.out.println(t.search(0));
//        System.out.println("");
        
        
//        System.out.println("--Delete--");
//        t.delete(10);
//        System.out.println(t.getRoot());
//        t.delete(2);
//        System.out.println(t.getRoot());
//        t.delete(3);
//        System.out.println(t.getRoot());
//        t.delete(0);
//        System.out.println(t.getRoot());
//        t.delete(2);
//        System.out.println(t.getRoot());
//        System.out.println("");

//        System.out.println("--traverse--");
//        System.out.print("pre : ");
//        t.preorderTraversal(t.getRoot());
//        System.out.println("");
//        System.out.print("in  : ");
//        t.inorderTraversal(t.getRoot());
//        System.out.println("");
//        System.out.print("post: ");
//        t.postorderTraversal(t.getRoot());
//        System.out.println("");
//        System.out.print("lvl : ");
//        t.levelOrderTraversal(t.getRoot());
//        System.out.println("");

//        System.out.println("--height--");
//        System.out.print("height: ");
//        System.out.println(t.getHeight(t.getRoot()));

//        System.out.println("--is balanced--");
//        System.out.println(t.isBalanced(t.getRoot()));
//        System.out.println("");

//        System.out.println("--is full tree--");
//        System.out.println(t.isFullTree(t.getRoot()));
//        System.out.println("");
//        
//        System.out.println("--is complete tree--");
//        System.out.println(t.isCompleteTree(t.getRoot()));
//        System.out.println("");
//
//        System.out.println("--is perfect tree--");
//        System.out.println(t.isPerfectTree(t.getRoot()));
//        System.out.println("");

//        System.out.println("--mirror--");
//        System.out.println(t.mirror(t.getRoot()));
//        System.out.println("");

//        System.out.println("--print/count leaf node--");
//        t.printLeafNode(t.getRoot());
//        System.out.println("");
//        System.out.println("total leaf: " + t.countLeafNode(t.getRoot()));
//        System.out.println("");
        
//        System.out.println("--path to node--");
//        int n = 15;
//        System.out.println("path to node " + n + ": ");
//        t.pathToNode(t.getRoot(), new TreeNode(n));
//        System.out.println("");

            
//    }
    public static void main(String[] args) {
        System.out.println("--insert--");
        Heap<Integer> h = new Heap<>();
        h.insert(1);
        h.insert(2);
        h.insert(3);
        h.insert(4);
        h.insert(5);
        System.out.println(h.getRoot());
        System.out.println("");
        
        System.out.println("--extract--");
        System.out.println(h.extract());
        System.out.println(h.getRoot());
        System.out.println(h.extract());
        System.out.println(h.getRoot());
        System.out.println("");
        
        System.out.println("--peek--");
        System.out.println(h.peek());
        System.out.println("");
        
        System.out.println("--size, isEmpty--");
        System.out.println(h.size());
        System.out.println(h.isEmpty());
        System.out.println("");
        
        System.out.println("--replace--");
        h.replace(0);
        System.out.println(h.getRoot());
        System.out.println("");
        
    }
}
