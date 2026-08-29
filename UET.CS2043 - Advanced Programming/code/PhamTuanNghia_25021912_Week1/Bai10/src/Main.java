public class Main {
    public static void main(String[] args) {
        Solution mySolution = new Solution();

        int[] normalArr = {10, 5, 20, 8, 12};
        System.out.println("Normal Array: " + mySolution.secondLargest(normalArr)); 

        int[] duplicateArr = {7, 7, 7, 7};
        System.out.println("Duplicate Array: " + mySolution.secondLargest(duplicateArr)); 

        int[] singleElementArr = {42};
        System.out.println("Single Element Array: " + mySolution.secondLargest(singleElementArr)); 

        int[] negativeArr = {-10, -5, -30, -5};
        System.out.println("Negative Array: " + mySolution.secondLargest(negativeArr)); 
    }
}
