import java.util.Scanner;
import java.time.LocalDate;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of products: ");
        int n = scanner.nextInt();

        //getting the "\n" inside of the scanner's buffer
        scanner.nextLine();

        Product[] array = new Product[n];

        for (int i = 0; i < n; i++){
            String line = scanner.nextLine();

            //getting the type of the product
            char type = line.charAt(0);

            int firstQuote = line.indexOf('"');
            int lastQuote = line.lastIndexOf('"');

            String name = line.substring(firstQuote + 1, lastQuote);

            //getting the numPart
            String numPart = line.substring(lastQuote + 1).trim();

            String[] numbers = numPart.split("\\s+");

            if (type == 'E'){
                double originalPrice = Double.parseDouble(numbers[0]);
                double warranty = Double.parseDouble(numbers[1]);

                array[i] = new Electronics(name, originalPrice, warranty);

            } else if (type == 'F'){
                double originalPrice = Double.parseDouble(numbers[0]);
                LocalDate expiration = LocalDate.parse(numbers[1]); 

                array[i] = new Food(name, originalPrice, expiration);
            }
        }

        double finalPrice = 0;
        for (int i = 0; i < n; ++i){
            finalPrice += array[i].getFinalPrice();
        }

        System.out.println("Total: " + finalPrice);
    }
}