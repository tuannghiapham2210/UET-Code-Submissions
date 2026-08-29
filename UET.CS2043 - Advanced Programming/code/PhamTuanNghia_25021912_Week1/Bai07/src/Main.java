public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Reverse 12345: " + s.reverse(12345));
        System.out.println("Reverse overflow test: " + s.reverse(1147483649));
    }
}