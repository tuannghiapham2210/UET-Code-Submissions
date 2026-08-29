import java.util.*;
import java.util.concurrent.*;

class SecondLargest implements Callable<Integer>{
    private int[] array;

    public SecondLargest(int[] array){
        this.array = array;
    }

    @Override
    public Integer call(){
        int secondLargest = Integer.MIN_VALUE;
        int largest = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++){
            if (array[i] > largest){
                secondLargest = largest;
                largest = array[i];

            } else if (array[i] > secondLargest && array[i] != largest){
                secondLargest = array[i];
            }
        }

        if (secondLargest != Integer.MIN_VALUE){
            return secondLargest;

        } 

        System.out.println();
        return secondLargest;
    }
}


public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        //creating an ExecutorService to do the tasks simultaneously
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        //creating a list to store the results
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < n; i++){
            int m = scanner.nextInt();
            int[] array = new int[m];

            for (int j = 0; j < m; j++){
                array[j] = scanner.nextInt();
            }

            Future<Integer> future = executorService.submit(new SecondLargest(array));
            futures.add(future);   
        }
        
        //getting the results
        int result = 0;

        int count = -1;
        for (Future<Integer> future : futures){
            count += 1;
            int secondLargest = 0;
            try {
                secondLargest = future.get();
                if (secondLargest != Integer.MIN_VALUE){
                    System.out.println("Array " + count + ": second largest = " + secondLargest);
                    result += secondLargest;

                } else {
                    System.out.println("Array " + count + ": Not found");

                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();

            }
        }

        System.out.println("Sum = " + result);

        executorService.close();
    }
}