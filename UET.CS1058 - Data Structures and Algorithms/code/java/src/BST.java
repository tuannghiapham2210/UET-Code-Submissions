//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Data Structures and Algorithms - Chapter 3.2: Binary Search Trees (BST)
 * Reference: Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 *
 * Definition: A Binary Search Tree (BST) is a binary tree in symmetric order.
 * - Symmetric order: Each node has a key, and every node's key is:
 *   + Larger than all keys in its left subtree.
 *   + Smaller than all keys in its right subtree.
 *
 * Key Operations:
 * 1. Search (get)     : O(h) where h is the tree height (~ 1.39 lg N average, N worst case)
 * 2. Insert (put)     : O(h)
 * 3. Min / Max        : O(h)
 * 4. Floor / Ceiling  : O(h)
 * 5. Rank / Select    : O(h) (requires storing subtree counts)
 * 6. Deletion         : Hibbard deletion algorithm
 * 7. Inorder Traversal: yields keys in sorted order in O(N) time
 *
 * @param <Key>   the generic key type (must implement Comparable<Key>)
 * @param <Value> the generic value type
 */
public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    // =========================================================================
    // INNER NODE CLASS & FIELDS (Slide 6 & 23)
    // =========================================================================

    private Node root; // reference to the root node of the BST

    /**
     * Node representation in a Binary Search Tree (Slide 6 & 23).
     * Composed of:
     * - key   : the comparable search key
     * - val   : the associated data/value
     * - left  : link to left subtree (keys strictly smaller than this.key)
     * - right : link to right subtree (keys strictly larger than this.key)
     * - count : number of nodes in the subtree rooted at this node (for size, rank, select)
     */
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count; // number of nodes in subtree rooted at this node

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    /**
     * Initializes an empty Binary Search Tree.
     */
    public BST() {
        this.root = null;
    }


    // =========================================================================
    // 1. BASIC SYMBOL TABLE OPERATIONS: size, isEmpty, contains, get, put
    // =========================================================================

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if size is 0, {@code false} otherwise
     */
    public boolean isEmpty() {
        // TODO: Implement isEmpty()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the total number of key-value pairs in this symbol table (Slide 23).
     *
     * @return the number of key-value pairs
     */
    public int size() {
        // TODO: Implement size()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Helper method to return the subtree count of node x (Slide 23).
     * Safe to call when x is null (returns 0).
     *
     * @param x the root node of the subtree
     * @return number of nodes in subtree rooted at x, or 0 if x == null
     */
    private int size(Node x) {
        // TODO: Implement size(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Checks whether the symbol table contains the given key.
     *
     * @param key the key to check
     * @return {@code true} if key is present, {@code false} otherwise
     * @throws IllegalArgumentException if key is null
     */
    public boolean contains(Key key) {
        // TODO: Implement contains(Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the value associated with the given key (Slide 8).
     *
     * Search algorithm:
     * - Start at root.
     * - If key < current node's key: go left.
     * - If key > current node's key: go right.
     * - If key == current node's key: search hit -> return value.
     * - If reach null: search miss -> return null.
     *
     * @param key the search key
     * @return value paired with key, or null if not found
     * @throws IllegalArgumentException if key is null
     */
    public Value get(Key key) {
        // TODO: Implement get(Key key) (can be iterative or recursive)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Inserts the specified key-value pair into the BST (Slide 9, 10, 23).
     * - If value is null, deletes the key from the BST.
     * - If key already exists, updates its value.
     * - If key does not exist, creates a new node and updates subtree counts.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if key is null
     */
    public void put(Key key, Value val) {
        // TODO: Implement put(Key key, Value val)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for put (Slide 10 & 23).
     *
     * Cases:
     * - If x == null: return new Node(key, val, 1).
     * - If key < x.key: x.left = put(x.left, key, val).
     * - If key > x.key: x.right = put(x.right, key, val).
     * - If key == x.key: x.val = val.
     * - Update count: x.count = 1 + size(x.left) + size(x.right).
     *
     * @param x   the subtree root
     * @param key the key
     * @param val the value
     * @return the updated subtree root
     */
    private Node put(Node x, Key key, Value val) {
        // TODO: Implement put(Node x, Key key, Value val)
        throw new UnsupportedOperationException("Not implemented yet");
    }


    // =========================================================================
    // 2. ORDERED OPERATIONS: min, max, floor, ceiling (Slide 18 - 21)
    // =========================================================================

    /**
     * Returns the smallest key in the BST (Slide 18).
     *
     * @return the smallest key
     * @throws NoSuchElementException if the BST is empty
     */
    public Key min() {
        // TODO: Implement min()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper to find node with minimum key in subtree x (Slide 18).
     * Rule: Go left until reaching a node with a null left link.
     *
     * @param x the subtree root
     * @return node containing the minimum key
     */
    private Node min(Node x) {
        // TODO: Implement min(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the largest key in the BST (Slide 18).
     *
     * @return the largest key
     * @throws NoSuchElementException if the BST is empty
     */
    public Key max() {
        // TODO: Implement max()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper to find node with maximum key in subtree x (Slide 18).
     * Rule: Go right until reaching a node with a null right link.
     *
     * @param x the subtree root
     * @return node containing the maximum key
     */
    private Node max(Node x) {
        // TODO: Implement max(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the largest key less than or equal to the given key (Slide 19 - 21).
     *
     * @param key the query key
     * @return largest key <= key, or null if no such key
     * @throws IllegalArgumentException if key is null
     */
    public Key floor(Key key) {
        // TODO: Implement floor(Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for floor (Slide 20 - 21).
     *
     * Case 1: [k equals x.key]     -> return x
     * Case 2: [k is less than x.key] -> floor must be in left subtree -> floor(x.left, key)
     * Case 3: [k is greater than x.key] -> floor could be in right subtree:
     *         Search right subtree. If found, return it; otherwise return x.
     *
     * @param x   the subtree root
     * @param key the query key
     * @return node containing floor key, or null
     */
    private Node floor(Node x, Key key) {
        // TODO: Implement floor(Node x, Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the smallest key greater than or equal to the given key (Slide 19).
     *
     * @param key the query key
     * @return smallest key >= key, or null if no such key
     * @throws IllegalArgumentException if key is null
     */
    public Key ceiling(Key key) {
        // TODO: Implement ceiling(Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for ceiling (symmetric to floor).
     *
     * @param x   the subtree root
     * @param key the query key
     * @return node containing ceiling key, or null
     */
    private Node ceiling(Node x, Key key) {
        // TODO: Implement ceiling(Node x, Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }


    // =========================================================================
    // 3. RANK & SELECT: rank, select (Slide 22 - 24)
    // =========================================================================

    /**
     * Returns the number of keys strictly less than the given key (Slide 24).
     *
     * @param key the query key
     * @return number of keys in the tree strictly less than key
     * @throws IllegalArgumentException if key is null
     */
    public int rank(Key key) {
        // TODO: Implement rank(Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for rank (Slide 24).
     *
     * 3 cases:
     * - If key < x.key: rank(key, x.left)
     * - If key > x.key: 1 + size(x.left) + rank(key, x.right)
     * - If key == x.key: size(x.left)
     *
     * @param key the query key
     * @param x   the subtree root
     * @return number of keys in subtree x strictly less than key
     */
    private int rank(Key key, Node x) {
        // TODO: Implement rank(Key key, Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the key of rank k (the k-th smallest key, 0-indexed) (Slide 22).
     *
     * @param k the rank (0 <= k < size())
     * @return the key of rank k
     * @throws IllegalArgumentException if k is out of bounds
     */
    public Key select(int k) {
        // TODO: Implement select(int k)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for select (Slide 22).
     *
     * Let t = size(x.left):
     * - If t > k: key must be in left subtree -> select(x.left, k)
     * - If t < k: key must be in right subtree -> select(x.right, k - t - 1)
     * - If t == k: x is the k-th smallest node -> return x
     *
     * @param x the subtree root
     * @param k the rank to select
     * @return node of rank k
     */
    private Node select(Node x, int k) {
        // TODO: Implement select(Node x, int k)
        throw new UnsupportedOperationException("Not implemented yet");
    }


    // =========================================================================
    // 4. DELETION: deleteMin, deleteMax, Hibbard deletion (Slide 30 - 34)
    // =========================================================================

    /**
     * Removes the smallest key and associated value from the BST (Slide 30).
     *
     * @throws NoSuchElementException if the BST is empty
     */
    public void deleteMin() {
        // TODO: Implement deleteMin()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for deleteMin (Slide 30).
     *
     * Rule:
     * - Go left until finding a node with a null left link.
     * - Replace that node by its right link: return x.right.
     * - Update subtree counts on the way back up.
     *
     * @param x the subtree root
     * @return updated subtree root
     */
    private Node deleteMin(Node x) {
        // TODO: Implement deleteMin(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Removes the largest key and associated value from the BST.
     *
     * @throws NoSuchElementException if the BST is empty
     */
    public void deleteMax() {
        // TODO: Implement deleteMax()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for deleteMax (symmetric to deleteMin).
     *
     * @param x the subtree root
     * @return updated subtree root
     */
    private Node deleteMax(Node x) {
        // TODO: Implement deleteMax(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Removes the specified key and its associated value from this BST (Slide 31 - 34).
     * Uses Hibbard Deletion:
     * - Case 0 (0 children): Replace node with null (Slide 31).
     * - Case 1 (1 child)   : Replace node with its non-null child (Slide 32).
     * - Case 2 (2 children): Replace node with its successor (min of right subtree) (Slide 33 & 34).
     *
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     */
    public void delete(Key key) {
        // TODO: Implement delete(Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for Hibbard deletion (Slide 34).
     *
     * Steps when key == x.key:
     * 1. If x.right == null, return x.left (Case 0 & 1).
     * 2. If x.left == null, return x.right (Case 1).
     * 3. Case 2 (2 children):
     *    Node t = x;
     *    x = min(t.right);
     *    x.right = deleteMin(t.right);
     *    x.left = t.left;
     * 4. Update count: x.count = 1 + size(x.left) + size(x.right).
     * 5. Return x.
     *
     * @param x   the subtree root
     * @param key the key to delete
     * @return updated subtree root
     */
    private Node delete(Node x, Key key) {
        // TODO: Implement delete(Node x, Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }


    // =========================================================================
    // 5. RANGE SEARCH & ITERATION: keys, size(lo, hi), iterator (Slide 25)
    // =========================================================================

    /**
     * Returns the number of keys in the range [lo, hi].
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return number of keys in [lo, hi]
     * @throws IllegalArgumentException if lo or hi is null
     */
    public int size(Key lo, Key hi) {
        // TODO: Implement size(Key lo, Key hi)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns all keys in the tree in ascending order (Slide 25).
     * Property: Inorder traversal of a BST yields keys in ascending order.
     *
     * @return all keys in sorted order
     */
    public Iterable<Key> keys() {
        // TODO: Implement keys()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns all keys in the given range [lo, hi] in ascending order.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys between lo and hi in sorted order
     * @throws IllegalArgumentException if lo or hi is null
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        // TODO: Implement keys(Key lo, Key hi)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Inorder traversal helper for range query (Slide 25).
     *
     * Traversal logic:
     * - Traverse left subtree if lo could be in left subtree.
     * - Enqueue key if it falls within [lo, hi].
     * - Traverse right subtree if hi could be in right subtree.
     *
     * @param x     the subtree root
     * @param list  the list collecting keys
     * @param lo    minimum endpoint
     * @param hi    maximum endpoint
     */
    private void keys(Node x, List<Key> list, Key lo, Key hi) {
        // TODO: Implement keys(Node x, List<Key> list, Key lo, Key hi)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns an iterator that iterates over the keys in sorted order.
     *
     * @return an iterator over all keys in sorted order
     */
    @Override
    public Iterator<Key> iterator() {
        // TODO: Implement iterator()
        throw new UnsupportedOperationException("Not implemented yet");
    }


    // =========================================================================
    // 6. TREE METRICS: height (Slide 11 & 15)
    // =========================================================================

    /**
     * Returns the height of the BST (number of edges on the longest path from root to leaf).
     * A tree with 1 node has height 0; an empty tree has height -1.
     *
     * @return height of the tree
     */
    public int height() {
        // TODO: Implement height()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Helper to compute height of subtree x.
     *
     * @param x the subtree root
     * @return height of subtree x, or -1 if x is null
     */
    private int height(Node x) {
        // TODO: Implement height(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }


    // =========================================================================
    // 7. MAIN METHOD (Test harness for verifying your implementation)
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("BST Template Code - Ready for implementation!");
        System.out.println("Run this class after completing your TODOs to test operations.");

        /*
         * UNCOMMENT THE TEST CODE BELOW ONCE YOU HAVE IMPLEMENTED THE METHODS:
         *
        BST<String, Integer> bst = new BST<>();

        // Test insertion sequence from Slide 4-10:
        // S E A R C H E X A M P L E
        String[] keys = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        int[] vals    = { 0 ,  1 ,  2 ,  3 ,  4 ,  5 ,  6 ,  7 ,  8 ,  9 , 10 , 11 , 12 };

        System.out.println("\n--- 1. Testing put() and size() ---");
        for (int i = 0; i < keys.length; i++) {
            bst.put(keys[i], vals[i]);
        }
        System.out.println("Total distinct keys inserted (size): " + bst.size()); // expected: 10
        System.out.println("Height of tree: " + bst.height());

        System.out.println("\n--- 2. Testing keys() (Inorder Traversal - Sorted) ---");
        for (String s : bst.keys()) {
            System.out.println(s + " " + bst.get(s));
        }

        System.out.println("\n--- 3. Testing get() and contains() ---");
        System.out.println("get('E'): " + bst.get("E")); // expected: 12
        System.out.println("get('A'): " + bst.get("A")); // expected: 8
        System.out.println("contains('R'): " + bst.contains("R")); // true
        System.out.println("contains('Z'): " + bst.contains("Z")); // false

        System.out.println("\n--- 4. Testing min() and max() ---");
        System.out.println("min: " + bst.min()); // expected: A
        System.out.println("max: " + bst.max()); // expected: X

        System.out.println("\n--- 5. Testing floor() and ceiling() ---");
        System.out.println("floor('G'): " + bst.floor("G"));     // expected: E
        System.out.println("ceiling('G'): " + bst.ceiling("G")); // expected: H
        System.out.println("floor('D'): " + bst.floor("D"));     // expected: C
        System.out.println("ceiling('Q'): " + bst.ceiling("Q")); // expected: R

        System.out.println("\n--- 6. Testing rank() and select() ---");
        System.out.println("rank('A'): " + bst.rank("A")); // expected: 0
        System.out.println("rank('P'): " + bst.rank("P")); // expected: 6
        System.out.println("select(6): " + bst.select(6)); // expected: P

        System.out.println("\n--- 7. Testing range size and keys ---");
        System.out.println("size('C', 'R'): " + bst.size("C", "R")); // expected: 7
        System.out.print("keys('C', 'R'): ");
        for (String s : bst.keys("C", "R")) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("\n--- 8. Testing deleteMin() and deleteMax() ---");
        bst.deleteMin(); // removes 'A'
        bst.deleteMax(); // removes 'X'
        System.out.println("New min: " + bst.min()); // expected: C
        System.out.println("New max: " + bst.max()); // expected: S
        System.out.println("Size after deleteMin/Max: " + bst.size()); // expected: 8

        System.out.println("\n--- 9. Testing Hibbard deletion: delete('E') ---");
        bst.delete("E");
        System.out.println("contains('E'): " + bst.contains("E")); // false
        System.out.println("Size after delete('E'): " + bst.size()); // expected: 7
        System.out.print("Remaining keys: ");
        for (String s : bst.keys()) {
            System.out.print(s + " ");
        }
        System.out.println();
        */
    }
}
