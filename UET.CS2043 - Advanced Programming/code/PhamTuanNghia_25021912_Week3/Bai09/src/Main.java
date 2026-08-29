import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        //getting the "\n" inside of the scanner buffer
        scanner.nextLine();

        //creating an array 
        IPayable[] array = new IPayable[n];

        for (int i = 0; i < n; ++i){
            String line = scanner.nextLine();

            //getting the type 
            char type = line.charAt(0);

            String[] data = line.split("\\s+");
            if (type == 'S'){
                String id = data[1];
                String name = data[2];
                int workingHours = Integer.parseInt(data[3]);
                double hourlyRate = Double.parseDouble(data[4]);
                array[i] = new PartTimeStaff(id, name, workingHours, hourlyRate); 
            
            } else if (type == 'I'){
                String itemName = data[1];
                int quantity = Integer.parseInt(data[2]);
                double pricePerItem = Double.parseDouble(data[3]);
                array[i] = new Invoice(itemName, quantity, pricePerItem);

            }
        }
        
        //creating newlines for an impeccable output ;)
        System.out.print("\n\n");

        double total = 0.0;
        //iterating through the array and printing all of the payments needed
        for (int i = 0; i < n; i++){
            System.out.println(array[i].getPaymentDetails());
            total += array[i].getPaymentAmount();
        }

        System.out.println("Total Payment = " + total);
    }
}