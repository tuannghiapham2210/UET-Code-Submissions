//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Data Structures and Algorithms - Chapter 3.1: Elementary Symbol Tables
 * Reference: Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 *
 * This file provides two fundamental elementary implementations of Symbol Tables (ST):
 *
 * 1. SequentialSearchST<Key, Value>:
 *    - Unordered linked list implementation.
 *    - Search: scan through all keys until found -> O(N).
 *    - Insert: scan through all keys; if not found, prepend to front -> O(N).
 *    - Uses equals() to compare keys.
 *
 * 2. BinarySearchST<Key extends Comparable<Key>, Value>:
 *    - Ordered array implementation with parallel resizing arrays (keys[] and vals[]).
 *    - Search: binary search (rank helper) -> O(log N).
 *    - Insert: binary search to find position, then shift greater elements right -> O(N).
 *    - Full Ordered Symbol Table API support:
 *      min(), max(), floor(), ceiling(), rank(), select(), deleteMin(), deleteMax(), keys(lo, hi).
 *    - Uses compareTo() to maintain keys in sorted order.
 */
public class ElementarySymbolTable {

    // =========================================================================
    // 1. UNORDERED LINKED LIST IMPLEMENTATION (SequentialSearchST)
    // =========================================================================
    /**
     * Symbol Table implementation with an unordered singly linked list.
     * Corresponds to textbook Section 3.1 (Sequential search in an unordered list).
     *
     * Worst-case performance:
     * - Search (get)     : O(N)
     * - Insert (put)     : O(N)
     * - Delete (delete)  : O(N)
     * Average-case performance:
     * - Search hit       : ~ N / 2
     * - Insert           : ~ N
     * Key interface: equals()
     */
    public static class SequentialSearchST<Key, Value> implements Iterable<Key> {
        private int n;       // number of key-value pairs
        private Node<Key, Value> first;  // head of the linked list

        // Helper linked list node data type
        private static class Node<Key, Value> {
            Key key;
            Value val;
            Node<Key, Value> next;

            public Node(Key key, Value val, Node<Key, Value> next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        /**
         * Initializes an empty symbol table.
         */
        public SequentialSearchST() {
            this.first = null;
            this.n = 0;
        }

        /**
         * Returns the number of key-value pairs in this symbol table.
         *
         * @return the number of key-value pairs
         */
        public int size() {
            return n;
        }

        /**
         * Returns true if this symbol table is empty.
         *
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * Returns true if this symbol table contains the specified key.
         *
         * @param key the key to check
         * @return true if key is present, false otherwise
         * @throws IllegalArgumentException if key is null
         */
        public boolean contains(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to contains() is null");
            }
            return get(key) != null;
        }

        /**
         * Returns the value associated with the given key.
         *
         * @param key the key
         * @return value associated with key, or null if key is not found
         * @throws IllegalArgumentException if key is null
         */
        public Value get(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to get() is null");
            }
            for (Node<Key, Value> x = first; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    return x.val; // search hit
                }
            }
            return null; // search miss
        }

        /**
         * Inserts the specified key-value pair into the symbol table.
         * If the key is already present, updates its value.
         * If the value is null, deletes the key from the table.
         *
         * @param key the key
         * @param val the value
         * @throws IllegalArgumentException if key is null
         */
        public void put(Key key, Value val) {
            if (key == null) {
                throw new IllegalArgumentException("first argument to put() is null");
            }
            if (val == null) {
                delete(key);
                return;
            }

            // Search for existing key to overwrite value
            for (Node<Key, Value> x = first; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    x.val = val;
                    return;
                }
            }

            // Key not present: prepend new node to the front of the list
            first = new Node<>(key, val, first);
            n++;
        }

        /**
         * Removes the specified key and its associated value from this symbol table.
         *
         * @param key the key to remove
         * @throws IllegalArgumentException if key is null
         */
        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to delete() is null");
            }
            first = delete(first, key);
        }

        // Recursive helper to remove key from list
        private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
            if (x == null) return null;
            if (key.equals(x.key)) {
                n--;
                return x.next;
            }
            x.next = delete(x.next, key);
            return x;
        }

        /**
         * Returns all keys in the symbol table as an Iterable.
         *
         * @return all keys in the symbol table
         */
        public Iterable<Key> keys() {
            List<Key> list = new ArrayList<>(n);
            for (Node<Key, Value> x = first; x != null; x = x.next) {
                list.add(x.key);
            }
            return list;
        }

        @Override
        public Iterator<Key> iterator() {
            return keys().iterator();
        }
    }


    // =========================================================================
    // 2. ORDERED ARRAY IMPLEMENTATION (BinarySearchST)
    // =========================================================================
    /**
     * Symbol Table implementation using two parallel sorted resizing arrays.
     * Corresponds to textbook Section 3.1 (Binary search in an ordered array).
     *
     * Worst-case performance:
     * - Search (get)     : O(log N)
     * - Insert (put)     : O(N)
     * - Delete (delete)  : O(N)
     * - Min / Max        : O(1)
     * - Floor / Ceiling  : O(log N)
     * - Rank             : O(log N)
     * - Select           : O(1)
     * Key interface: Comparable (compareTo())
     */
    public static class BinarySearchST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
        private static final int INIT_CAPACITY = 2;
        private Key[] keys;
        private Value[] vals;
        private int n = 0; // number of key-value pairs

        /**
         * Initializes an empty symbol table with the given initial capacity.
         *
         * @param capacity the initial capacity
         */
        @SuppressWarnings("unchecked")
        public BinarySearchST(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            vals = (Value[]) new Object[capacity];
        }

        /**
         * Initializes an empty symbol table with default initial capacity.
         */
        public BinarySearchST() {
            this(INIT_CAPACITY);
        }

        // Resizes the underlying arrays
        @SuppressWarnings("unchecked")
        private void resize(int capacity) {
            Key[] tempKeys = (Key[]) new Comparable[capacity];
            Value[] tempVals = (Value[]) new Object[capacity];
            for (int i = 0; i < n; i++) {
                tempKeys[i] = keys[i];
                tempVals[i] = vals[i];
            }
            vals = tempVals;
            keys = tempKeys;
        }

        /**
         * Returns the number of key-value pairs in this symbol table.
         *
         * @return the number of key-value pairs
         */
        public int size() {
            return n;
        }

        /**
         * Returns true if this symbol table is empty.
         *
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * Returns true if this symbol table contains the specified key.
         *
         * @param key the key to search for
         * @return true if key is present, false otherwise
         * @throws IllegalArgumentException if key is null
         */
        public boolean contains(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to contains() is null");
            }
            return get(key) != null;
        }

        /**
         * Returns the value paired with the given key.
         *
         * @param key the key
         * @return value associated with key, or null if key is not found
         * @throws IllegalArgumentException if key is null
         */
        public Value get(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to get() is null");
            }
            if (isEmpty()) return null;

            int i = rank(key);
            if (i < n && keys[i].compareTo(key) == 0) {
                return vals[i];
            }
            return null;
        }

        /**
         * Returns the number of keys strictly less than the given key.
         * Core binary search routine.
         *
         * @param key the key
         * @return number of keys in the table strictly less than key
         * @throws IllegalArgumentException if key is null
         */
        public int rank(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to rank() is null");
            }

            int lo = 0, hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) {
                    hi = mid - 1;
                } else if (cmp > 0) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
            return lo;
        }

        /**
         * Inserts the specified key-value pair into the symbol table.
         * If key is already present, updates its value.
         * If value is null, deletes key.
         *
         * @param key the key
         * @param val the value
         * @throws IllegalArgumentException if key is null
         */
        public void put(Key key, Value val) {
            if (key == null) {
                throw new IllegalArgumentException("first argument to put() is null");
            }
            if (val == null) {
                delete(key);
                return;
            }

            int i = rank(key);

            // Key is already present in table
            if (i < n && keys[i].compareTo(key) == 0) {
                vals[i] = val;
                return;
            }

            // Double size of array if full
            if (n == keys.length) {
                resize(2 * keys.length);
            }

            // Shift all greater keys over to make room
            for (int j = n; j > i; j--) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }
            keys[i] = key;
            vals[i] = val;
            n++;
        }

        /**
         * Removes the specified key and its associated value from this symbol table.
         *
         * @param key the key to remove
         * @throws IllegalArgumentException if key is null
         */
        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to delete() is null");
            }
            if (isEmpty()) return;

            int i = rank(key);

            // Key not present
            if (i == n || keys[i].compareTo(key) != 0) {
                return;
            }

            // Shift elements to the left
            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }

            n--;
            keys[n] = null; // Prevent loitering
            vals[n] = null;

            // Halve size of array if 1/4 full
            if (n > 0 && n == keys.length / 4) {
                resize(keys.length / 2);
            }
        }

        /**
         * Returns the smallest key in this symbol table.
         *
         * @return the smallest key
         * @throws NoSuchElementException if symbol table is empty
         */
        public Key min() {
            if (isEmpty()) {
                throw new NoSuchElementException("called min() with empty symbol table");
            }
            return keys[0];
        }

        /**
         * Returns the largest key in this symbol table.
         *
         * @return the largest key
         * @throws NoSuchElementException if symbol table is empty
         */
        public Key max() {
            if (isEmpty()) {
                throw new NoSuchElementException("called max() with empty symbol table");
            }
            return keys[n - 1];
        }

        /**
         * Removes the smallest key and associated value from the symbol table.
         *
         * @throws NoSuchElementException if symbol table is empty
         */
        public void deleteMin() {
            if (isEmpty()) {
                throw new NoSuchElementException("Symbol table underflow");
            }
            delete(min());
        }

        /**
         * Removes the largest key and associated value from the symbol table.
         *
         * @throws NoSuchElementException if symbol table is empty
         */
        public void deleteMax() {
            if (isEmpty()) {
                throw new NoSuchElementException("Symbol table underflow");
            }
            delete(max());
        }

        /**
         * Returns the key of rank k (the k-th smallest key).
         *
         * @param k the rank
         * @return the key of rank k
         * @throws IllegalArgumentException if k is not between 0 and n-1
         */
        public Key select(int k) {
            if (k < 0 || k >= n) {
                throw new IllegalArgumentException("called select() with invalid argument: " + k);
            }
            return keys[k];
        }

        /**
         * Returns the largest key less than or equal to key.
         *
         * @param key the query key
         * @return largest key <= key, or null if no such key
         * @throws IllegalArgumentException if key is null
         */
        public Key floor(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to floor() is null");
            }
            int i = rank(key);
            if (i < n && key.compareTo(keys[i]) == 0) {
                return keys[i];
            }
            if (i == 0) {
                return null;
            }
            return keys[i - 1];
        }

        /**
         * Returns the smallest key greater than or equal to key.
         *
         * @param key the query key
         * @return smallest key >= key, or null if no such key
         * @throws IllegalArgumentException if key is null
         */
        public Key ceiling(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to ceiling() is null");
            }
            int i = rank(key);
            if (i == n) {
                return null;
            }
            return keys[i];
        }

        /**
         * Returns the number of keys in the range [lo, hi].
         *
         * @param lo minimum endpoint
         * @param hi maximum endpoint
         * @return number of keys between lo and hi
         * @throws IllegalArgumentException if lo or hi is null
         */
        public int size(Key lo, Key hi) {
            if (lo == null || hi == null) {
                throw new IllegalArgumentException("arguments to size() are null");
            }
            if (lo.compareTo(hi) > 0) return 0;
            if (contains(hi)) {
                return rank(hi) - rank(lo) + 1;
            } else {
                return rank(hi) - rank(lo);
            }
        }

        /**
         * Returns all keys in the symbol table in sorted order.
         *
         * @return all keys in ascending order
         */
        public Iterable<Key> keys() {
            if (isEmpty()) return new ArrayList<>();
            return keys(min(), max());
        }

        /**
         * Returns all keys in the given range [lo, hi] in sorted order.
         *
         * @param lo minimum endpoint
         * @param hi maximum endpoint
         * @return all keys between lo and hi in ascending order
         * @throws IllegalArgumentException if lo or hi is null
         */
        public Iterable<Key> keys(Key lo, Key hi) {
            if (lo == null || hi == null) {
                throw new IllegalArgumentException("arguments to keys() are null");
            }
            List<Key> list = new ArrayList<>();
            if (lo.compareTo(hi) > 0) return list;
            for (int i = rank(lo); i < rank(hi); i++) {
                list.add(keys[i]);
            }
            if (contains(hi)) {
                list.add(keys[rank(hi)]);
            }
            return list;
        }

        @Override
        public Iterator<Key> iterator() {
            return keys().iterator();
        }
    }


    // =========================================================================
    // 3. MAIN DEMO & TESTING
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("   DEMO 1: UNORDERED LINKED LIST (SequentialSearchST)");
        System.out.println("===============================================================");

        SequentialSearchST<String, Integer> stList = new SequentialSearchST<>();

        // Standard trace sequence from Slide 13:
        // S E A R C H E X A M P L E
        // 0 1 2 3 4 5 6 7 8 9 10 11 12
        String[] traceKeys = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        int[] traceVals = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        System.out.println("Inserting trace sequence into SequentialSearchST:");
        for (int i = 0; i < traceKeys.length; i++) {
            stList.put(traceKeys[i], traceVals[i]);
            System.out.printf("put(%s, %2d) -> current size: %d%n", traceKeys[i], traceVals[i], stList.size());
        }

        System.out.println("\nAll keys and values in SequentialSearchST (iteration order):");
        for (String s : stList.keys()) {
            System.out.println(s + " " + stList.get(s));
        }

        System.out.println("\nTesting operations on SequentialSearchST:");
        System.out.println("get('E')      -> " + stList.get("E")); // Should be 12
        System.out.println("get('A')      -> " + stList.get("A")); // Should be 8
        System.out.println("contains('X') -> " + stList.contains("X")); // true
        System.out.println("contains('Z') -> " + stList.contains("Z")); // false

        System.out.println("Deleting 'E'...");
        stList.delete("E");
        System.out.println("contains('E') -> " + stList.contains("E")); // false
        System.out.println("size after delete: " + stList.size());

        System.out.println("\n===============================================================");
        System.out.println("   DEMO 2: ORDERED ARRAY (BinarySearchST)");
        System.out.println("===============================================================");

        BinarySearchST<String, Integer> stArray = new BinarySearchST<>();

        System.out.println("Inserting same trace sequence into BinarySearchST:");
        for (int i = 0; i < traceKeys.length; i++) {
            stArray.put(traceKeys[i], traceVals[i]);
        }

        System.out.println("\nAll keys and values in BinarySearchST (sorted order):");
        for (String s : stArray.keys()) {
            System.out.println(s + " " + stArray.get(s));
        }

        System.out.println("\nTesting Ordered Operations on BinarySearchST:");
        System.out.println("min()                 -> " + stArray.min());
        System.out.println("max()                 -> " + stArray.max());
        System.out.println("floor('G')            -> " + stArray.floor("G"));
        System.out.println("ceiling('G')          -> " + stArray.ceiling("G"));
        System.out.println("floor('M')            -> " + stArray.floor("M"));
        System.out.println("ceiling('M')          -> " + stArray.ceiling("M"));
        System.out.println("rank('P')             -> " + stArray.rank("P"));
        System.out.println("select(6)             -> " + stArray.select(6));
        System.out.println("size('C', 'R')        -> " + stArray.size("C", "R"));

        System.out.print("keys('C', 'R')        -> ");
        for (String s : stArray.keys("C", "R")) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("\nTesting deleteMin() and deleteMax():");
        stArray.deleteMin();
        stArray.deleteMax();
        System.out.println("New min after deleteMin: " + stArray.min());
        System.out.println("New max after deleteMax: " + stArray.max());
        System.out.println("Remaining size: " + stArray.size());
    }
}

// Package-private convenience classes for direct instantiation
class SequentialSearchST<Key, Value> extends ElementarySymbolTable.SequentialSearchST<Key, Value> {
    public SequentialSearchST() {
        super();
    }
}

class BinarySearchST<Key extends Comparable<Key>, Value> extends ElementarySymbolTable.BinarySearchST<Key, Value> {
    public BinarySearchST(int capacity) {
        super(capacity);
    }

    public BinarySearchST() {
        super();
    }
}
