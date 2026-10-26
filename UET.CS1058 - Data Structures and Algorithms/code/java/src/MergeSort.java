//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

public class MergeSort {

    // Helper function to compare two Comparable items
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    // Merge two sorted subarrays a[lo..mid] and a[mid+1..hi] using aux[]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Copy elements to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;

        // Merge back into original array a[]
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
    }


    // =========================================================================
    // 1. TOP-DOWN MERGESORT (RECURSIVE)
    // =========================================================================

    // Recursive helper function for top-down mergesort
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }


    // Public API to sort an array using top-down (recursive) mergesort
    public static void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }


    // =========================================================================
    // 2. BOTTOM-UP MERGESORT (NON-RECURSIVE / ITERATIVE)
    // Reference: Algorithms 4th Edition - Robert Sedgewick & Kevin Wayne
    // =========================================================================

    /**
     * Bottom-up mergesort (iterative, non-recursive).
     * - Does lg N passes of pairwise merges.
     * - Subarray size sz doubles in each pass: 1, 2, 4, 8, ...
     * - In the last step of each pass, Math.min ensures the upper bound does not exceed N - 1.
     *
     * @param a the array to be sorted
     */
    public static void sortBottomUp(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int N = a.length;
        Comparable[] aux = new Comparable[N];

        // sz: subarray size (1, 2, 4, 8, ...)
        for (int sz = 1; sz < N; sz = sz + sz) {
            // lo: index of subarray to merge
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                int mid = lo + sz - 1;
                int hi = Math.min(lo + sz + sz - 1, N - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }


    // Alias for sortBottomUp matching the textbook/slide name sortBU
    public static void sortBU(Comparable[] a) {
        sortBottomUp(a);
    }


    // Static nested class matching the textbook class MergeBU
    public static class MergeBU {
        public static void sort(Comparable[] a) {
            MergeSort.sortBottomUp(a);
        }
    }


    // =========================================================================
    // 3. HELPER & TESTING METHODS
    // =========================================================================

    // Helper to check if array is sorted
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }


    // Helper to print array elements
    private static void printArray(Comparable[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


    // Main test function
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   TESTING TOP-DOWN MERGESORT");
        System.out.println("==================================================");

        // Test 1: Integer array
        Integer[] a = { 38, 27, 43, 3, 9, 82, 10 };
        sort(a);
        if (!isSorted(a)) {
            throw new RuntimeException("TEST FAILED: Top-down integer array is not sorted.");
        }
        System.out.print("Test 1 (Random ints): ");
        printArray(a);

        // Test 2: Reverse sorted array
        Integer[] b = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        sort(b);
        if (!isSorted(b)) {
            throw new RuntimeException("TEST FAILED: Top-down reverse array test failed.");
        }
        System.out.print("Test 2 (Reverse sorted): ");
        printArray(b);

        // Test 3: Duplicates
        Integer[] c = { 5, 1, 5, 2, 8, 5, 1, 9 };
        sort(c);
        if (!isSorted(c)) {
            throw new RuntimeException("TEST FAILED: Top-down duplicates test failed.");
        }
        System.out.print("Test 3 (Duplicates): ");
        printArray(c);

        // Test 4: String array
        String[] words = { "mergesort", "algorithm", "data", "structures", "uet" };
        sort(words);
        if (!isSorted(words)) {
            throw new RuntimeException("TEST FAILED: Top-down string array test failed.");
        }
        System.out.print("Test 4 (Strings): ");
        printArray(words);


        System.out.println("\n==================================================");
        System.out.println("   TESTING BOTTOM-UP MERGESORT (MergeBU)");
        System.out.println("==================================================");

        // Test BU 1: Integer array with odd length (important for Math.min boundary check)
        Integer[] bu1 = { 38, 27, 43, 3, 9, 82, 10 };
        sortBottomUp(bu1);
        if (!isSorted(bu1)) {
            throw new RuntimeException("TEST FAILED: Bottom-up test 1 failed.");
        }
        System.out.print("BU Test 1 (Random ints, odd length): ");
        printArray(bu1);

        // Test BU 2: Reverse sorted array (power of 2 length)
        Integer[] bu2 = { 8, 7, 6, 5, 4, 3, 2, 1 };
        sortBU(bu2);
        if (!isSorted(bu2)) {
            throw new RuntimeException("TEST FAILED: Bottom-up test 2 failed.");
        }
        System.out.print("BU Test 2 (Reverse sorted, power of 2): ");
        printArray(bu2);

        // Test BU 3: Duplicates and odd length
        Integer[] bu3 = { 5, 1, 5, 2, 8, 5, 1, 9, 3 };
        MergeBU.sort(bu3);
        if (!isSorted(bu3)) {
            throw new RuntimeException("TEST FAILED: Bottom-up test 3 failed.");
        }
        System.out.print("BU Test 3 (Duplicates, MergeBU.sort): ");
        printArray(bu3);

        // Test BU 4: Strings
        String[] buWords = { "bottom", "up", "mergesort", "algorithms", "fourth", "edition" };
        sortBottomUp(buWords);
        if (!isSorted(buWords)) {
            throw new RuntimeException("TEST FAILED: Bottom-up test 4 failed.");
        }
        System.out.print("BU Test 4 (Strings): ");
        printArray(buWords);

        // Test BU 5: Edge cases (single element, empty array)
        Integer[] single = { 42 };
        sortBottomUp(single);
        if (!isSorted(single) || single[0] != 42) {
            throw new RuntimeException("TEST FAILED: Single element test failed.");
        }

        Integer[] empty = {};
        sortBottomUp(empty);
        if (!isSorted(empty)) {
            throw new RuntimeException("TEST FAILED: Empty array test failed.");
        }
        System.out.println("BU Test 5 (Edge cases: single element & empty): PASSED");

        System.out.println("\nAll Top-Down and Bottom-Up MergeSort tests passed successfully!");
    }
}


// Package-private wrapper class for direct access to MergeBU within the package
class MergeBU {
    public static void sort(Comparable[] a) {
        MergeSort.sortBottomUp(a);
    }
}
