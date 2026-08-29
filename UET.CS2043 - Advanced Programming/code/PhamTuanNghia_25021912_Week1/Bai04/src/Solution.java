public class Solution{
    public long fibonacci(long n){
        //edge case
        if (n < 0) return -1;

        //base cases:
        if (n == 0) return 0;
        if (n == 1) return 1;

        long prev = 0;
        long curr = 1;

        for (long i = 0; i <= n - 2; i++){
            if (curr > Long.MAX_VALUE - prev) return Long.MAX_VALUE;

            long next = prev + curr;

            prev = curr;
            curr = next;
        } 

        return curr;
    }
}