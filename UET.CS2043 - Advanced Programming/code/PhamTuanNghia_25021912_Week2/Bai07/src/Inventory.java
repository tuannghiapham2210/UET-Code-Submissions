public class Inventory {
    //instance variable
    private Product[] items;

    //constructor
    public Inventory(Product[] items){
        this.items = new Product[items.length];
        for (int i = 0; i < items.length; ++i){
            if (items[i] != null){
                this.items[i] = new Product(items[i]);
            }
        }
    }

    //public getter to get items
    public Product[] getProducts(){
        Product[] clone = new Product[items.length];
        for (int i = 0; i < items.length; ++i){
            if (items[i] != null){
                clone[i] = new Product(items[i]);
            }
        }
        return clone;
    }
}
