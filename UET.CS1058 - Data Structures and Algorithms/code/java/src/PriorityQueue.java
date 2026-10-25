//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

import java.util.NoSuchElementException;

public class PriorityQueue {

    // =========================================================================
    // 1. UNORDERED ARRAY IMPLEMENTATION
    // =========================================================================
    /**
     * Priority Queue implementation using an unordered array.
     * - insert operation: O(1) - append to the end of the array.
     * - delMax operation: O(N) - scan through the array to find the maximum element,
     *   exchange it with the last element, then remove it.
     */
    public static class UnorderedMaxPQ<Key extends Comparable<Key>> {
        private Key[] pq; // pq[i] = ith element on pq
        private int N;    // number of elements on pq

        // Initializes an empty priority queue with the given initial capacity
        @SuppressWarnings("unchecked")
        public UnorderedMaxPQ(int capacity) {
            pq = (Key[]) new Comparable[capacity];
            N = 0;
        }

        // Initializes an empty priority queue with default capacity 1
        public UnorderedMaxPQ() {
            this(1);
        }

        // Returns true if this priority queue is empty, false otherwise
        public boolean isEmpty() {
            return N == 0;
        }

        // Returns the number of keys on this priority queue
        public int size() {
            return N;
        }

        // Adds a new key to this priority queue - O(1)
        public void insert(Key x) {
            if (x == null) {
                throw new IllegalArgumentException("Cannot insert null key");
            }
            if (N == pq.length) {
                resize(2 * pq.length);
            }
            pq[N++] = x;
        }

        // Removes and returns a largest key on this priority queue - O(N)
        public Key delMax() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue underflow");
            }

            int max = 0;
            for (int i = 1; i < N; i++) {
                if (less(max, i)) {
                    max = i;
                }
            }

            exch(max, N - 1);
            Key item = pq[--N];
            pq[N] = null; // null out entry to prevent loitering

            // Halve the array size if number of elements drops to 1/4 of capacity
            if (N > 0 && N == pq.length / 4) {
                resize(pq.length / 2);
            }

            return item;
        }

        // Returns a largest key on this priority queue without removing it
        public Key max() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue underflow");
            }
            int max = 0;
            for (int i = 1; i < N; i++) {
                if (less(max, i)) {
                    max = i;
                }
            }
            return pq[max];
        }

        // Helper function: returns whether key at index i is less than key at index j
        private boolean less(int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }

        // Helper function: exchanges keys at indices i and j
        private void exch(int i, int j) {
            Key t = pq[i];
            pq[i] = pq[j];
            pq[j] = t;
        }

        // Helper function: resizes the underlying array to the given capacity
        @SuppressWarnings("unchecked")
        private void resize(int capacity) {
            Key[] temp = (Key[]) new Comparable[capacity];
            for (int i = 0; i < N; i++) {
                temp[i] = pq[i];
            }
            pq = temp;
        }
    }

    // =========================================================================
    // 2. BINARY HEAP IMPLEMENTATION
    // =========================================================================
    /**
     * Priority Queue implementation using a max binary heap (complete binary tree).
     * - 1-based indexing in array pq[1..N]:
     *   + Parent of node at index k is at k / 2
     *   + Children of node at index k are at 2 * k and 2 * k + 1
     * - insert operation: O(log N) - append at index N + 1, then swim up.
     * - delMax operation: O(log N) - exchange root (pq[1]) with the last element (pq[N]),
     *   remove the last element, then sink down the new root.
     */
    public static class MaxPQ<Key extends Comparable<Key>> {
        private Key[] pq; // store items at indices 1 to N
        private int N;    // number of items on priority queue

        // Initializes an empty priority queue with the given initial capacity
        @SuppressWarnings("unchecked")
        public MaxPQ(int capacity) {
            pq = (Key[]) new Comparable[capacity + 1];
            N = 0;
        }

        // Initializes an empty priority queue with default capacity 1
        public MaxPQ() {
            this(1);
        }

        // Initializes a priority queue from an array of keys using bottom-up heapify - O(N)
        @SuppressWarnings("unchecked")
        public MaxPQ(Key[] keys) {
            N = keys.length;
            pq = (Key[]) new Comparable[keys.length + 1];
            for (int i = 0; i < N; i++) {
                pq[i + 1] = keys[i];
            }
            // Bottom-up heap construction: O(N)
            for (int k = N / 2; k >= 1; k--) {
                sink(k);
            }
        }

        // Returns true if this priority queue is empty, false otherwise
        public boolean isEmpty() {
            return N == 0;
        }

        // Returns the number of keys on this priority queue
        public int size() {
            return N;
        }

        // Adds a new key to this priority queue - O(log N)
        public void insert(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Cannot insert null key");
            }

            // Double the size of the array if heap is full
            if (N == pq.length - 1) {
                resize(2 * pq.length);
            }

            pq[++N] = key;
            swim(N);
        }

        // Removes and returns a largest key on this priority queue - O(log N)
        public Key delMax() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue underflow");
            }

            Key max = pq[1];
            exch(1, N--);
            sink(1);
            pq[N + 1] = null; // null out entry to prevent loitering

            // Halve the array size if number of elements drops to 1/4 of capacity
            if (N > 0 && N == (pq.length - 1) / 4) {
                resize(pq.length / 2);
            }

            return max;
        }

        // Returns a largest key on this priority queue without removing it
        public Key max() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue underflow");
            }
            return pq[1];
        }

        // Promotion in a heap (swim): restores heap order when child becomes larger than parent
        private void swim(int k) {
            while (k > 1 && less(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        // Demotion in a heap (sink): restores heap order when parent becomes smaller than larger child
        private void sink(int k) {
            while (2 * k <= N) {
                int j = 2 * k; // left child
                if (j < N && less(j, j + 1)) {
                    j++; // choose the larger of the two children
                }
                if (!less(k, j)) {
                    break; // parent is not smaller than larger child, heap order restored
                }
                exch(k, j);
                k = j;
            }
        }

        // Helper function: returns whether key at index i is less than key at index j
        private boolean less(int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }

        // Helper function: exchanges keys at indices i and j
        private void exch(int i, int j) {
            Key t = pq[i];
            pq[i] = pq[j];
            pq[j] = t;
        }

        // Helper function: resizes the underlying array to the given capacity
        @SuppressWarnings("unchecked")
        private void resize(int capacity) {
            Key[] temp = (Key[]) new Comparable[capacity];
            for (int i = 1; i <= N; i++) {
                temp[i] = pq[i];
            }
            pq = temp;
        }
    }

    // =========================================================================
    // MAIN METHOD: DEMO & TESTING
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   DEMO 1: UNORDERED ARRAY IMPLEMENTATION");
        System.out.println("==================================================");

        UnorderedMaxPQ<String> unorderedPQ = new UnorderedMaxPQ<>(10);
        String[] sampleItems = {"P", "Q", "E", "-", "X", "A", "M", "-", "P", "L", "E", "-"};

        System.out.println("Testing with sequence: P Q E - X A M - P L E - (where '-' means delMax)");
        for (String item : sampleItems) {
            if (item.equals("-")) {
                if (!unorderedPQ.isEmpty()) {
                    System.out.print("delMax -> " + unorderedPQ.delMax() + " | remaining size: " + unorderedPQ.size() + "\n");
                }
            } else {
                unorderedPQ.insert(item);
                System.out.println("insert: " + item + " | current max: " + unorderedPQ.max());
            }
        }

        System.out.print("\nDraining remaining elements from UnorderedMaxPQ: ");
        while (!unorderedPQ.isEmpty()) {
            System.out.print(unorderedPQ.delMax() + " ");
        }
        System.out.println("\n");

        System.out.println("==================================================");
        System.out.println("   DEMO 2: BINARY HEAP IMPLEMENTATION (MaxPQ)");
        System.out.println("==================================================");

        MaxPQ<String> heapPQ = new MaxPQ<>(10);
        System.out.println("Testing with same sequence on Binary Heap MaxPQ:");
        for (String item : sampleItems) {
            if (item.equals("-")) {
                if (!heapPQ.isEmpty()) {
                    System.out.print("delMax -> " + heapPQ.delMax() + " | remaining size: " + heapPQ.size() + "\n");
                }
            } else {
                heapPQ.insert(item);
                System.out.println("insert: " + item + " | current max: " + heapPQ.max());
            }
        }

        System.out.print("\nDraining remaining elements from Binary Heap MaxPQ: ");
        while (!heapPQ.isEmpty()) {
            System.out.print(heapPQ.delMax() + " ");
        }
        System.out.println("\n");

        System.out.println("==================================================");
        System.out.println("   DEMO 3: TESTING WITH INTEGERS");
        System.out.println("==================================================");
        MaxPQ<Integer> intHeap = new MaxPQ<>();
        int[] numbers = {45, 12, 85, 32, 89, 39, 69, 22, 57};
        System.out.print("Inserting numbers: ");
        for (int num : numbers) {
            System.out.print(num + " ");
            intHeap.insert(num);
        }
        System.out.println();

        System.out.print("Extracting in descending order: ");
        while (!intHeap.isEmpty()) {
            System.out.print(intHeap.delMax() + " ");
        }
        System.out.println();
    }
}

// Package-private wrapper classes for direct access to UnorderedMaxPQ and MaxPQ within the package
class UnorderedMaxPQ<Key extends Comparable<Key>> extends PriorityQueue.UnorderedMaxPQ<Key> {
    public UnorderedMaxPQ(int capacity) {
        super(capacity);
    }
    public UnorderedMaxPQ() {
        super();
    }
}

class MaxPQ<Key extends Comparable<Key>> extends PriorityQueue.MaxPQ<Key> {
    public MaxPQ(int capacity) {
        super(capacity);
    }
    public MaxPQ() {
        super();
    }
    public MaxPQ(Key[] keys) {
        super(keys);
    }
}
