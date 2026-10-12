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

    // Recursive helper function for mergesort
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    // Public API to sort an array using mergesort
    public static void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    // Helper to check if array is sorted
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }

    // Main test function
    public static void main(String[] args) {
        // Test 1: Integer array
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

        // Test 3: Duplicates
        Integer[] c = { 5, 1, 5, 2, 8, 5, 1, 9 };
        sort(c);

        if (!isSorted(c)) {
            throw new RuntimeException("TEST FAILED: Duplicates test failed.");
        }

        // Test 4: String array
        String[] words = { "mergesort", "algorithm", "data", "structures", "uet" };
        sort(words);

        if (!isSorted(words)) {
            throw new RuntimeException("TEST FAILED: String array test failed.");
        }

        // SUCCESS
        System.out.println("MergeSort Worked");
    }
}
