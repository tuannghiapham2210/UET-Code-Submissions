//Invoice is not a staff, does not inherit staff
public class Invoice implements IPayable{
    //attributes
    private String itemName;
    private int quantity;
    double pricePerItem;

    //constructor
    public Invoice(String itemName, int quantity, double pricePerItem){
        this.itemName = itemName;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    //public getters/setters
    public String getItemName(){
        return this.itemName;
    }

    public String getPaymentDetails(){
        return "Invoice " + this.itemName + " - " + "Payment: " + this.getPaymentAmount();
    }

    @Override
    public double getPaymentAmount(){
        return quantity * pricePerItem;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setpricePerItem(double pricePerItem){
        this.pricePerItem = pricePerItem;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    } 
}