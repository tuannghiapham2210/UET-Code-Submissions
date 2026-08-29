import java.util.ArrayList;
import java.util.List;

public class OldParkingCustomer {
    private final String name;
    private final List<OldParkingTicket> tickets = new ArrayList<>();

    public OldParkingCustomer(String name) { this.name = name; }
    public void addTicket(OldParkingTicket ticket) { tickets.add(ticket); }

    public String receipt() {
        double totalFee = 0;
        int bonusPoints = 0;
        String result = "Parking Receipt for " + name + "\n";
        
        for (OldParkingTicket each : tickets) {
            double thisFee = 0;
            switch (each.getVehicle().getType()) {
                case OldVehicle.CAR:
                    thisFee += 10;
                    if (each.getHours() > 2) thisFee += (each.getHours() - 2) * 3;
                    break;
                case OldVehicle.BIKE:
                    thisFee += 5;
                    if (each.getHours() > 3) thisFee += (each.getHours() - 3) * 2;
                    break;
                case OldVehicle.TRUCK:
                    thisFee += 15 + each.getHours() * 4;
                    break;
            }
            totalFee += thisFee;
            bonusPoints++;
            if (each.getVehicle().getType() == OldVehicle.TRUCK && each.getHours() > 5) {
                bonusPoints++;
            }
            result += "\t" + each.getVehicle().getPlate() + "\t" + thisFee + "\n";
        }
        result += "Total fee is " + totalFee + "\n";
        result += "You earned " + bonusPoints + " bonus points";
        return result;
    }
}

class OldVehicle {
    public static final int CAR = 0;
    public static final int BIKE = 1;
    public static final int TRUCK = 2;
    private final String plate;
    private final int type;

    public OldVehicle(String plate, int type) {
        this.plate = plate;
        this.type = type;
    }
    public String getPlate() { return plate; }
    public int getType() { return type; }
}

class OldParkingTicket {
    private final OldVehicle vehicle;
    private final int hours;

    public OldParkingTicket(OldVehicle vehicle, int hours) {
        this.vehicle = vehicle;
        this.hours = hours;
    }
    public OldVehicle getVehicle() { return vehicle; }
    public int getHours() { return hours; }
}

