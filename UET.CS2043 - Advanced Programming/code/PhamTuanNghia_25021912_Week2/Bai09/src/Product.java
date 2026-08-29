public class Product {
    //instance fields
    private String name;
    private double price;
    private int quantity;
    private double discount;

    //static fields
    private static double taxRate = 0.1;
    private static double totalRevenue = 0.0;

    //constructor
    public Product(String name, double price, int quantity, double discount){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    //methods
    public static void updateTaxRate(double newRate){
        taxRate = newRate;
    }

    public double calculateFinalPrice(){
        double finalPrice = (this.price - this.discount) * (1 + taxRate);
        return finalPrice;
    }
    
    public void updateDiscount(double newDiscount){
        this.discount = newDiscount;
    }

    public void sell(int amount){
        if (amount <= this.quantity){
            this.quantity -= amount;
            totalRevenue += (this.calculateFinalPrice() * amount);
            System.out.println("sold successfully!");

        } else{
            System.err.println("Not enough quantity!");
        }
    }

    public static double getTotalRevenue(){
        return totalRevenue;
    }
}
