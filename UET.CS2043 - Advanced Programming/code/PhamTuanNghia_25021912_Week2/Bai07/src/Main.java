public class Main {
    public static void main(String[] args){
        //instantiating 2 products
        Product product1 = new Product("123", "Laptop", 1000.0);
        Product product2 = new Product("456", "PC", 2000.0);

        //creating an array that holds 2 products
        Product[] array = {product1, product2};

        //instantiating an inventory object
        Inventory inventory = new Inventory(array);

        array[0].setPrice(5000.0);

        for (Product product: inventory.getProducts()){
            System.out.println("Product name: " + product.getName() + " | Price: " + product.getPrice() + " | ID: " + product.getId());
        }
    }
}
