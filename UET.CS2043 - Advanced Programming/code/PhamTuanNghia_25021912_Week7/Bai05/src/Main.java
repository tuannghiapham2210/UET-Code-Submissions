import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;

class Order implements Callable<Boolean> {
    //attributes
    private String id;  
    private long processMs;
    private List<String> logs;
    private AtomicInteger successCount;

    //constructor
    public Order(String id, long processMs, List<String> logs, AtomicInteger successCount){
        this.id = id;
        this.processMs = processMs;
        this.logs = logs;
        this.successCount = successCount;

    }

    @Override public Boolean call(){
        System.out.println("Start ".concat(this.id));
        
        try {Thread.sleep(processMs);} catch (InterruptedException e) {}

        boolean success = processMs <= 1500;

        //write the results into logs (ensure thread safety)
        //only one thread can enter this block at a time
        synchronized (logs) {
            if (success) {
                logs.add("DONE ".concat(this.id));

            } else {
                logs.add("FAIL ".concat(this.id));

            }
        }

        //incrrementing the count
        if (success) {
            successCount.incrementAndGet();

        }

        //returning the value
        return success;
    }
}


public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();

        scanner.nextLine(); // consume the newline

        //creating the logs list so everyone can write into it
        List<String> logs = new ArrayList<>();

        //creating a count to keep track of successful orders
        AtomicInteger successCount = new AtomicInteger(0);

        //creating an ExecutorService to handle orders concurrently
        ExecutorService executorService = Executors.newFixedThreadPool(m);

        //creating a list to store the results
        List<Future<Boolean>> futures = new ArrayList<>();

        //submitting the tasks
        for (int i = 0; i < m; i++){
            String id = scanner.next();
            long processMs = scanner.nextLong();

            futures.add(executorService.submit(new Order(id, processMs, logs, successCount)));
        }

        for (Future<Boolean> future : futures){
            try{future.get();} catch(InterruptedException | ExecutionException e){}
        }

        //printing out the results
        System.out.println("Success = " + successCount.get());

        for (String log : logs){
            System.out.println(log);
        }

        executorService.shutdown();
    }
}

//testcase
// 3
// A 1000
// B 2000
// C 500