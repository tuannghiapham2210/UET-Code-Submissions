import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Product {
    protected String id;
    protected String name;
    protected double originalPrice;

    //constructor
    public Product(String name){
        this.name = name;
    }

    public abstract double getFinalPrice();
}

class Electronics extends Product{
    protected double warranty;

    //constructor
    public Electronics(String name, double originalPrice, double warranty){
        super(name);
        this.originalPrice = originalPrice;
        this.warranty = warranty;
    }

    public double getFinalPrice(){
        return this.originalPrice * (1 + 0.1) + this.warranty;
    }
}

class Food extends Product{
    protected LocalDate expiration;

    //constructor
    public Food(String name, double originalPrice, LocalDate expiration){
        super(name);
        this.originalPrice = originalPrice;
        this.expiration = expiration;
    }

    public double getFinalPrice(){
        LocalDate today = LocalDate.of(2025, 3, 1);
        long daysBetween = ChronoUnit.DAYS.between(today, expiration);
        if (daysBetween < 7){
            return originalPrice * (1 - 0.2);

        } else{
            return originalPrice;
        }
    }
}
