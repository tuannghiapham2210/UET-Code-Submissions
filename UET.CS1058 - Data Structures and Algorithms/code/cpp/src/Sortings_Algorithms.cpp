//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <stdexcept>

class SortingAlgorithms {
private:
    // Utility function for merge sort
    static void merge(std::vector<int>& arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        std::vector<int> L(n1);
        std::vector<int> R(n2);

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Utility function for quick sort (Lomuto partition scheme)
    static int partition(std::vector<int>& arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                std::swap(arr[i], arr[j]);
            }
        }

        std::swap(arr[i + 1], arr[high]);

        return i + 1;
    }

    // Recursive function for merge sort
    static void mergeSortRecursive(std::vector<int>& arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSortRecursive(arr, left, mid);
            mergeSortRecursive(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    // Recursive function for quick sort
    static void quickSortRecursive(std::vector<int>& arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSortRecursive(arr, low, pi - 1);
            quickSortRecursive(arr, pi + 1, high);
        }
    }

public:
    // Bubble Sort
    static void bubbleSort(std::vector<int>& arr) {
        int n = static_cast<int>(arr.size());

        for (int i = 0; i < n - 1; i++) {
            bool swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    std::swap(arr[j], arr[j + 1]);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    // Selection Sort
    static void selectionSort(std::vector<int>& arr) {
        int n = static_cast<int>(arr.size());

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                std::swap(arr[i], arr[minIdx]);
            }
        }
    }

    // Insertion Sort
    static void insertionSort(std::vector<int>& arr) {
        int n = static_cast<int>(arr.size());

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // Merge Sort (public interface)
    static void mergeSort(std::vector<int>& arr) {
        if (arr.size() <= 1) {
            return;
        }

        mergeSortRecursive(arr, 0, static_cast<int>(arr.size()) - 1);
    }

    // Quick Sort (public interface)
    static void quickSort(std::vector<int>& arr) {
        if (arr.size() <= 1) {
            return;
        }

        quickSortRecursive(arr, 0, static_cast<int>(arr.size()) - 1);
    }
};

// Utility function to print array
void printArray(const std::vector<int>& arr) {
    for (size_t i = 0; i < arr.size(); i++) {
        std::cout << arr[i] << (i + 1 < arr.size() ? " " : "");
    }

    std::cout << std::endl;
}

// Helper to verify sorting correctness against comprehensive test cases
void verifySort(void (*sortFunc)(std::vector<int>&), const std::string& sortName) {
    std::vector<std::vector<int>> testCases = {
        {},                                              // Empty array
        {42},                                            // Single element
        {1, 2, 3, 4, 5, 6},                              // Already sorted
        {9, 8, 7, 6, 5, 4, 3, 2, 1},                     // Reverse sorted
        {5, 2, 8, 5, 2, 5, 8, 1, 9, 2},                  // Duplicates
        {7, 7, 7, 7, 7},                                 // All identical elements
        {-12, 45, -3, 0, 89, -100, 23, 14},              // Negative and positive numbers
        {64, 34, 25, 12, 22, 11, 90, 8, 4, 23, 42}       // Arbitrary unsorted array
    };

    for (size_t i = 0; i < testCases.size(); i++) {
        std::vector<int> arr = testCases[i];
        std::vector<int> expected = testCases[i];
        std::sort(expected.begin(), expected.end());

        sortFunc(arr);

        if (arr != expected) {
            throw std::runtime_error("TEST FAILED: " + sortName + " failed on test case #" + std::to_string(i));
        }
    }
}

//-------------------- STRICT TESTING FUNCTIONS --------------------
void testElementarySorts() {
    // --- TEST 1: Bubble Sort ---
    verifySort(SortingAlgorithms::bubbleSort, "BubbleSort");

    // --- TEST 2: Selection Sort ---
    verifySort(SortingAlgorithms::selectionSort, "SelectionSort");

    // --- TEST 3: Insertion Sort ---
    verifySort(SortingAlgorithms::insertionSort, "InsertionSort");

    // --- SUCCESS ---
    std::cout << "ElementarySorts Worked" << std::endl;
}

void testAdvancedSorts() {
    std::cout << "========== TESTING ADVANCED SORTS ==========" << std::endl;

    // --- TEST 1: Merge Sort ---
    verifySort(SortingAlgorithms::mergeSort, "MergeSort");

    // --- TEST 2: Quick Sort ---
    verifySort(SortingAlgorithms::quickSort, "QuickSort");

    // --- SUCCESS ---
    std::cout << "AdvancedSorts Worked" << std::endl;
}

void testSortingAlgorithms() {
    testElementarySorts();
    testAdvancedSorts();
}

int main() {
    testSortingAlgorithms();
    return 0;
}
