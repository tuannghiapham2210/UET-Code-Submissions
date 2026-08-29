public class Main {
    public static void main(String[] args) {
        // Test Hệ thống Cũ (Before Refactor)
        OldParkingCustomer oldCustomer = new OldParkingCustomer("Nghia Pham (Old)");
        oldCustomer.addTicket(new OldParkingTicket(new OldVehicle("29A-111.11", OldVehicle.CAR), 3));    // 10 + 1*3 = 13 fee, 1 pt
        oldCustomer.addTicket(new OldParkingTicket(new OldVehicle("30F-222.22", OldVehicle.BIKE), 4));   // 5 + 1*2 = 7 fee, 1 pt
        oldCustomer.addTicket(new OldParkingTicket(new OldVehicle("51G-333.33", OldVehicle.TRUCK), 6));  // 15 + 6*4 = 39 fee, 2 pts (Truck > 5h)
        System.out.println(oldCustomer.receipt());

        System.out.println("------------------------------------------------");

        // Test Hệ thống Mới (After Refactor - Output PHẢI GIỐNG HỆT)
        ParkingCustomer newCustomer = new ParkingCustomer("Nghia Pham (New)");
        newCustomer.addTicket(new ParkingTicket(new Car("29A-111.11"), 3));
        newCustomer.addTicket(new ParkingTicket(new Bike("30F-222.22"), 4));
        newCustomer.addTicket(new ParkingTicket(new Truck("51G-333.33"), 6));
        System.out.println(newCustomer.receipt());
    }
}