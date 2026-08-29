import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //getting the number of elements inside of the list
        int n = scanner.nextInt();

        //removing the "\n" inside of the scanner buffer
        scanner.nextLine();

        //instantiating an arraylist
        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < n; ++i){
            String line = scanner.nextLine();

            String[] data = line.split("\\s+");

            String id = data[0];
            String name = data[1];
            double gpa = Double.parseDouble(data[2]);

            //adding a new student into the list
            studentList.add(new Student(id, name, gpa));
        }

        //the old way (using anonymous class)
        // studentList.removeIf(new Predicate<Student>(){
        //     @Override
        //     public boolean test(Student student){
        //         return student.getGpa() < 5.0;
        //     }
        // });

        //the new way (using lambda)
        studentList.removeIf((student) -> student.getGpa() < 5);

        //printing neewline for better visual
        System.out.println("\n");

        System.out.println("After removing GPA < 5:");

        for (Student student : studentList){
            System.out.println(student);
        }

        System.out.println("\n");

        //sorting the students (by their names)
        studentList.sort((student1, student2) -> student1.getName().compareTo(student2.getName()));

        System.out.println("After sorting by name:");

        //displaying
        for (Student student : studentList){
            System.out.println(student);
        }

        //using lambda to create four different Operation objects 
        Operation<Double> add = (a, b) -> a + b;
        Operation<Double> multiply = (a, b) -> a * b;
        Operation<Double> divide = (a, b) -> a / b;
        Operation<Double> subtract = (a, b) -> a - b;

    }
}