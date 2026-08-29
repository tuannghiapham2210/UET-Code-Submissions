import java.util.Scanner;

//abstract products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

//abstract factory
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

//the concrete products
class WindowsButton implements Button{
    @Override
    public void render(){
        System.out.println("Creating a windows button");
    }
}

class WindowsCheckbox implements Checkbox{
    @Override
    public void render(){
        System.out.println("creating a windows checkbox");
    }
}

class MacButton implements Button{
    @Override
    public void render(){
        System.out.println("creating a mac button");
    }
}

class MacCheckbox implements Checkbox{
    @Override
    public void render(){
        System.out.println("creating a mac checkbox");
    }
}

//the concrete factories (using singleton)
class WindowsFactory implements UIFactory {
    //private constructor
    private WindowsFactory() {};

    //the pointer to the only instance
    private static volatile WindowsFactory instance = null;
    
    public static WindowsFactory getInstance(){
        if (instance == null){
            //only one thread can get in at a time
            synchronized (WindowsFactory.class){
                if (instance == null){ //double check
                    instance = new WindowsFactory();
                }
            }
        }
        return instance;
    } 

    //fullfilling the contract
    @Override
    public Button createButton(){
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox(){
        return new WindowsCheckbox();
    }
}

class MacFactory implements UIFactory{
    //private constructor
    private MacFactory() {}

    //pointer to the only instance (null initially)
    private static volatile MacFactory instance = null;

    public static MacFactory getInstance(){
        if (instance == null){
            synchronized (MacFactory.class){
                if (instance == null){
                    instance = new MacFactory();
                }
            }
        }
        return instance;
    }

    //fullfilling the contract
    @Override
    public Button createButton(){
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox(){
        return new MacCheckbox();
    }
}

class Application {
    private Button button;
    private Checkbox checkbox;

    //constructor
    public Application(UIFactory factory){
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();

    }

    public void draw(){
        button.render();
        checkbox.render();
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("win/mac: ");

        String os = scanner.nextLine().toLowerCase().trim();

        UIFactory factory = null;

        if (os.equals("win")){
            factory = WindowsFactory.getInstance();

        } else if (os.equals("mac")){
            factory = MacFactory.getInstance();

        } else{
            System.out.println("Invalid input");
            return; 
        }

        Application app = new Application(factory);
        app.draw();

    }
}