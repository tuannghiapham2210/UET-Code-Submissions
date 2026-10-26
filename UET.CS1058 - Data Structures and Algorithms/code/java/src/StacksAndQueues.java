//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Data Structures and Algorithms - Chapter 1.3: Bags, Queues, and Stacks
 * Reference: Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 *
 * This file provides comprehensive, clean, and well-spaced implementations:
 *
 * PART 1: WARMUP STRING IMPLEMENTATIONS (SLIDES 5 - 30)
 *   1. LinkedStackOfStrings        : Singly-linked list stack of strings
 *   2. FixedCapacityStackOfStrings : Fixed-capacity array stack of strings
 *   3. ResizingArrayStackOfStrings : Dynamically resizing array stack of strings
 *   4. LinkedQueueOfStrings        : Singly-linked list queue of strings
 *
 * PART 2: GENERIC IMPLEMENTATIONS WITH ITERABLE SUPPORT (SLIDES 34 - 46)
 *   5. LinkedStack<Item>           : Generic singly-linked list stack
 *   6. FixedCapacityStack<Item>    : Generic fixed-capacity array stack
 *   7. ResizingArrayStack<Item>    : Generic dynamic resizing array stack
 *   8. LinkedQueue<Item>           : Generic singly-linked list queue
 *   9. ResizingArrayQueue<Item>    : Generic dynamic circular resizing array queue
 *
 * PART 3: APPLICATION (SLIDES 55 - 57)
 *  10. evaluateDijkstra(expr)      : Dijkstra's Two-Stack Algorithm for expression evaluation
 *
 * PART 4: DEMO & TESTING
 *  11. main(args)                  : Comprehensive test runner for all implementations
 */
public class StacksAndQueues {


    // =========================================================================
    // PART 1: WARMUP STRING IMPLEMENTATIONS (FROM COURSE SLIDES)
    // =========================================================================


    /**
     * 1. LinkedStackOfStrings
     * Slide 10: Stack: linked-list implementation in Java
     *
     * - Maintains a reference 'first' to the first node in a singly-linked list.
     * - push(): insert a new node before first.
     * - pop(): remove and return the item from first.
     * - Every operation takes constant O(1) time in the worst case.
     * - Memory usage: ~ 40 N bytes.
     */
    public static class LinkedStackOfStrings {

        private Node first; // pointer to top of stack
        private int n;      // number of items currently on stack


        // Private inner class for singly-linked list node
        private static class Node {
            String item;
            Node next;
        }


        // Initializes an empty stack
        public LinkedStackOfStrings() {
            this.first = null;
            this.n = 0;
        }


        // Returns true if the stack is empty, false otherwise
        public boolean isEmpty() {
            return first == null;
        }


        // Returns the number of items on the stack
        public int size() {
            return n;
        }


        // Pushes a new string onto the stack (insert before first)
        public void push(String item) {
            Node oldfirst = first;

            first = new Node();
            first.item = item;
            first.next = oldfirst;

            n++;
        }


        // Removes and returns the string most recently added (from first)
        public String pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow: stack is empty");
            }

            String item = first.item;
            first = first.next;
            n--;

            return item;
        }


        // Returns the string most recently added without removing it
        public String peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow: stack is empty");
            }

            return first.item;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            Node current = first;
            while (current != null) {
                sb.append(current.item);
                if (current.next != null) {
                    sb.append(", ");
                }
                current = current.next;
            }

            sb.append("] (top to bottom)");
            return sb.toString();
        }
    }



    /**
     * 2. FixedCapacityStackOfStrings
     * Slide 14 & 15: Fixed-capacity stack: array implementation
     *
     * - Stores items in an array s[] of fixed capacity.
     * - push(): add new item at s[n], then increment n.
     * - pop(): decrement n, then retrieve item at s[n].
     * - Avoids loitering: sets s[n] = null so garbage collector can reclaim memory.
     */
    public static class FixedCapacityStackOfStrings {

        private String[] s; // underlying array
        private int n;      // number of items currently on stack


        // Initializes an empty stack with the specified capacity
        public FixedCapacityStackOfStrings(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }

            this.s = new String[capacity];
            this.n = 0;
        }


        // Returns true if the stack is empty
        public boolean isEmpty() {
            return n == 0;
        }


        // Returns true if the stack is at maximum capacity
        public boolean isFull() {
            return n == s.length;
        }


        // Returns the number of items on the stack
        public int size() {
            return n;
        }


        // Pushes a new string onto the stack
        public void push(String item) {
            if (isFull()) {
                throw new IllegalStateException("Stack overflow: maximum capacity reached");
            }

            s[n++] = item;
        }


        // Removes and returns the string most recently added
        public String pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow: stack is empty");
            }

            String item = s[--n];

            // Avoid loitering: prevent memory leak
            s[n] = null;

            return item;
        }


        // Returns the most recently added string without removing it
        public String peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow: stack is empty");
            }

            return s[n - 1];
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = n - 1; i >= 0; i--) {
                sb.append(s[i]);
                if (i > 0) {
                    sb.append(", ");
                }
            }

            sb.append("] (top to bottom)");
            return sb.toString();
        }
    }



    /**
     * 3. ResizingArrayStackOfStrings
     * Slide 18 & 20: Stack: resizing-array implementation
     *
     * - Dynamic array resizing:
     *   + Double size of array when array is full (push).
     *   + Halve size of array when array is 1/4 full (pop).
     * - Invariant: array is always between 25% and 100% full.
     * - Every operation takes constant amortized O(1) time.
     */
    public static class ResizingArrayStackOfStrings {

        private String[] s; // underlying array
        private int n;      // number of items on stack


        // Initializes an empty stack with default capacity 1
        public ResizingArrayStackOfStrings() {
            this(1);
        }


        // Initializes an empty stack with given initial capacity
        public ResizingArrayStackOfStrings(int initialCapacity) {
            if (initialCapacity <= 0) {
                initialCapacity = 1;
            }

            this.s = new String[initialCapacity];
            this.n = 0;
        }


        // Returns true if the stack is empty
        public boolean isEmpty() {
            return n == 0;
        }


        // Returns the number of items on the stack
        public int size() {
            return n;
        }


        // Returns current capacity of the internal array
        public int capacity() {
            return s.length;
        }


        // Resizes the underlying array to the specified capacity
        private void resize(int capacity) {
            String[] copy = new String[capacity];

            for (int i = 0; i < n; i++) {
                copy[i] = s[i];
            }

            s = copy;
        }


        // Pushes a new string onto the stack, doubling array if full
        public void push(String item) {
            if (n == s.length) {
                resize(2 * s.length);
            }

            s[n++] = item;
        }


        // Removes and returns the string most recently added
        public String pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow: stack is empty");
            }

            String item = s[--n];

            // Avoid loitering
            s[n] = null;

            // Halve array size when 1/4 full to save space
            if (n > 0 && n == s.length / 4) {
                resize(s.length / 2);
            }

            return item;
        }


        // Returns the most recently added string without removing it
        public String peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow: stack is empty");
            }

            return s[n - 1];
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = n - 1; i >= 0; i--) {
                sb.append(s[i]);
                if (i > 0) {
                    sb.append(", ");
                }
            }

            sb.append("] (top to bottom, cap=").append(s.length).append(")");
            return sb.toString();
        }
    }



    /**
     * 4. LinkedQueueOfStrings
     * Slide 30: Queue: linked-list implementation in Java
     *
     * - Maintains 'first' pointing to front node and 'last' pointing to end node.
     * - enqueue(): insert after last.
     * - dequeue(): remove from first.
     * - Handles empty queue edge cases cleanly.
     * - Constant worst-case O(1) time for all operations.
     */
    public static class LinkedQueueOfStrings {

        private Node first; // front of queue (least recently added)
        private Node last;  // back of queue (most recently added)
        private int n;      // number of items in queue


        // Private inner class for singly-linked list node
        private static class Node {
            String item;
            Node next;
        }


        // Initializes an empty queue
        public LinkedQueueOfStrings() {
            this.first = null;
            this.last = null;
            this.n = 0;
        }


        // Returns true if the queue is empty
        public boolean isEmpty() {
            return first == null;
        }


        // Returns the number of items in the queue
        public int size() {
            return n;
        }


        // Inserts a new string onto the back of the queue
        public void enqueue(String item) {
            Node oldlast = last;

            last = new Node();
            last.item = item;
            last.next = null;

            if (isEmpty()) {
                first = last; // special case for previously empty queue
            } else {
                oldlast.next = last;
            }

            n++;
        }


        // Removes and returns the string least recently added (from front)
        public String dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue underflow: queue is empty");
            }

            String item = first.item;
            first = first.next;
            n--;

            if (isEmpty()) {
                last = null; // avoid loitering when queue becomes empty
            }

            return item;
        }


        // Returns the least recently added string without removing it
        public String peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue underflow: queue is empty");
            }

            return first.item;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            Node current = first;
            while (current != null) {
                sb.append(current.item);
                if (current.next != null) {
                    sb.append(", ");
                }
                current = current.next;
            }

            sb.append("] (front to back)");
            return sb.toString();
        }
    }




    // =========================================================================
    // PART 2: GENERIC IMPLEMENTATIONS WITH ITERABLE SUPPORT
    // =========================================================================




    /**
     * 5. LinkedStack<Item>
     * Slide 37 & 45: Generic stack: linked-list implementation & iterator
     *
     * - Generic LIFO stack using singly-linked list nodes.
     * - Implements java.lang.Iterable<Item> for foreach loop support (LIFO order).
     * - Worst-case O(1) time per operation.
     *
     * @param <Item> the type of items in this stack
     */
    public static class LinkedStack<Item> implements Iterable<Item> {

        private Node<Item> first; // top of stack
        private int n;            // number of items on stack


        // Helper node inner class
        private static class Node<Item> {
            Item item;
            Node<Item> next;
        }


        // Initializes an empty stack
        public LinkedStack() {
            this.first = null;
            this.n = 0;
        }


        // Returns true if this stack is empty
        public boolean isEmpty() {
            return first == null;
        }


        // Returns the number of items on this stack
        public int size() {
            return n;
        }


        // Adds the item to this stack (insert before first)
        public void push(Item item) {
            Node<Item> oldfirst = first;

            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;

            n++;
        }


        // Removes and returns the item most recently added
        public Item pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow");
            }

            Item item = first.item;
            first = first.next;
            n--;

            return item;
        }


        // Returns (without removing) the item most recently added
        public Item peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow");
            }

            return first.item;
        }


        // Returns an iterator that iterates over the items in LIFO order
        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }


        // ListIterator implementing java.util.Iterator (Slide 45)
        private class ListIterator implements Iterator<Item> {
            private Node<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more items in iteration");
                }

                Item item = current.item;
                current = current.next;
                return item;
            }
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            Node<Item> current = first;
            while (current != null) {
                sb.append(current.item);
                if (current.next != null) {
                    sb.append(", ");
                }
                current = current.next;
            }

            sb.append("] (top to bottom)");
            return sb.toString();
        }
    }



    /**
     * 6. FixedCapacityStack<Item>
     * Slide 38-40: Generic stack: array implementation & unchecked cast
     *
     * - Generic LIFO stack using fixed-capacity array.
     * - Uses Java idiom (Item[]) new Object[capacity] to overcome generic array limitation.
     * - Avoids loitering upon pop.
     *
     * @param <Item> the type of items in this stack
     */
    public static class FixedCapacityStack<Item> implements Iterable<Item> {

        private Item[] s; // storage array
        private int n;    // number of items on stack


        // Initializes an empty stack with the given capacity
        @SuppressWarnings("unchecked")
        public FixedCapacityStack(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }

            // Generic array creation not allowed in Java: use cast with warning suppressed
            this.s = (Item[]) new Object[capacity];
            this.n = 0;
        }


        // Returns true if this stack is empty
        public boolean isEmpty() {
            return n == 0;
        }


        // Returns true if this stack is full
        public boolean isFull() {
            return n == s.length;
        }


        // Returns the number of items on this stack
        public int size() {
            return n;
        }


        // Adds the item to this stack
        public void push(Item item) {
            if (isFull()) {
                throw new IllegalStateException("Stack overflow");
            }

            s[n++] = item;
        }


        // Removes and returns the item most recently added
        public Item pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow");
            }

            Item item = s[--n];

            // Avoid loitering: allow GC to collect the object
            s[n] = null;

            return item;
        }


        // Returns the item most recently added without removing it
        public Item peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow");
            }

            return s[n - 1];
        }


        // Returns an iterator that iterates over the items in LIFO order
        @Override
        public Iterator<Item> iterator() {
            return new ReverseArrayIterator();
        }


        // ReverseArrayIterator iterates backwards from n-1 down to 0
        private class ReverseArrayIterator implements Iterator<Item> {
            private int i = n;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more items in iteration");
                }

                return s[--i];
            }
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = n - 1; i >= 0; i--) {
                sb.append(s[i]);
                if (i > 0) {
                    sb.append(", ");
                }
            }

            sb.append("] (top to bottom)");
            return sb.toString();
        }
    }



    /**
     * 7. ResizingArrayStack<Item>
     * Slide 46: Generic stack iterator: array implementation
     *
     * - Dynamically sized array with repeated doubling and halving.
     * - Keeps memory usage bounded between 25% and 100%.
     * - Iterates in LIFO order.
     * - Amortized constant O(1) time per operation.
     *
     * @param <Item> the type of items in this stack
     */
    public static class ResizingArrayStack<Item> implements Iterable<Item> {

        private Item[] s; // storage array
        private int n;    // number of items on stack


        // Initializes an empty stack with default capacity 1
        public ResizingArrayStack() {
            this(1);
        }


        // Initializes an empty stack with given initial capacity
        @SuppressWarnings("unchecked")
        public ResizingArrayStack(int capacity) {
            if (capacity <= 0) {
                capacity = 1;
            }

            this.s = (Item[]) new Object[capacity];
            this.n = 0;
        }


        // Returns true if this stack is empty
        public boolean isEmpty() {
            return n == 0;
        }


        // Returns the number of items on this stack
        public int size() {
            return n;
        }


        // Returns current capacity of the internal array
        public int capacity() {
            return s.length;
        }


        // Resizes the underlying array to hold the specified capacity
        @SuppressWarnings("unchecked")
        private void resize(int capacity) {
            Item[] copy = (Item[]) new Object[capacity];

            for (int i = 0; i < n; i++) {
                copy[i] = s[i];
            }

            s = copy;
        }


        // Adds the item to this stack, doubling array if full
        public void push(Item item) {
            if (n == s.length) {
                resize(2 * s.length);
            }

            s[n++] = item;
        }


        // Removes and returns the item most recently added
        public Item pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow");
            }

            Item item = s[--n];

            // Avoid loitering
            s[n] = null;

            // Halve array size when 1/4 full to save memory
            if (n > 0 && n == s.length / 4) {
                resize(s.length / 2);
            }

            return item;
        }


        // Returns the item most recently added without removing it
        public Item peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow");
            }

            return s[n - 1];
        }


        // Returns an iterator that iterates over the items in LIFO order
        @Override
        public Iterator<Item> iterator() {
            return new ReverseArrayIterator();
        }


        // ReverseArrayIterator iterates backwards from n-1 down to 0
        private class ReverseArrayIterator implements Iterator<Item> {
            private int i = n;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more items in iteration");
                }

                return s[--i];
            }
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = n - 1; i >= 0; i--) {
                sb.append(s[i]);
                if (i > 0) {
                    sb.append(", ");
                }
            }

            sb.append("] (top to bottom, cap=").append(s.length).append(")");
            return sb.toString();
        }
    }



    /**
     * 8. LinkedQueue<Item>
     * Slide 26-30: Queue: linked-list implementation in Java
     *
     * - Generic FIFO queue using a singly-linked list.
     * - Maintains 'first' (front) and 'last' (end) pointers.
     * - Supports foreach iteration in FIFO order.
     * - Constant worst-case O(1) time per operation.
     *
     * @param <Item> the type of items in this queue
     */
    public static class LinkedQueue<Item> implements Iterable<Item> {

        private Node<Item> first; // front of queue (least recently added)
        private Node<Item> last;  // back of queue (most recently added)
        private int n;            // number of items in queue


        // Helper node inner class
        private static class Node<Item> {
            Item item;
            Node<Item> next;
        }


        // Initializes an empty queue
        public LinkedQueue() {
            this.first = null;
            this.last = null;
            this.n = 0;
        }


        // Returns true if this queue is empty
        public boolean isEmpty() {
            return first == null;
        }


        // Returns the number of items in this queue
        public int size() {
            return n;
        }


        // Adds the item to the back of this queue
        public void enqueue(Item item) {
            Node<Item> oldlast = last;

            last = new Node<Item>();
            last.item = item;
            last.next = null;

            if (isEmpty()) {
                first = last; // special case: first element in queue
            } else {
                oldlast.next = last;
            }

            n++;
        }


        // Removes and returns the item at the front of this queue
        public Item dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue underflow");
            }

            Item item = first.item;
            first = first.next;
            n--;

            if (isEmpty()) {
                last = null; // avoid loitering when queue becomes empty
            }

            return item;
        }


        // Returns the item least recently added without removing it
        public Item peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue underflow");
            }

            return first.item;
        }


        // Returns an iterator that iterates over the items in FIFO order
        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }


        // ListIterator traverses from first to last
        private class ListIterator implements Iterator<Item> {
            private Node<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more items in iteration");
                }

                Item item = current.item;
                current = current.next;
                return item;
            }
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            Node<Item> current = first;
            while (current != null) {
                sb.append(current.item);
                if (current.next != null) {
                    sb.append(", ");
                }
                current = current.next;
            }

            sb.append("] (front to back)");
            return sb.toString();
        }
    }



    /**
     * 9. ResizingArrayQueue<Item>
     * Slide 31 & 32: Queue: resizing-array implementation
     *
     * - Generic FIFO queue using a circular dynamic array.
     * - Head and tail indices wrap around using modulo capacity.
     * - Doubles array when full; halves array when 1/4 full.
     * - Unrolls circular items back into sequential indices 0..n-1 during resize.
     * - Avoids loitering by clearing dequeued positions.
     * - Amortized constant O(1) time per operation.
     *
     * @param <Item> the type of items in this queue
     */
    public static class ResizingArrayQueue<Item> implements Iterable<Item> {

        private Item[] q;  // queue storage array
        private int n;     // number of elements on queue
        private int first; // index of first element (head)
        private int last;  // index of next available slot (tail)


        // Initializes an empty queue with default capacity 2
        public ResizingArrayQueue() {
            this(2);
        }


        // Initializes an empty queue with the specified capacity
        @SuppressWarnings("unchecked")
        public ResizingArrayQueue(int capacity) {
            if (capacity <= 0) {
                capacity = 2;
            }

            this.q = (Item[]) new Object[capacity];
            this.n = 0;
            this.first = 0;
            this.last = 0;
        }


        // Returns true if this queue is empty
        public boolean isEmpty() {
            return n == 0;
        }


        // Returns the number of items in this queue
        public int size() {
            return n;
        }


        // Returns current capacity of the internal array
        public int capacity() {
            return q.length;
        }


        // Resizes the underlying array and resets circular indices to 0..n-1
        @SuppressWarnings("unchecked")
        private void resize(int capacity) {
            Item[] copy = (Item[]) new Object[capacity];

            for (int i = 0; i < n; i++) {
                copy[i] = q[(first + i) % q.length];
            }

            q = copy;
            first = 0;
            last = n;
        }


        // Adds the item to this queue
        public void enqueue(Item item) {
            // Double the array size if full
            if (n == q.length) {
                resize(2 * q.length);
            }

            q[last++] = item;

            // Wrap-around modulo capacity
            if (last == q.length) {
                last = 0;
            }

            n++;
        }


        // Removes and returns the item least recently added
        public Item dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue underflow");
            }

            Item item = q[first];

            // Avoid loitering: free slot
            q[first] = null;

            n--;
            first++;

            // Wrap-around modulo capacity
            if (first == q.length) {
                first = 0;
            }

            // Halve array size when 1/4 full to save space
            if (n > 0 && n == q.length / 4) {
                resize(q.length / 2);
            }

            return item;
        }


        // Returns the item least recently added without removing it
        public Item peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue underflow");
            }

            return q[first];
        }


        // Returns an iterator that iterates over the items in FIFO order
        @Override
        public Iterator<Item> iterator() {
            return new ArrayIterator();
        }


        // ArrayIterator traverses the circular buffer from head to tail
        private class ArrayIterator implements Iterator<Item> {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < n;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more items in iteration");
                }

                Item item = q[(first + i) % q.length];
                i++;
                return item;
            }
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = 0; i < n; i++) {
                sb.append(q[(first + i) % q.length]);
                if (i < n - 1) {
                    sb.append(", ");
                }
            }

            sb.append("] (front to back, cap=").append(q.length).append(")");
            return sb.toString();
        }
    }




    // =========================================================================
    // PART 3: APPLICATION: DIJKSTRA'S TWO-STACK ALGORITHM
    // =========================================================================




    /**
     * 10. Dijkstra's Two-Stack Algorithm for Expression Evaluation
     * Slide 55-57: Arithmetic expression evaluation
     *
     * Evaluates fully parenthesized arithmetic expressions:
     * - Value: push onto the value stack
     * - Operator: push onto the operator stack
     * - Left parenthesis '(': ignore
     * - Right parenthesis ')': pop operator and operands, apply operator, push result
     *
     * Example input: "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )" -> 101.0
     *
     * @param expression whitespace-delimited infix arithmetic expression
     * @return the evaluated double value
     */
    public static double evaluateDijkstra(String expression) {
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedStack<Double> vals = new LinkedStack<>();

        String[] tokens = expression.trim().split("\\s+");

        for (String s : tokens) {
            if (s.isEmpty()) {
                continue;
            }

            if (s.equals("(")) {
                // Ignore left parenthesis
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("sqrt")) {
                ops.push(s);
            } else if (s.equals(")")) {
                if (ops.isEmpty()) {
                    continue;
                }

                String op = ops.pop();
                double v = vals.pop();

                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() - v; // preserves (left - right) order
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v; // preserves (left / right) order
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);
                }

                vals.push(v);
            } else {
                // Number token
                vals.push(Double.parseDouble(s));
            }
        }

        if (vals.isEmpty()) {
            throw new IllegalArgumentException("Invalid empty expression");
        }

        return vals.pop();
    }




    // =========================================================================
    // PART 4: MAIN METHOD (DEMO & TESTING ALL IMPLEMENTATIONS)
    // =========================================================================




    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("   ALGORITHMS 1.3: BAGS, QUEUES, AND STACKS - DEMO & TESTING   ");
        System.out.println("================================================================\n");


        // -------------------------------------------------------------
        // Demo 1: LinkedStackOfStrings (Slide 10)
        // -------------------------------------------------------------
        System.out.println(">>> 1. LinkedStackOfStrings (Slide 10)");
        LinkedStackOfStrings strStack = new LinkedStackOfStrings();

        String[] words1 = {"to", "be", "or", "not", "to", "-"};
        for (String w : words1) {
            if (w.equals("-")) {
                System.out.println("Pop: " + strStack.pop());
            } else {
                strStack.push(w);
                System.out.println("Pushed: " + w + " | Stack: " + strStack);
            }
        }
        System.out.println("Remaining size: " + strStack.size() + ", isEmpty: " + strStack.isEmpty() + "\n");


        // -------------------------------------------------------------
        // Demo 2: FixedCapacityStackOfStrings (Slide 14-15)
        // -------------------------------------------------------------
        System.out.println(">>> 2. FixedCapacityStackOfStrings (Slide 14-15)");
        FixedCapacityStackOfStrings fixedStrStack = new FixedCapacityStackOfStrings(4);

        fixedStrStack.push("it");
        fixedStrStack.push("was");
        fixedStrStack.push("the");
        fixedStrStack.push("best");
        System.out.println("Stack after 4 pushes: " + fixedStrStack + " | isFull: " + fixedStrStack.isFull());
        System.out.println("Pop: " + fixedStrStack.pop() + " | Stack now: " + fixedStrStack);
        System.out.println("Peek top: " + fixedStrStack.peek() + "\n");


        // -------------------------------------------------------------
        // Demo 3: ResizingArrayStackOfStrings (Slide 18-20)
        // -------------------------------------------------------------
        System.out.println(">>> 3. ResizingArrayStackOfStrings (Slide 18-20, Dynamic Doubling/Halving)");
        ResizingArrayStackOfStrings dynStrStack = new ResizingArrayStackOfStrings();
        System.out.println("Initial capacity: " + dynStrStack.capacity());

        String[] items = {"it", "was", "the", "best", "of", "times"};
        for (String item : items) {
            dynStrStack.push(item);
            System.out.println("Pushed: " + item + " -> size=" + dynStrStack.size() + ", cap=" + dynStrStack.capacity());
        }

        System.out.println("Stack: " + dynStrStack);
        System.out.println("Popping items to observe shrinking:");
        while (!dynStrStack.isEmpty()) {
            String popped = dynStrStack.pop();
            System.out.println("Popped: " + popped + " -> size=" + dynStrStack.size() + ", cap=" + dynStrStack.capacity());
        }
        System.out.println();


        // -------------------------------------------------------------
        // Demo 4: LinkedQueueOfStrings (Slide 30)
        // -------------------------------------------------------------
        System.out.println(">>> 4. LinkedQueueOfStrings (Slide 30)");
        LinkedQueueOfStrings strQueue = new LinkedQueueOfStrings();

        String[] queueInput = {"it", "was", "the", "-", "best", "of", "-", "times"};
        for (String s : queueInput) {
            if (s.equals("-")) {
                System.out.println("Dequeued: " + strQueue.dequeue() + " | Queue: " + strQueue);
            } else {
                strQueue.enqueue(s);
                System.out.println("Enqueued: " + s + " | Queue: " + strQueue);
            }
        }
        System.out.println("Remaining size: " + strQueue.size() + "\n");


        // -------------------------------------------------------------
        // Demo 5: Generic LinkedStack<Item> with Iterator (Slide 37, 45)
        // -------------------------------------------------------------
        System.out.println(">>> 5. Generic LinkedStack<Item> & For-each Iteration (Slide 37, 45)");
        LinkedStack<Integer> intStack = new LinkedStack<>();

        for (int i = 1; i <= 5; i++) {
            intStack.push(i * 10);
        }

        System.out.println("LinkedStack: " + intStack);
        System.out.print("Iteration via foreach loop: ");
        for (int val : intStack) {
            System.out.print(val + " ");
        }
        System.out.println("\n");


        // -------------------------------------------------------------
        // Demo 6: Generic FixedCapacityStack<Item> (Slide 38-40)
        // -------------------------------------------------------------
        System.out.println(">>> 6. Generic FixedCapacityStack<Item> (Slide 38-40)");
        FixedCapacityStack<Character> charStack = new FixedCapacityStack<>(5);

        for (char c : "HELLO".toCharArray()) {
            charStack.push(c);
        }

        System.out.println("Char stack: " + charStack);
        System.out.print("Pop all: ");
        while (!charStack.isEmpty()) {
            System.out.print(charStack.pop() + " ");
        }
        System.out.println("\n");


        // -------------------------------------------------------------
        // Demo 7: Generic ResizingArrayStack<Item> with Iterator (Slide 46)
        // -------------------------------------------------------------
        System.out.println(">>> 7. Generic ResizingArrayStack<Item> (Slide 46)");
        ResizingArrayStack<Double> dblStack = new ResizingArrayStack<>();

        dblStack.push(1.1);
        dblStack.push(2.2);
        dblStack.push(3.3);
        dblStack.push(4.4);

        System.out.println("ResizingArrayStack: " + dblStack);
        System.out.print("Iteration (LIFO): ");
        for (double d : dblStack) {
            System.out.print(d + " ");
        }
        System.out.println("\n");


        // -------------------------------------------------------------
        // Demo 8: Generic LinkedQueue<Item> with Iterator
        // -------------------------------------------------------------
        System.out.println(">>> 8. Generic LinkedQueue<Item> & For-each Iteration");
        LinkedQueue<String> genericQueue = new LinkedQueue<>();

        genericQueue.enqueue("First");
        genericQueue.enqueue("Second");
        genericQueue.enqueue("Third");

        System.out.println("LinkedQueue: " + genericQueue);
        System.out.print("Iteration (FIFO): ");
        for (String s : genericQueue) {
            System.out.print(s + " -> ");
        }
        System.out.println("END\n");


        // -------------------------------------------------------------
        // Demo 9: Generic ResizingArrayQueue<Item> (Slide 31-32, Circular Buffer)
        // -------------------------------------------------------------
        System.out.println(">>> 9. Generic ResizingArrayQueue<Item> (Slide 31-32, Circular Buffer)");
        ResizingArrayQueue<Integer> circQueue = new ResizingArrayQueue<>();

        System.out.println("Enqueuing 1 to 6:");
        for (int i = 1; i <= 6; i++) {
            circQueue.enqueue(i);
            System.out.println("Enqueue(" + i + ") -> size=" + circQueue.size() + ", cap=" + circQueue.capacity() + ", queue=" + circQueue);
        }

        System.out.println("Dequeuing 3 elements:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Dequeue: " + circQueue.dequeue() + " -> size=" + circQueue.size() + ", cap=" + circQueue.capacity());
        }

        System.out.println("Enqueuing 7, 8, 9 (testing wrap-around and expansion):");
        circQueue.enqueue(7);
        circQueue.enqueue(8);
        circQueue.enqueue(9);

        System.out.println("Queue after wrap-around: " + circQueue);
        System.out.print("Iterating over ResizingArrayQueue: ");
        for (int val : circQueue) {
            System.out.print(val + " ");
        }
        System.out.println("\n");


        // -------------------------------------------------------------
        // Demo 10: Dijkstra's Two-Stack Algorithm (Slide 55-57)
        // -------------------------------------------------------------
        System.out.println(">>> 10. Dijkstra's Two-Stack Expression Evaluation (Slide 55-57)");
        String expr1 = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        double result1 = evaluateDijkstra(expr1);
        System.out.println("Expression: " + expr1);
        System.out.println("Result:     " + result1 + " (Expected: 101.0)");

        String expr2 = "( ( 1 + sqrt ( 5 ) ) / 2.0 )";
        double result2 = evaluateDijkstra(expr2);
        System.out.println("Expression: " + expr2);
        System.out.println("Result:     " + result2 + " (Golden ratio: ~1.6180339887...)");

        System.out.println("\nAll tests completed successfully!");
    }
}




// =============================================================================
// PACKAGE-PRIVATE WRAPPER CLASSES FOR DIRECT ACCESS WITHIN THE PACKAGE
// =============================================================================




class LinkedStack<Item> extends StacksAndQueues.LinkedStack<Item> {
}


class FixedCapacityStack<Item> extends StacksAndQueues.FixedCapacityStack<Item> {
    public FixedCapacityStack(int capacity) {
        super(capacity);
    }
}


class ResizingArrayStack<Item> extends StacksAndQueues.ResizingArrayStack<Item> {
    public ResizingArrayStack() {
        super();
    }

    public ResizingArrayStack(int capacity) {
        super(capacity);
    }
}


class LinkedQueue<Item> extends StacksAndQueues.LinkedQueue<Item> {
}


class ResizingArrayQueue<Item> extends StacksAndQueues.ResizingArrayQueue<Item> {
    public ResizingArrayQueue() {
        super();
    }

    public ResizingArrayQueue(int capacity) {
        super(capacity);
    }
}


class LinkedStackOfStrings extends StacksAndQueues.LinkedStackOfStrings {
}


class FixedCapacityStackOfStrings extends StacksAndQueues.FixedCapacityStackOfStrings {
    public FixedCapacityStackOfStrings(int capacity) {
        super(capacity);
    }
}


class ResizingArrayStackOfStrings extends StacksAndQueues.ResizingArrayStackOfStrings {
    public ResizingArrayStackOfStrings() {
        super();
    }

    public ResizingArrayStackOfStrings(int capacity) {
        super(capacity);
    }
}


class LinkedQueueOfStrings extends StacksAndQueues.LinkedQueueOfStrings {
}
