//Singleton (using double-checked locking) 
class AppConfig{
    private static volatile AppConfig instance = null;

    //private attributes 
    private String appName;
    private String version;
    private String logLevel;

    //private constructor so the object cannot be instantiated a second time
    private AppConfig() {
        this.appName = "myApp";
        this.version = "5.2.1";
        this.logLevel = "DEBUG";
    }

    public static AppConfig getInstance(){
        if (instance == null){
            //ensuring thread safety (only one thread can enter at a time)
            synchronized (AppConfig.class){
                //the second check
                if (instance == null){
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }
}

public class Main{
    public static void main(String[] args){
        //creating the Runnable objects
        Runnable task1 = () -> {
            AppConfig config1 = AppConfig.getInstance();
            System.out.println("Thread1: " + config1.hashCode());
        };

        Runnable task2 = () -> {
            AppConfig config2 = AppConfig.getInstance();
            System.out.println("Thread2: " + config2.hashCode());
        };

        //creating the threads
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        //triggering 2 threads concurrently
        thread1.start();
        thread2.start();
    }
}