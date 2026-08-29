class Task implements Runnable {
    //attributes
    private String name;
    private long durationMs;

    //constructor
    public Task(String name, long durationMs){
        this.name = name;
        this.durationMs = durationMs;

    }

    @Override
    public void run(){
        System.out.println("Start ".concat(this.name));

        try {
            Thread.sleep(this.durationMs);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        System.out.println("End ".concat(this.name));
    }
}

public class Main {
    public static void main(String[] args){
        //creating the tasks
        Task task1 = new Task("Task1", 1000);
        Task task2 = new Task("Task2", 2000);

        //creating Thread objects
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        //starting 2 threads
        thread1.start();
        thread2.start();

        //using join() to wait for both threads to finish
        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks done.");
    }
}