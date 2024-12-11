
package recursion_exercise;

public class EX1 {
    
    //fibo
    public static int fibonacci(int x){
        //exception
        if(x < 0){
            throw new IllegalArgumentException();
        }
        
        //special case
        if(x == 0 || x == 1){
            return x;
        }
        
        //normal case
        int[] memory = new int[x + 1];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;
        }
        
        //start recursion
        return fibonacciHelper(x, memory);
    }
    
    private static int fibonacciHelper(int n, int[] memory){
        //base case - smallest case
        if(n == 0 || n == 1){
            return n;
        }
        
        //special case
        if(memory[n] != -1){
            return memory[n];
        }
        
        //recursive case
        memory[n] =
                fibonacciHelper(n - 1, memory) + 
                fibonacciHelper(n - 2, memory);
        
        return memory[n];
    }

    //fractorial
    public static int fractorial(int x){
        //exception
        if(x < 0){
            throw new IllegalArgumentException();
        }
        
        //special case
        if(x == 0){
            return 1;
        }
        
        //recursion
        return fractorialTail(x);
    }
    
    private static int fractorialTail(int x){
        //base case - smallest case
        if(x == 0){
            return 1;
        }
        
        //recursive case - break into smaller case
        return x * fractorialTail(x - 1);
    }

    //sum of n first positive integer
    public static int sum(int n){
        //exception
        if(n < 0){
            throw new IllegalArgumentException();
        }
        
        //special case
        if(n == 0){
            return 0;
        }
        
        //normal case - recursion
        return sumTail(n);
    }
    
    private static int sumTail(int n){
        //base case
        if(n == 0){
            return 0;
        }
        
        //recursive case
        return n + sumTail(n - 1);
    }
}
