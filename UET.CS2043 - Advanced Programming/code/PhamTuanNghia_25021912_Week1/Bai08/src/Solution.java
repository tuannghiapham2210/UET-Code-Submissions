public class Solution {
    public boolean isPalindrome(int n){
        if (n < 0) return false;

        int original = n;
        long reversed = 0;

        while (n != 0){
            int rear = n % 10;
            reversed = reversed * 10 + rear;
            n /= 10;
        }

        return original == reversed;
    }
}
