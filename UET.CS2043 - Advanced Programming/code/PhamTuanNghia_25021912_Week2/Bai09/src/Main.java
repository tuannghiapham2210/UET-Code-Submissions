import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //instantiating a scanner object
        Scanner scanner = new Scanner(System.in);

        //getting the name
        System.out.print("Enter the name of the first product: ");
        String name1 = scanner.next();

        //getting the price
        System.out.print("Enter the price of the first product: ");
        double price1 = scanner.nextDouble();

        //getting the quantity
        System.out.print("Enter the quantity of the first product: ");
        int quantity1 = scanner.nextInt();

        //getting the discount
        System.out.print("Enter the discount of the first product: ");
        double discount1 = scanner.nextDouble();

        //instantiating the first product object
        Product p1 = new Product(name1, price1, quantity1, discount1);

        //getting the name
        System.out.print("Enter the name of the second product: ");
        String name2 = scanner.next();

        //getting the price
        System.out.print("Enter the price of the second product: ");
        double price2 = scanner.nextDouble();

        //getting the quantity
        System.out.print("Enter the quantity of the second product: ");
        int quantity2 = scanner.nextInt();

        //getting the discount
        System.out.print("Enter the discount of the second product: ");
        double discount2 = scanner.nextDouble();

        //instantiating the second product object
        Product p2 = new Product(name2, price2, quantity2, discount2);

        //enter the number of product1
        System.out.print("How many product1 do you wanna buy?: ");
        int amount1 = scanner.nextInt();

        //enter the number of product2
        System.out.print("How many product2 do you wanna buy?: ");
        int amount2 = scanner.nextInt();

        //closing the scanner
        scanner.close();

        //selling
        p1.sell(amount1);
        p2.sell(amount2);

        //printing out the prices of p1 and p2
        System.out.println("the final price for Product1 is: " + p1.calculateFinalPrice());
        System.out.println("the final price for Product2 is: " + p2.calculateFinalPrice());

        //updating the tax rate
        Product.updateTaxRate(0.08);

        //updating the discount of p1
        p1.updateDiscount(10.0);

        //printing out the prices of p1 and p2
        System.out.println("the final price for Product1 is: " + p1.calculateFinalPrice());
        System.out.println("the final price for Product2 is: " + p2.calculateFinalPrice());

        //printing out the total revenue
        System.out.println("The total revenue is: " + Product.getTotalRevenue());

    }
}
