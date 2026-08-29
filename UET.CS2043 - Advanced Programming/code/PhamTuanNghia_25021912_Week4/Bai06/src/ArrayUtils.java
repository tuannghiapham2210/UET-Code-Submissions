public class ArrayUtils {
    //implementing swap method
    public static <T> void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    

    //implementing bubble sort
    public static <T extends Comparable<T>> void sort(T[] array){
        int n = array.length;
        for (int i = 0; i < n - 1; ++i){
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++){
                if (array[j + 1].compareTo(array[j]) < 0){
                    swapped = true;
                    swap(array, j, j + 1);
                }
            }
            if (!swapped){
                return;
            }
        }
    }
}
