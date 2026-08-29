import java.util.*;
import java.util.concurrent.*;

class StageOneTask implements Callable<ArrayList<Integer>> {
    private ArrayList<Integer> array;
    private int arrayIndex;

    //constructor
    public StageOneTask(ArrayList<Integer> array, int arrayIndex){
        this.array = array;
        this.arrayIndex = arrayIndex;
    }

    //helper method to check a prime number
    boolean isPrime(int n){
        if (n < 2) return false;

        for (int i = 2; i*i <=n; i++){
            if (n % i == 0) return false;
        }

        return true;
    }

    @Override
    public ArrayList<Integer> call(){
        ArrayList<Integer> primeIntegers = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            if (isPrime(array.get(i))){
                primeIntegers.add(array.get(i));
            }
        }
        System.out.println("Stage 1 - Array " + this.arrayIndex + ": " + primeIntegers);
        return primeIntegers;   
    }
}

class StageTwoTask implements Callable<Integer> {
    private ArrayList<Integer> primeArray;
    private int arrayIndex;

    //constructor
    public StageTwoTask(ArrayList<Integer> primeArray, int arrayIndex){
        this.primeArray = primeArray;
        this.arrayIndex = arrayIndex;
    }

    @Override 
    public Integer call(){
        int result = 0;
        
        if (primeArray.size() % 2 == 0){
            for (int x : primeArray){
                result += (int) Math.pow(x, 2);
            }

            System.out.println("Stage 2 - Array " + this.arrayIndex + ": sum of squares = " + result);

        } else if (primeArray.size() % 2 != 0){
            for (int x : primeArray){
                result += (int) Math.pow(x, 3);
            }

            System.out.println("Stage 2 - Array " + this.arrayIndex + ": sum of cubes = " + result);
        }

        return result;
    }
}


public class Main {
    public static void main(String[] args){
        //getting the input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        for (int i = 0; i < n; i++){
            int m = scanner.nextInt();

            ArrayList<Integer> array = new ArrayList<>();
            for (int j = 0; j < m; j++){
                array.add(scanner.nextInt());
            }   

            input.add(array);
        }

        //creating the pools
        ExecutorService pool1 = Executors.newFixedThreadPool(n);
        ExecutorService pool2 = Executors.newFixedThreadPool(n);

        //submiting the tasks
        ArrayList<Future<ArrayList<Integer>>> stage1Futures = new ArrayList<>();
        ArrayList<Future<Integer>> stage2Futures = new ArrayList<>();

        //submiting first tasks
        for (int i = 0; i < input.size(); i++){
            stage1Futures.add(pool1.submit(new StageOneTask(input.get(i), i)));

        }

        //submiting second tasks
        for (int i = 0; i < stage1Futures.size(); i++){
            try{
                //getting the result from stage 1
                ArrayList<Integer> primeList = stage1Futures.get(i).get();

                //submiting it to pool2
                stage2Futures.add(pool2.submit(new StageTwoTask(primeList, i)));

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();

            }
        }

        int total = 0;
        for (Future<Integer> future : stage2Futures){
            try{
                total += future.get();

            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();

            }
        }

        System.out.println("Total = " + total);

        pool1.shutdown();
        pool2.shutdown();
    }
}