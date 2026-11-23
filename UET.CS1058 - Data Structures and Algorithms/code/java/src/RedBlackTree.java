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
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /**
     * Returns the number of nodes in the subtree rooted at x.
     *
     * @param x the subtree root
     * @return number of nodes in subtree x, or 0 if x is null
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.count;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return total number of key-value pairs
     */
    public int size() {
        return size(root);
    }

    /**
     * Checks if this symbol table is empty.
     *
     * @return {@code true} if empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
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
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
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
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * Color flip: Recolors to split a (temporary) 4-node (Slide 28 - 29).
     * Precondition: h is black, and both h.left and h.right are red.
     * Invariants: Maintains symmetric order and perfect black balance.
     *
     * @param h the parent node of the temporary 4-node
     */
    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
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
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    /**
     * Checks whether this symbol table contains the given key.
     *
     * @param key the key to check
     * @return {@code true} if key is present, {@code false} otherwise
     * @throws IllegalArgumentException if key is null
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Inserts the specified key-value pair into the Red-Black BST (Slide 37).
     * Overwrites the old value if the key is already present.
     * Note: Always set root.color = BLACK after insertion.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if key or val is null
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            throw new IllegalArgumentException("second argument to put() is null");
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    /**
     * Recursive helper for insertion in a LLRB tree (Slide 37).
     *
     * @param h   the subtree root
     * @param key the key
     * @param val the value
     * @return the balanced subtree root
     */
    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED, 1);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        // Fix any right-leaning links, two consecutive left red links, or 4-nodes
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.count = 1 + size(h.left) + size(h.right);
        return h;
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
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;
    }

    /**
     * Helper to find the node with the smallest key in subtree x.
     *
     * @param x the subtree root
     * @return node with minimum key
     */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(root).key;
    }

    /**
     * Helper to find the node with the largest key in subtree x.
     *
     * @param x the subtree root
     * @return node with maximum key
     */
    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    /**
     * Returns the largest key less than or equal to the given key.
     *
     * @param key the query key
     * @return largest key <= key, or null if no such key
     * @throws IllegalArgumentException if key is null
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    /**
     * Recursive helper for floor.
     *
     * @param x   the subtree root
     * @param key the query key
     * @return node containing floor key, or null
     */
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Returns the smallest key greater than or equal to the given key.
     *
     * @param key the query key
     * @return smallest key >= key, or null if no such key
     * @throws IllegalArgumentException if key is null
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    /**
     * Recursive helper for ceiling.
     *
     * @param x   the subtree root
     * @param key the query key
     * @return node containing ceiling key, or null
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Returns the number of keys strictly less than the given key.
     *
     * @param key the query key
     * @return number of keys < key
     * @throws IllegalArgumentException if key is null
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, root);
    }

    /**
     * Recursive helper for rank.
     *
     * @param key the query key
     * @param x   the subtree root
     * @return number of keys in subtree x strictly less than key
     */
    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    /**
     * Returns the key of rank k (0-indexed).
     *
     * @param k the rank (0 <= k < size())
     * @return the k-th smallest key
     * @throws IllegalArgumentException if k is out of bounds
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        return select(root, k).key;
    }

    /**
     * Recursive helper for select.
     *
     * @param x the subtree root
     * @param k the rank to select
     * @return node of rank k
     */
    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
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
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
        }
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Returns all keys in the symbol table in ascending order.
     *
     * @return all keys in sorted order
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new ArrayList<>();
        }
        return keys(min(), max());
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
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        List<Key> list = new ArrayList<>();
        keys(root, list, lo, hi);
        return list;
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
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, list, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            list.add(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, list, lo, hi);
        }
    }

    /**
     * Returns an iterator over all keys in ascending order.
     *
     * @return an iterator over all keys
     */
    @Override
    public Iterator<Key> iterator() {
        return keys().iterator();
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
        return height(root);
    }

    /**
     * Recursive helper to compute the height of subtree x.
     *
     * @param x the subtree root
     * @return height of subtree x, or -1 if x is null
     */
    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    // =========================================================================
    // 7. MAIN METHOD (Test harness for verifying the implementation)
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("   DEMO: LEFT-LEANING RED-BLACK BST IMPLEMENTATION");
        System.out.println("===============================================================");

        RedBlackTree<String, Integer> rbt = new RedBlackTree<>();

        // Test insertion sequence from Slide 35-36:
        // S E A R C H E X A M P L E
        String[] keys = { "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E" };
        int[] vals = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

        for (int i = 0; i < keys.length; i++) {
            rbt.put(keys[i], vals[i]);
        }

        System.out.println("Size: " + rbt.size()); // expected: 10
        System.out.println("Height: " + rbt.height()); // expected: 3 (balanced, <= 2 * lg(10))

        System.out.println("\nKeys in sorted order:");
        for (String s : rbt.keys()) {
            System.out.println(s + " " + rbt.get(s));
        }

        System.out.println("\nOrdered operations:");
        System.out.println("min(): " + rbt.min());
        System.out.println("max(): " + rbt.max());
        System.out.println("floor('G'): " + rbt.floor("G"));
        System.out.println("ceiling('G'): " + rbt.ceiling("G"));
        System.out.println("rank('P'): " + rbt.rank("P"));
        System.out.println("select(6): " + rbt.select(6));
        System.out.println("size('C', 'R'): " + rbt.size("C", "R"));
        System.out.print("keys('C', 'R'): ");
        for (String s : rbt.keys("C", "R")) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
