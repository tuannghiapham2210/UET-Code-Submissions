public class Product {
    private String id;
    private String name;
    private double price;

    //constructor for a product
    public Product(String id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //copy constructor for easy cloning
    public Product(Product source){
        this(source.id, source.name, source.price);
    }

    //public getters
    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    //public setters
    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }
}

