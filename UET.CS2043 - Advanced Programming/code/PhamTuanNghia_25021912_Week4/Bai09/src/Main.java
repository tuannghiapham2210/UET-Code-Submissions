import java.util.ArrayList;
import java.util.List;

abstract class Product {
    //attributes (instance variables)
    protected String id;
    protected String name;

    //constructor
    public Product(String id, String name){
        this.id = id;
        this.name = name;
    }

    //public getter
    public String getName(){
        return this.name;
    }
}

class FoodProduct extends Product{
    //attribute
    private String expirationDate;

    //constructor
    public FoodProduct(String id, String name, String expirationDate){
        super(id, name);
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString(){
        return this.name +  " - " + this.expirationDate;
    }
}

class ElectronicProduct extends Product {
    private int warranty;

    //constructor
    public ElectronicProduct(String id, String name, int warranty){
        super(id, name);
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.warranty + " months";
    }
}

//creating a warehouse than only srores a product
class Warehouse<T extends Product> {
    private List<T> items = new ArrayList<>();

    public void addProduct(T item){
        items.add(item);
    }

    public void removeProduct (T item){
        items.remove(item);
    }

    public void printInventory(){
        for (T item : items){{
            System.out.println(item.toString());
        }}
    }
}

public class Main {
    public static void main(String[] args) {
        Warehouse<FoodProduct> foodWarehouse = new Warehouse<>();
        foodWarehouse.addProduct(new FoodProduct("ABC", "Milk", "2026-12-31"));
        foodWarehouse.addProduct(new FoodProduct("DEF", "Bread", "2026-03-30"));

        Warehouse<ElectronicProduct> techWarehouse = new Warehouse<>();
        techWarehouse.addProduct(new ElectronicProduct("123", "Laptop", 24));
        techWarehouse.addProduct(new ElectronicProduct("456", "Phone", 12));

        foodWarehouse.printInventory();
        techWarehouse.printInventory();
    }
}