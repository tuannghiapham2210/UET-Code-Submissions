class Logger {
    private static volatile Logger instance = null;

    //private constructor
    private Logger() {}

    public static Logger getInstance(){
        if (instance == null){
            //only one thread can enter this block at a time
            synchronized(Logger.class){
                if (instance == null){
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void logInfo(String message){
        System.out.println("[INFO] ".concat(message));
    }

    public void logError(String message){
        System.out.println("[ERROR] ".concat(message));
    }
}


public class Main{
    public static void main(String[] args){
        //calling Logger.getInstance in various places
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        //checking if logger1 and logger2 both point to the same object on the heap
        System.out.println("Logger instances equal: " + (logger1 == logger2));

        logger1.logInfo("Application started");
        logger2.logInfo("Processing data...");
        logger1.logError("Something went wrong");
    }
}