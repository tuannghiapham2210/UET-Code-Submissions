public abstract class Order {
    protected double weight;
    protected double distance;

    //constructor
    public Order(double weight, double distance){
        this.weight = weight;
        this.distance = distance;

    }

    //abstract methods
    public abstract double getDeliveryFee();
    public abstract String getLabel();
}

class StandardOrder extends Order {
    //constructor
    public StandardOrder(double weight, double distance){
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee(){
        return weight * 3000 + distance * 500;
    }

    @Override
    public String getLabel(){
        return "[THƯỜNG]";
    }
}

class ExpressOrder extends Order {
    public ExpressOrder(double weight, double distance){
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee(){
       return (weight * 3000 + distance * 500) * 1.5;  
    }

    @Override
    public String getLabel(){
       return "[HỎA TỐC]"; 
    }
}

class FragileOrder extends Order {
    public FragileOrder(double weight, double distance){
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee(){
        return weight * 5000 + distance * 700 + 20000;  
    }

    @Override
    public String getLabel(){
        return "[HÀNG DỄ VỠ]";
    }
}

class BulkyOrder extends Order {
    public BulkyOrder(double weight, double distance){
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee(){
        return weight * 4000 + distance * 600 + 50000;
    }

    @Override
    public String getLabel(){
        return "[HÀNG CỒNG KỀNH]";
    }
}
