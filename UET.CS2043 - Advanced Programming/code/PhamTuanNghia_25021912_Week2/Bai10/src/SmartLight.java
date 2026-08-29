public class SmartLight {
    //instance variables
    private String id;
    private String name;
    private int brightness;

    //constructors
    public SmartLight(String id, String name, int brightness){
        this.id = id;
        this.name = name;
        this.brightness = brightness;
    }

    public SmartLight(String id, String name){
        this(id, name, 50);
    }

    //public methods   
    public void setBrightness(int brightness){
        this.brightness = brightness;
    }

    public void setBrightness(String preset){
        if (preset.equals("MAX")){
            this.setBrightness(100);

        } else if (preset.equals("MIN")){
            this.setBrightness(10);

        } else if (preset.equals("ECO")){
            this.setBrightness(30);
        }
    }

    public void connectToHub(CentralHub hub){
        hub.registerDevice(this);
    }

    public String getName(){
        return this.name;
    }

    public int getBrightness(){
        return this.brightness;
    }
}
