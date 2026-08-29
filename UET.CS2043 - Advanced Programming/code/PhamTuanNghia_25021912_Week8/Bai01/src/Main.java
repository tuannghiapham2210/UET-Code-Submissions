public class Main {
    public static void main(String[] args){
        //testing the first problem
        FeeCalculator calc = new FeeCalculator();
        System.out.println("Fee (10h, rate 50, member): " + calc.calculateFee(10, 50.0, true));

        //testing the third problem

        Shape rect = new Rectangle(10, 5);
        Shape tri = new Triangle(10, 5);
        Shape circ = new Circle(10);
        
        System.out.println("Rectangle Area: " + rect.getArea());
        System.out.println("Triangle Area: " + tri.getArea());
        System.out.println("Circle Area: " + circ.getArea());
    }
}