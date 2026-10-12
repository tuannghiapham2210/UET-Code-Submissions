//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

#include <iostream>
#include <vector>
#include <stdexcept>

//-------------------- BINARY HEAP (MAX HEAP) --------------------
class MaxHeap {
private:
    int* array;      // Mảng để lưu trữ các phần tử của heap
    int capacity;    // Kích thước tối đa của heap
    int size;        // Số phần tử hiện tại trong heap

    // Helper function to get parent index
    int parent(int i) const {
        return (i - 1) / 2;
    }

    // Helper function to get left child index
    int leftChild(int i) const {
        return (2 * i + 1);
    }

    // Helper function to get right child index
    int rightChild(int i) const {
        return (2 * i + 2);
    }

    // Helper function to swap two elements
    static void swap(int& a, int& b) {
        int temp = a;
        a = b;
        b = temp;
    }

    // Move an element up to its correct position (for insertion)
    void swim(int index) {
        while (index > 0 && array[index] > array[parent(index)]) {
            swap(array[index], array[parent(index)]);
            index = parent(index);
        }
    }

    // Move an element down to its correct position (for deletion)
    void sink(int index, int heapSize) {
        sink(array, index, heapSize);
    }

    // Static sink helper used by both member functions and heapSort
    static void sink(int arr[], int index, int heapSize) {
        while (2 * index + 1 < heapSize) {
            int largest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < heapSize && arr[left] > arr[largest]) {
                largest = left;
            }

            if (right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }

            if (largest != index) {
                swap(arr[index], arr[largest]);
                index = largest;
            }
            else {
                break;
            }
        }
    }

public:
    // Constructor: Initialize array with given capacity
    MaxHeap(int maxCapacity = 100) {
        if (maxCapacity <= 0) {
            maxCapacity = 100;
        }

        capacity = maxCapacity;
        size = 0;
        array = new int[capacity];
    }

    // Destructor: Free dynamically allocated memory
    ~MaxHeap() {
        delete[] array;
        array = nullptr;
    }

    // Copy Constructor (Deep copy)
    MaxHeap(const MaxHeap& other) {
        capacity = other.capacity;
        size = other.size;
        array = new int[capacity];

        for (int i = 0; i < size; i++) {
            array[i] = other.array[i];
        }
    }

    // Copy Assignment Operator (Deep copy)
    MaxHeap& operator=(const MaxHeap& other) {
        if (this != &other) {
            delete[] array;

            capacity = other.capacity;
            size = other.size;
            array = new int[capacity];

            for (int i = 0; i < size; i++) {
                array[i] = other.array[i];
            }
        }

        return *this;
    }

    // Returns true if heap has no elements, false otherwise
    bool isEmpty() const {
        return size == 0;
    }

    // Returns true if heap is full, false otherwise
    bool isFull() const {
        return size == capacity;
    }

    // Add value to the heap
    void insert(int value) {
        if (isFull()) {
            throw std::overflow_error("Heap is full");
        }

        array[size] = value;
        swim(size);
        size++;
    }

    // Remove and return the maximum element (root)
    int removeMax() {
        if (isEmpty()) {
            throw std::underflow_error("Heap is empty");
        }

        int maxVal = array[0];
        array[0] = array[size - 1];
        size--;

        if (size > 0) {
            sink(0, size);
        }

        return maxVal;
    }

    // Return the maximum element without removing it
    int peekMax() const {
        if (isEmpty()) {
            throw std::underflow_error("Heap is empty");
        }

        return array[0];
    }

    // Remove a node at the given index
    int removeAt(int index) {
        if (isEmpty()) {
            throw std::underflow_error("Heap is empty");
        }

        if (index < 0 || index >= size) {
            throw std::out_of_range("Index out of range");
        }

        int removedValue = array[index];
        array[index] = array[size - 1];
        size--;

        if (index < size) {
            if (index > 0 && array[index] > array[parent(index)]) {
                swim(index);
            }
            else {
                sink(index, size);
            }
        }

        return removedValue;
    }

    // Return the number of elements in the heap
    int getSize() const {
        return size;
    }

    // Build a heap from an array
    void buildHeap(int arr[], int n) {
        if (n < 0) {
            throw std::invalid_argument("Size cannot be negative");
        }

        if (arr == nullptr && n > 0) {
            throw std::invalid_argument("Array pointer cannot be null");
        }

        if (n > capacity) {
            delete[] array;
            capacity = (n > 100) ? n : 100;
            array = new int[capacity];
        }

        size = n;

        for (int i = 0; i < n; i++) {
            array[i] = arr[i];
        }

        for (int i = (n / 2) - 1; i >= 0; i--) {
            sink(i, size);
        }
    }

    // Print the heap (for debugging)
    void printHeap() const {
        for (int i = 0; i < size; i++) {
            std::cout << array[i] << (i + 1 < size ? " " : "");
        }

        std::cout << std::endl;
    }

    // Perform heapsort on the given array
    static void heapSort(int arr[], int n) {
        if (arr == nullptr || n <= 1) {
            return;
        }

        for (int i = (n / 2) - 1; i >= 0; i--) {
            sink(arr, i, n);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr[0], arr[i]);
            sink(arr, 0, i);
        }
    }
};

//-------------------- PRIORITY QUEUE USING MAX HEAP --------------------
class PriorityQueue {
private:
    MaxHeap heap;

public:
    PriorityQueue(int capacity = 100) : heap(capacity) {}

    bool isEmpty() const {
        return heap.isEmpty();
    }

    bool isFull() const {
        return heap.isFull();
    }

    void enqueue(int value) {
        heap.insert(value);
    }

    int dequeue() {
        return heap.removeMax();
    }

    int peek() const {
        return heap.peekMax();
    }

    int size() const {
        return heap.getSize();
    }
};

//-------------------- STRICT TESTING FUNCTIONS --------------------
void testMaxHeap() {
    MaxHeap heap(10);

    // --- TEST 1: Initialization & Empty State ---
    if (!heap.isEmpty()) {
        throw std::runtime_error("TEST FAILED: New heap should be empty.");
    }

    if (heap.isFull()) {
        throw std::runtime_error("TEST FAILED: New heap should not be full.");
    }

    if (heap.getSize() != 0) {
        throw std::runtime_error("TEST FAILED: New heap size should be 0.");
    }

    // --- TEST 2: Insert & Peek ---
    int values[] = {3, 10, 12, 8, 2, 14};

    for (int val : values) {
        heap.insert(val);
    }

    if (heap.getSize() != 6) {
        throw std::runtime_error("TEST FAILED: Size should be 6 after insertions.");
    }

    if (heap.peekMax() != 14) {
        throw std::runtime_error("TEST FAILED: Max element should be 14.");
    }

    // --- TEST 3: Extraction Order (Max Property) ---
    int prev = heap.removeMax();

    if (prev != 14) {
        throw std::runtime_error("TEST FAILED: First extracted element should be 14.");
    }

    while (!heap.isEmpty()) {
        int current = heap.removeMax();

        if (current > prev) {
            throw std::runtime_error("TEST FAILED: Heap property violated during extraction.");
        }

        prev = current;
    }

    if (heap.getSize() != 0) {
        throw std::runtime_error("TEST FAILED: Heap should be empty after extracting all elements.");
    }

    // --- TEST 4: removeAt ---
    MaxHeap testHeap(10);
    int testArr[] = {15, 10, 8, 6, 4, 2};

    for (int val : testArr) {
        testHeap.insert(val);
    }

    int removed = testHeap.removeAt(1);

    if (removed != 10) {
        throw std::runtime_error("TEST FAILED: removeAt(1) returned wrong value.");
    }

    if (testHeap.getSize() != 5) {
        throw std::runtime_error("TEST FAILED: Size after removeAt is incorrect.");
    }

    removed = testHeap.removeAt(0);

    if (removed != 15) {
        throw std::runtime_error("TEST FAILED: removeAt(0) did not remove root.");
    }

    prev = testHeap.removeMax();

    while (!testHeap.isEmpty()) {
        int curr = testHeap.removeMax();

        if (curr > prev) {
            throw std::runtime_error("TEST FAILED: Heap property violated after removeAt.");
        }

        prev = curr;
    }

    // --- TEST 5: buildHeap ---
    int buildArr[] = {4, 10, 3, 5, 1};
    heap.buildHeap(buildArr, 5);

    if (heap.getSize() != 5) {
        throw std::runtime_error("TEST FAILED: Size after buildHeap should be 5.");
    }

    if (heap.peekMax() != 10) {
        throw std::runtime_error("TEST FAILED: Max element after buildHeap should be 10.");
    }

    prev = heap.removeMax();

    while (!heap.isEmpty()) {
        int curr = heap.removeMax();

        if (curr > prev) {
            throw std::runtime_error("TEST FAILED: buildHeap did not establish valid heap order.");
        }

        prev = curr;
    }

    // --- TEST 6: heapSort ---
    int sortArr[] = {12, 11, 13, 5, 6, 7};
    int n = sizeof(sortArr) / sizeof(sortArr[0]);

    MaxHeap::heapSort(sortArr, n);

    for (int i = 0; i < n - 1; i++) {
        if (sortArr[i] > sortArr[i + 1]) {
            throw std::runtime_error("TEST FAILED: heapSort did not sort array correctly.");
        }
    }

    // --- TEST 7: Overflow Handling ---
    MaxHeap smallHeap(2);
    smallHeap.insert(100);
    smallHeap.insert(200);

    if (!smallHeap.isFull()) {
        throw std::runtime_error("TEST FAILED: Heap should be full.");
    }

    bool overflowCaught = false;

    try {
        smallHeap.insert(300);
    }
    catch (const std::overflow_error&) {
        overflowCaught = true;
    }

    if (!overflowCaught) {
        throw std::runtime_error("TEST FAILED: insert on full heap did not throw overflow_error.");
    }

    // --- TEST 8: Exception Handling (Crucial!) ---
    bool exceptionCaught = false;
    MaxHeap emptyHeap(5);

    try {
        emptyHeap.removeMax();
    }
    catch (const std::underflow_error&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: removeMax on empty heap did not throw underflow_error.");
    }

    exceptionCaught = false;

    try {
        emptyHeap.peekMax();
    }
    catch (const std::underflow_error&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: peekMax on empty heap did not throw underflow_error.");
    }

    exceptionCaught = false;

    try {
        emptyHeap.removeAt(0);
    }
    catch (const std::underflow_error&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: removeAt on empty heap did not throw underflow_error.");
    }

    smallHeap.removeMax(); // size is 1 now
    exceptionCaught = false;

    try {
        smallHeap.removeAt(5); // invalid index
    }
    catch (const std::out_of_range&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: removeAt out of bounds did not throw out_of_range.");
    }

    exceptionCaught = false;

    try {
        smallHeap.removeAt(-1);
    }
    catch (const std::out_of_range&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: removeAt with negative index did not throw out_of_range.");
    }

    // --- SUCCESS ---
    std::cout << "MaxHeap Worked" << std::endl;
}

void testPriorityQueue() {
    std::cout << "========== TESTING PRIORITY QUEUE ==========" << std::endl;
    PriorityQueue pq(10);

    // --- TEST 1: Initialization & Empty State ---
    if (!pq.isEmpty()) {
        throw std::runtime_error("TEST FAILED: New priority queue should be empty.");
    }

    if (pq.isFull()) {
        throw std::runtime_error("TEST FAILED: New priority queue should not be full.");
    }

    if (pq.size() != 0) {
        throw std::runtime_error("TEST FAILED: New priority queue size should be 0.");
    }

    // --- TEST 2: Enqueue & Peek ---
    int values[] = {3, 10, 12, 8, 2, 14};

    for (int val : values) {
        pq.enqueue(val);
    }

    if (pq.size() != 6) {
        throw std::runtime_error("TEST FAILED: Size should be 6 after enqueuing.");
    }

    if (pq.peek() != 14) {
        throw std::runtime_error("TEST FAILED: Highest priority element should be 14.");
    }

    // --- TEST 3: Dequeue (Priority Order) ---
    int prev = pq.dequeue();

    if (prev != 14) {
        throw std::runtime_error("TEST FAILED: First dequeued element should be 14.");
    }

    while (!pq.isEmpty()) {
        int curr = pq.dequeue();

        if (curr > prev) {
            throw std::runtime_error("TEST FAILED: Priority queue order violated during dequeue.");
        }

        prev = curr;
    }

    if (pq.size() != 0) {
        throw std::runtime_error("TEST FAILED: Priority queue should be empty after dequeuing all.");
    }

    // --- TEST 4: Interleaved Enqueue and Dequeue ---
    pq.enqueue(50);
    pq.enqueue(20);

    if (pq.dequeue() != 50) {
        throw std::runtime_error("TEST FAILED: Interleaved dequeue returned wrong value.");
    }

    pq.enqueue(70);
    pq.enqueue(10);

    if (pq.dequeue() != 70) {
        throw std::runtime_error("TEST FAILED: Interleaved dequeue returned wrong value.");
    }

    if (pq.dequeue() != 20) {
        throw std::runtime_error("TEST FAILED: Interleaved dequeue returned wrong value.");
    }

    if (pq.dequeue() != 10) {
        throw std::runtime_error("TEST FAILED: Interleaved dequeue returned wrong value.");
    }

    if (!pq.isEmpty()) {
        throw std::runtime_error("TEST FAILED: Priority queue should be empty.");
    }

    // --- TEST 5: Overflow Handling ---
    PriorityQueue smallPQ(2);
    smallPQ.enqueue(100);
    smallPQ.enqueue(200);

    if (!smallPQ.isFull()) {
        throw std::runtime_error("TEST FAILED: Priority queue should be full.");
    }

    bool overflowCaught = false;

    try {
        smallPQ.enqueue(300);
    }
    catch (const std::overflow_error&) {
        overflowCaught = true;
    }

    if (!overflowCaught) {
        throw std::runtime_error("TEST FAILED: enqueue on full priority queue did not throw overflow_error.");
    }

    // --- TEST 6: Exception Handling (Crucial!) ---
    bool exceptionCaught = false;
    PriorityQueue emptyPQ(5);

    try {
        emptyPQ.dequeue();
    }
    catch (const std::underflow_error&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: dequeue on empty queue did not throw underflow_error.");
    }

    exceptionCaught = false;

    try {
        emptyPQ.peek();
    }
    catch (const std::underflow_error&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: peek on empty queue did not throw underflow_error.");
    }

    // --- SUCCESS ---
    std::cout << "PriorityQueue Worked" << std::endl;
}

int main() {
    testMaxHeap();
    testPriorityQueue();
    return 0;
}
