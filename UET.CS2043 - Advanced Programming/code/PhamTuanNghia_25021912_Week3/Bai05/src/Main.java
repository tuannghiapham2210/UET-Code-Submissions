import java.util.Scanner;

public class Main{
    public static void main(String[] agrs){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of employees: ");
        int n = scanner.nextInt();

        Employee[] array = new Employee[n];

        //to read the remaining "/n" inside of the scanner's buffer
        scanner.nextLine();

        for (int i = 0; i < n; i++){
            //reading the line
            String line = scanner.nextLine();

            //getting the type of employee
            char type = line.charAt(0);

            //extracting the names by finding the quotes
            int firstQuote = line.indexOf('"');
            int lastQuote = line.lastIndexOf('"');

            //getting the name between two quotes
            String name = line.substring(firstQuote + 1, lastQuote);

            //extracting the numbers after the last quote
            String numPart = line.substring(lastQuote + 1).trim();
            String[] numbers = numPart.split("\\s+");

            //building the object based on the type
            if (type == 'F'){
                double baseSalary = Double.parseDouble(numbers[0]);
                double bonus = Double.parseDouble(numbers[1]);
                double penalty = Double.parseDouble(numbers[2]);

                array[i] = new FullTimeEmployee(name, baseSalary, bonus, penalty);

            } else if (type == 'P'){
                double workingHours = Double.parseDouble(numbers[0]);
                double hourlyRate = Double.parseDouble(numbers[1]);

                array[i] = new PartTimeEmployee(name, workingHours, hourlyRate);
            }
        }

        //printing out the results
        for (int i = 0; i < n; i++){
            System.out.println(array[i].getInfo());
        }
    }
}