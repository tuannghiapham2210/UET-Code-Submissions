#selection_sort O(n^2)
def selection_sort(arr: list[int]) -> list[int]:
    arr_clone = arr.copy()
    n = len(arr_clone)
    for i in range(n - 1):
        min_idx = i
        for j in range(i + 1, n):
            if arr_clone[j] < arr_clone[min_idx]:
                min_idx = j
        arr_clone[i], arr_clone[min_idx] = arr_clone[min_idx], arr_clone[i] 
    return arr_clone


#bubble_sort O(n^2)
def bubble_sort(arr: list[int]) ->list[int]:
    arr_clone = arr.copy()
    n = len(arr)
    for i in range(n - 1):
        swapped = False
        for j in range(1, n - i):
            if arr_clone[j] < arr_clone[j - 1]:
                swapped = True
                arr_clone[j], arr_clone[j - 1] = arr_clone[j - 1], arr_clone[j]
        if not swapped:
            break
    return arr_clone

#insertion_sort O(n^2)
def insertion_sort(arr: list[int]) -> list[int]:
    arr_clone = arr.copy()
    n = len(arr_clone)
    for i in range(1, n):
        key = arr_clone[i]
        j = i - 1
        while j >= 0 and arr_clone[j] > key:
            arr_clone[j + 1] = arr_clone[j]
            j -= 1
        arr_clone[j + 1] = key
    return arr_clone

#merge_sort O(nLogn)
def merge(arr1: list[int], arr2: list[int]) -> list[int]:
    result = []
    i = j = 0
    
    while i < len(arr1) and j < len(arr2):
        if arr1[i] <= arr2[j]:
            result.append(arr1[i])
            i += 1
        else:
            result.append(arr2[j])
            j += 1
    
    result.extend(arr1[i:])
    result.extend(arr2[j:])

    return result

def merge_sort(arr: list[int]) -> list[int]:
    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2

    left_sorted = merge_sort(arr[:mid])
    right_sorted = merge_sort(arr[mid:])

    return merge(left_sorted, right_sorted)

#quick_sort O(nLogn)
def partition(arr: list[int], low: int, high: int):
    pivot = arr[high]
    i = low - 1
    
    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    
    arr[high], arr[i + 1] = arr[i + 1], arr[high]

    return i + 1

def quicksort(arr: list[int], low: int, high: int):
    if low < high:
        p = partition(arr, low, high)
        quicksort(arr, low, p - 1)
        quicksort(arr, p + 1, high)

def quick_sort(arr: list[int]) -> list[int]:
    arr_clone = arr.copy()
    quicksort(arr_clone, 0, len(arr_clone) - 1)
    return arr_clone

if __name__ ==  "__main__":
    #check quick_sort
    assert quick_sort([]) == []
    assert quick_sort([1]) == [1]
    assert quick_sort([3, 2, 1]) == [1, 2, 3]
    assert quick_sort([1, 2, 3, 4]) == [1, 2, 3, 4]
    assert quick_sort([5, 1, 4, 2, 8]) == [1, 2, 4, 5, 8]
    assert quick_sort([2, 2, 1]) == [1, 2, 2]
    print("quick_sort works")

    #check merge_sort
    assert merge_sort([]) == []
    assert merge_sort([1]) == [1]
    assert merge_sort([3, 2, 1]) == [1, 2, 3]
    assert merge_sort([1, 2, 3, 4]) == [1, 2, 3, 4]
    assert merge_sort([5, 1, 4, 2, 8]) == [1, 2, 4, 5, 8]
    assert merge_sort([2, 2, 1]) == [1, 2, 2]
    print("merge_sort works")

    #check bubble_sort
    assert bubble_sort([]) == []
    assert bubble_sort([1]) == [1]
    assert bubble_sort([3, 2, 1]) == [1, 2, 3]
    assert bubble_sort([1, 2, 3, 4]) == [1, 2, 3, 4]
    assert bubble_sort([5, 1, 4, 2, 8]) == [1, 2, 4, 5, 8]
    assert bubble_sort([2, 2, 1]) == [1, 2, 2]
    print("bubble_sort works")

    #check selection_sort
    assert selection_sort([]) == []
    assert selection_sort([1]) == [1]
    assert selection_sort([3, 2, 1]) == [1, 2, 3]
    assert selection_sort([1, 2, 3, 4]) == [1, 2, 3, 4]
    assert selection_sort([5, 1, 4, 2, 8]) == [1, 2, 4, 5, 8]
    assert selection_sort([2, 2, 1]) == [1, 2, 2]
    print("selection_sort works")

    #check insertion_sort
    assert insertion_sort([]) == []
    assert insertion_sort([1]) == [1]
    assert insertion_sort([3, 2, 1]) == [1, 2, 3]
    assert insertion_sort([1, 2, 3, 4]) == [1, 2, 3, 4]
    assert insertion_sort([5, 1, 4, 2, 8]) == [1, 2, 4, 5, 8]
    assert insertion_sort([2, 2, 1]) == [1, 2, 2]
    print("insertion_sort works")