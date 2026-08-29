public class Solution {

    public int secondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int currentNum = arr[i];

            if (currentNum > largest) {
                secondLargest = largest;
                largest = currentNum;
                
            } else if (currentNum > secondLargest && currentNum != largest) {
                secondLargest = currentNum;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            return -1;
        }

        return secondLargest;
    }
}