import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        //getting the "\n" inside of the scanner buffer
        scanner.nextLine();

        //creating an arraylist
        ArrayList<Employee> array = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++){
            String line = scanner.nextLine();

            char type = line.charAt(0);

            String[] data = line.split("\\s+");

            if (type == 'E'){
                String name = data[1];
                double baseSalary = Double.parseDouble(data[2]);
                array.add(new Employee(name, baseSalary));

            } else if (type == 'D'){
                String name = data[1];
                Double baseSalary = Double.parseDouble(data[2]);
                int overtimeHours = Integer.parseInt(data[3]);
                array.add(new Developer(name, baseSalary, overtimeHours));

            } else if (type == 'T'){
                String name = data[1];
                double baseSalary = Double.parseDouble(data[2]);
                int bugsFound = Integer.parseInt(data[3]);
                array.add(new Tester(name, baseSalary, bugsFound));

            }
        }

        //printing out some newline for a flawless output
        System.out.print("\n\n");

        for (int i = 0; i < n; ++i){
            Employee e = array.get(i);
            if (e instanceof Developer){
                System.out.println(e.getName() + " - " + "Bonus: " + e.calculateBonus());
                System.out.println("Tặng khóa học AWS");

            } else if (e instanceof Tester){
                System.out.println(e.getName() + " - " + "Bonus: " + e.calculateBonus());
                System.out.println("Tặng tool Test");

            } else if (e instanceof Employee){
                System.out.println(e.getName() + " - " + "Bonus: " + e.calculateBonus());
            }

            System.out.print("\n");
        }
    }
}