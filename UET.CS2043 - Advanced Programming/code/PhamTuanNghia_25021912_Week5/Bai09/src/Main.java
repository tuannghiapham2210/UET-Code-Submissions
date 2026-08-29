import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable{
    //attributes (instance variables)
    protected String id;
    protected String name;
    protected double gpa;

    //constructor
    public Student(String id, String name, double gpa){
        this.id = id;
        this.name = name;
        this.gpa = gpa;

    }

    @Override 
    public String toString(){
        return "ID: " + this.id + " | " + "Name: " + this.name + " | " + "Gpa: " + this.gpa;
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> studentList = new ArrayList<>();

        //loops until meeting the "END" keyword
        while (true){
            try{
                System.out.print("Enter the id: ");
                
                String id = scanner.nextLine();
                if (id.equals("END")){
                    break;
                }

                System.out.print("Enter the name: ");
                String name = scanner.nextLine();
                if (name.equals("END")){
                    break;
                }

                System.out.print("Enter the gpa: ");
                String val = scanner.nextLine();
                if (val.equals("END")){
                    break;
                }
                double gpa = Double.parseDouble(val);

                studentList.add(new Student(id, name, gpa));
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please try again");

            }
        }

        //Serializing (Ram -> Hard Drive)
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.bin"))){
            //writing the entire ArrayList to the binary file
            out.writeObject(studentList);

        } catch (IOException e){
            e.printStackTrace();
            
        }

        //Deserializing (Hard drive -> ram)
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("test.bin"))){
            @SuppressWarnings("unchecked")
            List<Student> restoredList = (ArrayList<Student>) in.readObject();

            System.out.println("The retrieved student list is: ");
            for (Student student : restoredList){
                System.out.println(student);

            }

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }   
}

