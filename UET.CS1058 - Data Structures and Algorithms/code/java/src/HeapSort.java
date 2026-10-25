//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

public class HeapSort {

    /**
     * Rearranges the array in ascending order using heapsort.
     * 
     * Heapsort consists of two phases:
     * 1. Heap construction: build a max-heap from the input array in O(N) time
     *    by sinking elements starting from k = N / 2 down to 1.
     * 2. Sortdown: repeatedly extract the maximum element by exchanging the root
     *    with the last element, decrementing the heap size, and sinking the new root.
     * 
     * Time Complexity: O(N log N) in the best, average, and worst cases.
     * Space Complexity: O(1) in-place sorting.
     * 
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int N = a.length;

        // Phase 1: Build max-heap (bottom-up reheapify)
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        // Phase 2: Sortdown (repeatedly remove max and restore heap order)
        while (N > 1) {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    /**
     * Demotion in a heap (sink).
     * Sinks the element at index k down to restore max-heap order within a[1..N].
     * 
     * @param a the array representing the heap
     * @param k the 1-based index of the node to sink
     * @param N the size of the heap
     */
    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k; // left child index (1-based)

            // Select the larger of the two children
            if (j < N && less(a, j, j + 1)) {
                j++;
            }

            // If parent is not less than the largest child, heap order is restored
            if (!less(a, k, j)) {
                break;
            }

            exch(a, k, j);
            k = j;
        }
    }

    /**
     * Helper function to compare two elements in array a using 1-based indices.
     * Converts 1-based indices (i, j) to 0-based indices (i - 1, j - 1).
     * 
     * @param a the array of Comparable elements
     * @param i 1-based index of first element
     * @param j 1-based index of second element
     * @return true if a[i - 1] < a[j - 1], false otherwise
     */
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    /**
     * Helper function to swap two elements in array a using 1-based indices.
     * Converts 1-based indices (i, j) to 0-based indices (i - 1, j - 1).
     * 
     * @param a the array of elements
     * @param i 1-based index of first element
     * @param j 1-based index of second element
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = swap;
    }

    /**
     * Helper function to compare two Comparable items directly.
     */
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Helper function to verify if an array is sorted in ascending order.
     */
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

    /**
     * Helper function to print elements of an array.
     */
    private static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // =========================================================================
    // MAIN METHOD: COMPREHENSIVE TESTS
    // =========================================================================
    public static void main(String[] args) {
        // Test 1: Standard Integer array
        Integer[] a = { 38, 27, 43, 3, 9, 82, 10 };
        System.out.print("Original array a: ");
        show(a);
        sort(a);
        System.out.print("Sorted array a:   ");
        show(a);
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

        // Test 8: String array (textbook example)
        String[] words = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
        System.out.print("\nOriginal words: ");
        show(words);
        sort(words);
        System.out.print("Sorted words:   ");
        show(words);
        if (!isSorted(words)) {
            throw new RuntimeException("TEST FAILED: String array test failed.");
        }

        // SUCCESS
        System.out.println("\nheapsort works!");
    }
}

// Package-private alias matching Sedgewick's slide class name 'Heap'
class Heap extends HeapSort {
}
