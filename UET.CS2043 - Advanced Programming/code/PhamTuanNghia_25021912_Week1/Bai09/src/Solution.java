public class Solution {
    public int sumOfDigits(int n){
        int res = 0;
        while (n != 0){
            int rear = n % 10;
            n /= 10;
            res += rear;
        }
        return res;
    }
}
