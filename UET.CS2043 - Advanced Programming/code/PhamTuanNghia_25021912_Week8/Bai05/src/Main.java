import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){
        List<Order> orders = new ArrayList<>();

        orders.add(new StandardOrder(10, 5));
        orders.add(new ExpressOrder(2, 10));
        orders.add(new FragileOrder(5, 2));
        orders.add(new BulkyOrder(50, 100));

        for (Order order : orders){
            System.out.println("Label: ".concat(order.getLabel()));
            System.out.println("Delivery fee: " + order.getDeliveryFee());
            System.out.println();
        }
    }
}