//Singleton: creating a Logger class that has only one object throughout the whole program
class Logger {
    public static volatile Logger instance = null;

    //private constructor
    private Logger() {}

    public static Logger getInstance(){
        if (instance == null){
            //only one thread can enter at a time
            synchronized (Logger.class){
                if (instance == null){
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
}

//Abstract factory: creating object without using the new operator in main
//product (abstract)
interface Export{
    void export();
}

//concrete products
class PdfExport implements Export{
    @Override
    public void export(){
        System.out.println("exporting PDF");
    }
}

class ExcelExport implements Export{
    public void export(){
        System.out.println("exporting Excel");
    }
}

//creator (abstract)
abstract class ExportFactory{
    //factory method
    public abstract Export createExport();
}

//creators (concrete)
class PdfFactory extends ExportFactory{
    @Override 
    public Export createExport(){
        return new PdfExport();
    }
}

class ExcelFactory extends ExportFactory{
    @Override
    public Export createExport(){
        return new ExcelExport();
    }
}

//Adapter: forcing incompatible codes to work together (withoug modifying the existing code)
class OldPlayer {
    void playFile(String name){
        System.out.println("Playing file: " + name);
    }
}

//the interface that we must implement
interface Player{
    void play(String name);
}   

class PlayerAdapter implements Player{
    //holding a reference to the old incompatible object
    OldPlayer lib;

    //constructor
    public PlayerAdapter() {
        this.lib = new OldPlayer();
    }

    @Override
    public void play(String name){
        //delegate the call 
        lib.playFile(name);
    }
}

//Prototype: creating a clone of a configuration object to modify while keeping the original file untampered with
interface Prototype<T>{
    T clone();
}

class Configuration implements Prototype<Configuration>{
    private String setting;

    public Configuration(String setting){
        this.setting = setting;
    }

    public Configuration clone(){
        return new Configuration(this.setting);
    }

    //public setter to modify the object
    public void setSetting(String setting){
        this.setting = setting;
    }

    public void display(){
        System.out.println("Setting: ".concat(this.setting));
    }
}

public class Main{
    public static void main(String[] args){
        //testing singleton design pattern
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        //checking if they are the same object
        System.out.println("Checking singleton: ");
        System.out.print("Checking if those 2 logger objects are the same (using == operator): ");
        System.out.println(logger1 == logger2);

        //testing factory method design pattern
        System.out.println("\nChecking factory method: ");
        ExportFactory pdfFactory = new PdfFactory();
        Export pdf = pdfFactory.createExport();

        ExportFactory excelFactory = new ExcelFactory();
        Export excel = excelFactory.createExport();

        pdf.export();
        excel.export();

        //checking adapter design pattern
        System.out.println("\nChecking adapter: ");
        Player filePlayer = new PlayerAdapter();
        filePlayer.play("test.txt");

        //checking prototype design pattern
        System.out.print("\nChecking prototype: ");
        System.out.println();
        Configuration original = new Configuration("fps_max 1000");
        Configuration clone = original.clone();
        //modifying the clone
        clone.setSetting("fps_max 500");
        System.out.print("Original: ");
        original.display();
        System.out.print("Clone: ");
        clone.display();
    }
}