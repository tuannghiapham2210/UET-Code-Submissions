import java.util.List;
import java.util.ArrayList;

abstract class Vehicle {
    private final String plate;
    
    //constructor
    public Vehicle(String plate){
        this.plate = plate;
    }

    public String getPlate(){
        return plate;
    }

    public abstract double calculateFee(int hours);
    public abstract int calculateBonus(int hours);
}

class Car extends Vehicle {
    public Car(String plate){
        super(plate);
    }

    @Override
    public double calculateFee(int hours){
        double fee = 10.0;
        if (hours > 2){
            return fee + (hours - 2) * 3;
        }
        return fee;
    }

    @Override
    public int calculateBonus(int hours){
        return 1;
    }
}

class Bike extends Vehicle {
    public Bike(String plate){
        super(plate);
    }

    @Override
    public double calculateFee(int hours){
        double fee = 5;
        if (hours > 3){
            return fee + (hours - 3) * 2;
        }
        return fee;
    }

    @Override
    public int calculateBonus(int hours){
        return 1;
    }
}

class Truck extends Vehicle { 
    public Truck(String plate){
        super(plate);
    }

    @Override
    public double calculateFee(int hours){
        return 15 + hours * 4;
    }

    @Override
    public int calculateBonus(int hours){
        return (hours > 5) ? 2 : 1;
    }
}

class ParkingTicket {
    private final Vehicle vehicle;
    private final int hours;

    public ParkingTicket(Vehicle vehicle, int hours){
        this.vehicle = vehicle;
        this.hours = hours;
    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }

    public int getHours(){
        return this.hours;
    }

    public double getFee(){
        return vehicle.calculateFee(hours);
    }

    public int getBonus(){
        return vehicle.calculateBonus(hours);
    }
}

public class ParkingCustomer {
    private final String name;
    private final List<ParkingTicket> tickets = new ArrayList<>();

    //constructor
    public ParkingCustomer(String name){
        this.name = name;
    }

    public void addTicket(ParkingTicket ticket){
        tickets.add(ticket);
    }

    public double getTotalFee(){
        double totalFee = 0.0;
        for (ParkingTicket ticket : tickets){
            totalFee += ticket.getFee();
        }
        return totalFee;
    }

    public int getTotalBonus(){
        int totalBonus = 0;
        for (ParkingTicket ticket : tickets){
            totalBonus += ticket.getBonus();
        }
        return totalBonus;
    }

    public String receipt(){
        StringBuilder result = new StringBuilder("Parking Receipt for " + name + "\n");
        
        for (ParkingTicket ticket : tickets){
            result.append("\t")
                  .append(ticket.getVehicle().getPlate())
                  .append("\t")
                  .append(ticket.getFee())
                  .append("\n");
        }

        result.append("Total fee is ").append(getTotalFee()).append("\n");
        result.append("You earned ").append(getTotalBonus()).append(" bonus points");

        return result.toString();
    }
}