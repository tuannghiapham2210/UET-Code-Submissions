class Worker implements Runnable {
    private volatile boolean running = true;

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("Working...");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);

        thread.start();

        try {
            Thread.sleep(1000);
            worker.stop();
            thread.join();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}