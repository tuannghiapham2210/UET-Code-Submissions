import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Devices{
    //attributes as requested (every device has these attributes so we use an abstract class)
    protected String id;
    protected String name;
    protected boolean isOn;

    //constructor
    public Devices(String id, String name){
        this.id = id;
        this.name = name;
        this.isOn = false;
    }

    //methods to turn on and off for electronic devices
    public void turnOn(){
        this.isOn = true;
    }

    public void turnOff(){
        this.isOn = false;
        System.out.println(this.name + " turned off");
    }
}

interface WifiEnabled {
    void connectWifi();
}

interface BrightnessAdjustable {
    void increaseBrightness();
    void decreaseBrightness();
}

interface VolumeAdjustable {
    void increaseVolume();
    void decreaseVolume();
} 

class SmartLight extends Devices implements BrightnessAdjustable{
    int brightness;
    //constructor
    public SmartLight(String id, String name){
        super(id, name);
        this.brightness = 0;
    }

    public void increaseBrightness(){
        if (this.brightness >=0 && this.brightness <=90){
            this.brightness +=10;
        }
    }

    public void decreaseBrightness(){
        if (this.brightness >= 10){
            this.brightness -=10;
        }
    }
}

class AirConditioner extends Devices implements WifiEnabled{
    //constructor
    public AirConditioner(String id, String name){
        super(id, name);
    }

    public void connectWifi(){
        System.out.println(this.name + " connected to wifi");
    }
}


class Speaker extends Devices implements VolumeAdjustable, WifiEnabled{
    int volume;
    //constructor
    public Speaker(String id, String name){
        super(id, name);
        this.volume = 0;
    }

    public void increaseVolume(){
        if (this.volume >=0 && this.volume <=90){
            this.volume +=10;
        }
    }

    public void decreaseVolume(){
        if (this.volume >= 10){
            this.volume -=10;
        }
    }

    public void connectWifi(){
        System.out.println(this.name + " connected to wifi");
    }
}

class SmartCurtain extends Devices{
    //constructor
    public SmartCurtain(String id, String name){
        super(id, name);
    }
}

class Hub{
    private List<Devices> devices = new ArrayList<>();

    //public function to add a device into the list
    public void addDevice(Devices device){
        devices.add(device);
    }

    public void turnOffAll(){
        System.out.println("Turn Off All Devices:");
        for (Devices device : devices){
            device.turnOff();;
        }
    }

    public void setupWifi(){
        System.out.println("Setup Wifi:");
        for (Devices device : devices){
            if (device instanceof WifiEnabled){
                ((WifiEnabled) device).connectWifi();
            }
        }
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //getting the number of devices
        int n = scanner.nextInt();

        //creating a hub
        Hub hub = new Hub();

        //removing the "\n" inside of the scanner buffer
        scanner.nextLine();

        for (int i = 0; i < n; ++i){
            String[] data = scanner.nextLine().split("\\s+");
            String type = data[0];
            String id = data[1];
            String name = data[2];

            if (type.equals("L")){
                hub.addDevice(new SmartLight(id, name));

            } else if (type.equals("AC")){
                hub.addDevice(new AirConditioner(id, name));

            } else if (type.equals("S")){
                hub.addDevice(new Speaker(id, name));

            } else if (type.equals("C")){
                hub.addDevice(new SmartCurtain(id, name));
            }
        }

        hub.turnOffAll();
        hub.setupWifi();

    }
}