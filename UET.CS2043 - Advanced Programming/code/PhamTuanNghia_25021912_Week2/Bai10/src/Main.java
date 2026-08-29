public class Main {
    public static void main(String[] args){
        //instantiating a CentralHub object
        CentralHub hub = new CentralHub();

        //creating 2 lights
        SmartLight l1 = new SmartLight("L01", "Đèn phòng khách", 80);
        SmartLight l2 = new SmartLight("L02", "Đèn ngủ");

        //modifying the brightness of the second light
        l2.setBrightness("ECO");

        //connecting to hub
        l1.connectToHub(hub);
        l2.connectToHub(hub);

        //printing out the brightness of each light
        System.out.println("The brightness of the L01 is: " + l1.getBrightness());
        System.out.println("The brightness of the L02 is: " + l2.getBrightness());
    }
}
