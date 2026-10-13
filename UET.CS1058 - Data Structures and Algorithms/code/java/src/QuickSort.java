//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

public class QuickSort {
    // Helper function to compare two Comparable items
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // Helper function to exchange two Comparables items
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Partition method to put one element in place
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            // finding the item on the left to swap
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }

            // finding the item on the right to swap
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            // checking if pointers cross
            if (i >= j) {
                break;
            }

            // swap
            exch(a, i, j);
        }

        // swapping the pivot
        exch(a, lo, j);

        // returns the index of the element now known to be in place
        return j;
    }

    // private recursive sort
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);

        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // public API
    public static void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // Helper to check if array is sorted
    private static boolean isSorted(Comparable[] a) {
        if (a == null) {
            return true;
        }
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test 1: Standard Integer array
        Integer[] a = { 38, 27, 43, 3, 9, 82, 10 };
        sort(a);
        if (!isSorted(a)) {
            throw new RuntimeException("TEST FAILED: Integer array is not sorted.");
        }

        // Test 2: Reverse sorted array
        Integer[] b = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        sort(b);
        if (!isSorted(b)) {
            throw new RuntimeException("TEST FAILED: Reverse array test failed.");
        }

        // Test 3: Already sorted array
        Integer[] c = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        sort(c);
        if (!isSorted(c)) {
            throw new RuntimeException("TEST FAILED: Already sorted array test failed.");
        }

        // Test 4: Array with duplicate elements
        Integer[] d = { 5, 1, 5, 2, 8, 5, 1, 9, 5 };
        sort(d);
        if (!isSorted(d)) {
            throw new RuntimeException("TEST FAILED: Duplicates test failed.");
        }

        // Test 5: All elements are identical
        Integer[] e = { 7, 7, 7, 7, 7, 7, 7 };
        sort(e);
        if (!isSorted(e)) {
            throw new RuntimeException("TEST FAILED: Identical elements test failed.");
        }

        // Test 6: Single element and empty array
        Integer[] single = { 42 };
        sort(single);
        if (!isSorted(single)) {
            throw new RuntimeException("TEST FAILED: Single element test failed.");
        }

        Integer[] empty = {};
        sort(empty);
        if (!isSorted(empty)) {
            throw new RuntimeException("TEST FAILED: Empty array test failed.");
        }

        // Test 7: Array with negative numbers
        Integer[] negatives = { -5, 12, -3, 0, 8, -15, 7, 0 };
        sort(negatives);
        if (!isSorted(negatives)) {
            throw new RuntimeException("TEST FAILED: Negative numbers test failed.");
        }

        // Test 8: String array
        String[] words = { "quicksort", "algorithm", "data", "structures", "uet", "vnu" };
        sort(words);
        if (!isSorted(words)) {
            throw new RuntimeException("TEST FAILED: String array test failed.");
        }

        // SUCCESS
        System.out.println("quicksort works!");
    }
}
