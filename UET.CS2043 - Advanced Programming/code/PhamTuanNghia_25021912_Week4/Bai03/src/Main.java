import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //getting the number of employees
        int n = scanner.nextInt();

        scanner.nextLine();

        //creating an array to hold all of the employees
        Employee[] array = new Employee[n];

        for (int i = 0; i < n; i++){
            //getting a line
            String line = scanner.nextLine();

            //getting the Employee type
            char type = line.charAt(0);

            String[] information = line.split("\\s+");
            
            if (type == 'O'){
                String id = information[1];
                String name = information[2];
                double baseSalary = Double.parseDouble(information[3]);

                array[i] = new OfficeWorker(id, name, baseSalary);

            } else if (type == 'T'){
                String id = information[1];
                String name = information[2];
                double baseSalary = Double.parseDouble(information[3]);
                int overtimeHOurs = Integer.parseInt(information[4]);

                array[i] = new Technician(id, name, baseSalary, overtimeHOurs);
            }
        }
        
        //iterating through the employee list
        double totalPay = 0;
        for (int i = 0; i < n; i++){
            Employee employee = array[i];
            double pay = employee.calculatePay();
            System.out.println(employee.getName() + " - " + "Pay: " + pay);
            employee.work();
            System.out.println("\n");
            totalPay += pay;
        }

        System.out.println("Total Pay = "  + totalPay);
    }
}