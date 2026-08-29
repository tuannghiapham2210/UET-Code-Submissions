public abstract class Vehicle {
    //attributes 
    protected String plate;
    protected String brand;

    //constructor
    public Vehicle(String plate, String brand){
        this.plate = plate;
        this.brand = brand;

    }

    public String getBaseInfo(){
        return "[" + plate + "] - " + brand;
    }

    //delegate the implementations to subclasses
    public abstract String getInfo();
}

class MotorBike extends Vehicle {
    protected double fuelLevel;

    //constructor
    public MotorBike(String plate, String brand){
        super(plate, brand);
        this.fuelLevel = 0.0;

    }

    public void refuel(double liters){
        this.fuelLevel += liters;
    }

    @Override
    public String getInfo(){
        return "Xe máy ".concat(getBaseInfo());
    }
}

class Car extends Vehicle {
    protected double fuelLevel;

    //constructor
    public Car(String plate, String brand){
        super(plate, brand);
        this.fuelLevel = 0.0;

    }

    public void refuel(double liters){
        this.fuelLevel += liters;
    }

    @Override
    public String getInfo(){
        return "Ô tô ".concat(getBaseInfo());
    }
}

class ElectricCar extends Vehicle {
    protected double batteryPercent;

    //constructor
    public ElectricCar(String plate, String brand){
        super(plate, brand);
        this.batteryPercent = 0.0;

    }

    public void charge(int percent){
        this.batteryPercent += percent;
    }

    @Override
    public String getInfo(){
        return "Xe điện ".concat(getBaseInfo());
    }
}
