public abstract class Room {
    protected int numNights;

    //constructor
    public Room(int numNights){
        this.numNights = numNights;
    }

    abstract public double getPrice();
}

class Standard extends Room{
    //constructor
    public Standard(int numNights){
        super(numNights);
    }

    public double getPrice(){
        double totalPrice = 500000 * this.numNights;

        if (this.numNights > 3){
            return totalPrice * (1 - 0.05);

        } else{
            return totalPrice; 
        }
    }
}

class Vip extends Room{
    //constructor
    public Vip(int numNights){
        super(numNights);
    }

    public double getPrice(){
        double totalPrice = this.numNights * 2000000;
        return totalPrice;
    }
}
