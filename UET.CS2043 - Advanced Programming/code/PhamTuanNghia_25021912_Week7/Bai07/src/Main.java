import java.util.*;
import java.util.concurrent.*;

class CountPrime implements Callable<Integer>{
    private int[] array;

    //constructor
    public CountPrime(int[] array){
        this.array = array;
        
    }

    //helper method to find prime number
    private boolean isPrime(int n){
        if (n < 2) return false;

        for (int i = 2; i*i <= n; i++){
            if (n % i == 0) return false;
        }

        return true;
    }

    @Override
    public Integer call(){
        int count = 0;
        for(int number: array){
            if (isPrime(number)) count += 1;
        }

        return count;
    }
}

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        //creating an ExecutorService to run tasks concurrently
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        //creating a list to store the result
        List<Future<Integer>> futures = new ArrayList<>();

        //submitting the tasks
        for (int i = 0; i < n; i++){
            int m = scanner.nextInt();
            int[] array = new int[m];

            for (int j = 0; j < m; j++){
                array[j] = scanner.nextInt();
            }

            Future<Integer> future = executorService.submit(new CountPrime(array));
            futures.add(future);
        }

        //getting the results
        List<Integer> allCounts = new ArrayList<>();
        int maxPrime = -1;
        for (Future<Integer> future : futures){
            try {
                int primeCount = future.get();
                allCounts.add(primeCount);
                if (primeCount > maxPrime) maxPrime = primeCount;

            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();

            }
        }

        //printing out the results
        for (int k = 0; k < allCounts.size(); k++){
            System.out.println("Array " + k + ": " + allCounts.get(k));
        }

        System.out.print("Most primes: ");
        for (int k = 0; k < allCounts.size(); k++){
            if (allCounts.get(k) == maxPrime){
                System.out.println("Array " + k + " with " + maxPrime + " primes");
            }
        }

        executorService.shutdown();
    }
}