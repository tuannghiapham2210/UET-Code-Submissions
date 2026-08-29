import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
//PHASE1: Adapter design pattern

//the required interface (must implement)
interface Sorter{
    int[] sort(int[] arr);
}

//assuming that this class is imported and cannot be modifed
class LegacySorter {
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; //choosing the last element as pivot
        int i = low - 1;

        for (int j = low; j < high; ++j){
            if (arr[j] <= pivot){
                ++i;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }

        //swapping pivot to the correct position
        int temp = arr[i + 1];
        arr[i + 1] = pivot;
        arr[high] = temp;
        
        //returning the index of the pivot (after swapping)
        return i + 1;
    }

    public void messyQuickSort(int[] arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);

            //sorting the left subarray
            messyQuickSort(arr, low, pi - 1);
            //soriting the right array
            messyQuickSort(arr, pi + 1, high);
        }
    }

    //public API (pristine)
    public int[] quickSort(int[] arr){
        messyQuickSort(arr, 0, arr.length - 1);
        return arr;
    }
}

class SorterAdapter implements Sorter{
    //holding a reference to the imported library
    private LegacySorter lib;

    //constructor
    public SorterAdapter(){
        this.lib = new LegacySorter();
    }

    @Override
    public int[] sort(int[] arr){
        //delegate the work to the library
        return lib.quickSort(arr);
    }
}

//PHASE2: Prototype design pattern
interface Prototype<T>{
    T clone();
}

class ReportTemplate implements Prototype<ReportTemplate>{
    //attributes
    private String title;
    private String footer;
    private List<String> sections;

    //constructor
    public ReportTemplate(String title, String footer, List<String> sections){
        this.title = title;
        this.footer = footer;
        this.sections = sections;
    }

    @Override
    public ReportTemplate clone(){
        //String is immutable so it is safe to share
        //creating a deep copy of the list
        List<String> newList = new ArrayList<>(this.sections);
        return new ReportTemplate(this.title, this.footer, newList);
    }

    //public setter to modify the object (to test)
    public void setTitle(String title){
        this.title = title;
    }

    //display method to see the result
    public void display(){
        System.out.println("title: " + this.title);
        System.out.println("footer: " + this.footer);
        System.out.println("section: ");
        for (String section : sections){
            System.out.println(" -" + section);
        }

        System.out.println();
    }
}

public class Main{
    public static void main(String[] args) {
        //PHASE1: TESTING THE ADAPTER
        System.out.print("Testing the adapter (printing out the sorted array): ");
        int[] arr = {2, 4, 1, 5, 3};

        Sorter sorter = new SorterAdapter();
        sorter.sort(arr);

        System.out.println(Arrays.toString(arr));
        System.out.println("\n");

        //PHASE2: TESTING PROTOTYPE
        List<String> sections = new ArrayList<>();
        sections.add("Introduction");
        sections.add("Body");
        sections.add("Conclusion");

        ReportTemplate original = new ReportTemplate("Original Report","Confidential", sections);
        ReportTemplate cloneA = original.clone();
        ReportTemplate cloneB = original.clone();

        //attempting to modify the clone objects
        cloneA.setTitle("CloneA");
        cloneB.setTitle("CloneB");
        System.out.println("Testing out prototype (printing out the original and the clones): ");

        System.out.println("Original: ");
        original.display();

        System.out.println("CloneA: ");
        cloneA.display();

        System.out.println("CloneB: ");
        cloneB.display();

    }
}