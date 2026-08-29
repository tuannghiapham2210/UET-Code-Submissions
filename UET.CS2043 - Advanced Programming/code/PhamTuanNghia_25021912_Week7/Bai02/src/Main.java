import java.util.*;
import java.util.concurrent.*;

class Task implements Callable<Integer> {
    //attributes
    private int[] array;
    private int start;
    private int end;

    //constructor
    public Task(int[] array, int start, int end){
        this.array = array;
        this.start = start;
        this.end = end;

    }

    @Override
    public Integer call(){
        int sum = 0;
        for (int i = start; i < end; i++){
            sum += array[i];
        }

        return sum;
    }
}

public class Main {
    public static void main(String[] args){
        //getting the imput
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.print("Enter " + n + " integers: ");
        for (int i = 0; i < n; i++){
            array[i] = scanner.nextInt();
        }

        //the number of chunks of the array
        int k = 4;

        //creating thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(k);

        //creating an ArrayList to store future results
        List<Future<Integer>> results = new ArrayList<>();

        int chunkSize = (int) Math.ceil((double) n / k);

        for (int i = 0; i < k; ++i){
            int start = chunkSize * i; 
            int end = Math.min(start + chunkSize, n); //using Min to avoid IndexOutOfBound

            if (start >= n) break;

            Task task = new Task(array, start, end);
            Future<Integer> result = executorService.submit(task);
            results.add(result);
        }

        //calculating the total sum
        int totalSum = 0;
        try{
            for (Future<Integer> result : results){
                totalSum += result.get();
            }

        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();

        }
        
        executorService.shutdown();
        System.out.println("Final sum is: " + totalSum);
    }
}