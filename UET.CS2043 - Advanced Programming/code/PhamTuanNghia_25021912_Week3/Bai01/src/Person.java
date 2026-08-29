public class Person {
    protected String name, dob;

    public Person(String name){
        System.out.println("1. Person is created");
    }
}

//creating employee class inherits from person
class Employee extends Person{
    protected double salary;

    //default constructor
    public Employee(String name){
        super(name);
        System.out.println("2. Employee is created");
    }
}

//creating manager class inherits from employee
class Manager extends Employee{
    protected String department;

    //default constructor
    public Manager(String name){
        super(name);
        System.out.println("3. Manager is created");
    }
}