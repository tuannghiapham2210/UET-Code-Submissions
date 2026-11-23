//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Data Structures and Algorithms - Chapter 3.3: Balanced Search Trees
 * (Left-Leaning Red-Black BST)
 * Reference: Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 *
 * Equivalent definition of a Left-Leaning Red-Black (LLRB) BST (Slide 19):
 * A BST such that:
 * 1. No node has two red links connected to it.
 * 2. Every path from root to null link has the same number of black links
 * ("perfect black balance").
 * 3. Red links lean left.
 *
 * Key property: 1-1 correspondence between 2-3 trees and LLRB BSTs.
 * Guaranteed worst-case height: <= 2 * lg N (typical height ~ 1.0 * lg N).
 *
 * @param <Key>   the generic key type (must implement Comparable<Key>)
 * @param <Value> the generic value type
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    // =========================================================================
    // CONSTANTS, FIELDS & INNER NODE CLASS (Slide 22)
    // =========================================================================

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root; // root of the Red-Black BST

    /**
     * Node representation for a Left-Leaning Red-Black BST (Slide 22).
     * Each node is pointed to by precisely one link (from its parent),
     * so we encode the color of the parent link inside the node itself.
     */
    private class Node {
        private Key key; // search key
        private Value val; // associated data
        private Node left, right; // links to left and right subtrees
        private boolean color; // color of link from parent to this node
        private int count; // number of nodes in subtree rooted at this node

        public Node(Key key, Value val, boolean color, int count) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.count = count;
        }
    }

    /**
     * Initializes an empty Red-Black BST.
     */
    public RedBlackTree() {
        this.root = null;
    }

    // =========================================================================
    // 1. NODE HELPER METHODS (Slide 22)
    // =========================================================================

    /**
     * Checks whether the link from parent to node x is red (Slide 22).
     * Convention: null links are black.
     *
     * @param x the node to check
     * @return {@code true} if x is non-null and x.color == RED; {@code false}
     *         otherwise
     */
    private boolean isRed(Node x) {
        // TODO: Implement isRed(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the number of nodes in the subtree rooted at x.
     *
     * @param x the subtree root
     * @return number of nodes in subtree x, or 0 if x is null
     */
    private int size(Node x) {
        // TODO: Implement size(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return total number of key-value pairs
     */
    public int size() {
        // TODO: Implement size()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Checks if this symbol table is empty.
     *
     * @return {@code true} if empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        // TODO: Implement isEmpty()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // 2. ELEMENTARY RED-BLACK BST OPERATIONS (Slide 24 - 29)
    // =========================================================================

    /**
     * Left rotation: Orients a (temporarily) right-leaning red link to lean left
     * (Slide 24 - 25).
     * Precondition: h.right is red.
     * Invariants: Maintains symmetric order and perfect black balance.
     *
     * @param h the root of the subtree to rotate
     * @return the new root of the rotated subtree
     */
    private Node rotateLeft(Node h) {
        // TODO: Implement rotateLeft(Node h)
        // Steps:
        // 1. assert isRed(h.right);
        // 2. Node x = h.right;
        // 3. h.right = x.left;
        // 4. x.left = h;
        // 5. x.color = h.color;
        // 6. h.color = RED;
        // 7. Update subtree counts (x.count and h.count)
        // 8. return x;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Right rotation: Orients a left-leaning red link to (temporarily) lean right
     * (Slide 26 - 27).
     * Precondition: h.left is red.
     * Invariants: Maintains symmetric order and perfect black balance.
     *
     * @param h the root of the subtree to rotate
     * @return the new root of the rotated subtree
     */
    private Node rotateRight(Node h) {
        // TODO: Implement rotateRight(Node h)
        // Steps:
        // 1. assert isRed(h.left);
        // 2. Node x = h.left;
        // 3. h.left = x.right;
        // 4. x.right = h;
        // 5. x.color = h.color;
        // 6. h.color = RED;
        // 7. Update subtree counts (x.count and h.count)
        // 8. return x;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Color flip: Recolors to split a (temporary) 4-node (Slide 28 - 29).
     * Precondition: h is black, and both h.left and h.right are red.
     * Invariants: Maintains symmetric order and perfect black balance.
     *
     * @param h the parent node of the temporary 4-node
     */
    private void flipColors(Node h) {
        // TODO: Implement flipColors(Node h)
        // Steps:
        // 1. assert !isRed(h);
        // 2. assert isRed(h.left);
        // 3. assert isRed(h.right);
        // 4. h.color = RED;
        // 5. h.left.color = BLACK;
        // 6. h.right.color = BLACK;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // 3. SEARCH & INSERTION (Slide 21, 30 - 37)
    // =========================================================================

    /**
     * Returns the value associated with the given key (Slide 21).
     * Observation: Search is identical to an elementary BST (ignore link colors).
     *
     * @param key the search key
     * @return value associated with key, or null if not found
     * @throws IllegalArgumentException if key is null
     */
    public Value get(Key key) {
        // TODO: Implement get(Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Checks whether this symbol table contains the given key.
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
     * Inserts the specified key-value pair into the Red-Black BST (Slide 37).
     * Overwrites the old value if the key is already present.
     * Note: Always set root.color = BLACK after insertion.
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
     * Recursive helper for insertion in a LLRB tree (Slide 37).
     *
     * Steps:
     * 1. If h == null, insert at bottom and color it RED: return new Node(key, val,
     * RED, 1).
     * 2. Standard BST recursive descent (update value if cmp == 0).
     * 3. Fix color invariants on the way up:
     * - Right child red, left child black -> rotateLeft(h)
     * - Left child red, left-left grandchild red -> rotateRight(h)
     * - Both children red -> flipColors(h)
     * 4. Update subtree count: h.count = 1 + size(h.left) + size(h.right).
     * 5. Return h.
     *
     * @param h   the subtree root
     * @param key the key
     * @param val the value
     * @return the balanced subtree root
     */
    private Node put(Node h, Key key, Value val) {
        // TODO: Implement put(Node h, Key key, Value val)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // 4. ORDERED SYMBOL TABLE OPERATIONS (Slide 21)
    // =========================================================================

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        // TODO: Implement min()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Helper to find the node with the smallest key in subtree x.
     *
     * @param x the subtree root
     * @return node with minimum key
     */
    private Node min(Node x) {
        // TODO: Implement min(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        // TODO: Implement max()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Helper to find the node with the largest key in subtree x.
     *
     * @param x the subtree root
     * @return node with maximum key
     */
    private Node max(Node x) {
        // TODO: Implement max(Node x)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the largest key less than or equal to the given key.
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
     * Recursive helper for floor.
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
     * Returns the smallest key greater than or equal to the given key.
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
     * Recursive helper for ceiling.
     *
     * @param x   the subtree root
     * @param key the query key
     * @return node containing ceiling key, or null
     */
    private Node ceiling(Node x, Key key) {
        // TODO: Implement ceiling(Node x, Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the number of keys strictly less than the given key.
     *
     * @param key the query key
     * @return number of keys < key
     * @throws IllegalArgumentException if key is null
     */
    public int rank(Key key) {
        // TODO: Implement rank(Key key)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for rank.
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
     * Returns the key of rank k (0-indexed).
     *
     * @param k the rank (0 <= k < size())
     * @return the k-th smallest key
     * @throws IllegalArgumentException if k is out of bounds
     */
    public Key select(int k) {
        // TODO: Implement select(int k)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper for select.
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
    // 5. RANGE SEARCH & ITERATION (Slide 21)
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
     * Returns all keys in the symbol table in ascending order.
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
     * Inorder traversal helper to collect keys within [lo, hi].
     *
     * @param x    the subtree root
     * @param list the list collecting keys
     * @param lo   minimum endpoint
     * @param hi   maximum endpoint
     */
    private void keys(Node x, List<Key> list, Key lo, Key hi) {
        // TODO: Implement keys(Node x, List<Key> list, Key lo, Key hi)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns an iterator over all keys in ascending order.
     *
     * @return an iterator over all keys
     */
    @Override
    public Iterator<Key> iterator() {
        // TODO: Implement iterator()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // 6. TREE METRICS & INVARIANT CHECKS (Slide 19 & 41)
    // =========================================================================

    /**
     * Returns the height of the Red-Black BST (1-node tree has height 0; empty tree
     * has height -1).
     * Guaranteed worst-case height is <= 2 * lg N.
     *
     * @return height of the tree
     */
    public int height() {
        // TODO: Implement height()
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursive helper to compute the height of subtree x.
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
        System.out.println("RedBlackTree Template Code - Ready for implementation!");
        System.out.println("Uncomment the test cases below after completing all TODOs.");

        /*
         * UNCOMMENT THE TEST CODE BELOW ONCE YOU HAVE IMPLEMENTED THE METHODS:
         *
         * RedBlackTree<String, Integer> rbt = new RedBlackTree<>();
         * 
         * // Test insertion sequence from Slide 35-36:
         * // S E A R C H E X A M P L E
         * String[] keys = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L",
         * "E"};
         * int[] vals = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 };
         * 
         * for (int i = 0; i < keys.length; i++) {
         * rbt.put(keys[i], vals[i]);
         * }
         * 
         * System.out.println("Size: " + rbt.size()); // expected: 10
         * System.out.println("Height: " + rbt.height()); // expected: <= 2 * lg(10)
         * 
         * System.out.println("\nKeys in sorted order:");
         * for (String s : rbt.keys()) {
         * System.out.println(s + " " + rbt.get(s));
         * }
         * 
         * System.out.println("\nOrdered operations:");
         * System.out.println("min(): " + rbt.min());
         * System.out.println("max(): " + rbt.max());
         * System.out.println("floor('G'): " + rbt.floor("G"));
         * System.out.println("ceiling('G'): " + rbt.ceiling("G"));
         * System.out.println("rank('P'): " + rbt.rank("P"));
         * System.out.println("select(6): " + rbt.select(6));
         */
        }
}
