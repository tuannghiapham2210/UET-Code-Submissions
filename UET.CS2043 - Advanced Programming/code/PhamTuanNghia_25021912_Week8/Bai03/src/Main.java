import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new MotorBike("29A-12345", "Honda"));
        vehicleList.add(new Car("30F-99999", "Toyota"));
        vehicleList.add(new ElectricCar("51G-88888", "VinFast"));

        for (Vehicle vehicle : vehicleList){
            System.out.println(vehicle.getInfo());
        }
    }
}