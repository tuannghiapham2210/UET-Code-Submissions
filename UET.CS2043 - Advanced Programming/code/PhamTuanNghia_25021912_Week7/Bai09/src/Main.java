import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int value = 0;
    private final ReentrantLock lock = new ReentrantLock();

    //method to increment the count (thread safe)
    public void increment() {
        //only one thread can enter this block (returns false and stops waiting immediately if cannot obtain the lock)
        if (lock.tryLock()) {
            try {
                value++;

            } finally {
                lock.unlock();

            }
        
        } else { //if cannot aquire the lock, prints out message
            System.out.println(Thread.currentThread().getName() + " could not acquire the lock");

        }
    }

    //method to get the value
    public int getValue() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        //creating an array that stores 4 threads
        Thread[] threads = new Thread[4];

        //starting threads
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.increment();
                }
            }, "Thread-" + i);
            threads[i].start();
        }

        //using join to wait for all threads to finish calculating
        try {
            threads[1].join();
            threads[2].join();
            threads[3].join();

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println("Final counter value: " + counter.getValue());
    }
}