public abstract class Robot{
    //instance variables (attributes)
    private int id;
    private String modelName;
    private int batteryLevel;

    //constructor
    public Robot(int id, String modelName){
        this.id = id;
        this.modelName = modelName;
        this.batteryLevel = 100;
    }

    public void chargeBattery(){
        this.batteryLevel = 100;
    }

    public final void showIdentity(){
        System.out.println("ID: " + this.id + " Model: " + this.modelName);
    }

    //implementing public getters so that subclasses can access private properties
    public int getId(){
        return this.id;
    }

    public String getModel(){
        return this.modelName;
    }


    //every class that inherits Robot class MUST implement the following abstract method
    public abstract void performMainTask();
}

//declaring interfaces
interface Flyable{
    //the complier (javac) will automatically add public abstract before the method, so we dont have to do that manually
    void fly();
}

interface Swimmable{
    void swim();
}

interface GPS{
    void getCoordinates();
}

//creating subclasses
class DroneRobot extends Robot implements Flyable, GPS{
    //constructor
    public DroneRobot(int id, String modelName){
        super(id, modelName);
    }

    @Override
    public void performMainTask(){
        System.out.println(this.getModel() + " performing main task");
        this.fly();
        this.getCoordinates();
    }

    @Override
    public void fly(){
        System.out.println(this.getModel() + " flying");
    }

    @Override
    public void getCoordinates(){
        System.out.println(this.getModel() + " getting coordinates");
    }
}

class FishRobot extends Robot implements Swimmable{
    //constructor
    public FishRobot(int id, String modelName){
        super(id, modelName);
    }

    @Override
    public void performMainTask(){
        System.out.println(this.getModel() + " Performing main task");
        this.swim();
    }

    @Override
    public void swim(){
        System.out.println(this.getModel() + " swimming");
    }
}

class AmphibiousRobot extends Robot implements Flyable, Swimmable, GPS{
    //constructor
    AmphibiousRobot(int id, String modelName){
        super(id, modelName);
    }

    @Override
    public void performMainTask(){
        System.out.println(this.getModel() + " performing main task");
        this.fly();
        this.swim();
        this.getCoordinates();;
    }

    @Override
    public void fly(){
        System.out.println(this.getModel() + " flying");
    }

    @Override
    public void swim(){
        System.out.println(this.getModel() + " swimming");
    }

    @Override
    public void getCoordinates(){
        System.out.println(this.getModel() + " getting coordinates");
    }

}