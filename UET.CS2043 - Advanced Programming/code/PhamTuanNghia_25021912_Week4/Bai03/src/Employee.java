public abstract class Employee implements IWorkable {
    //attributes (instance variables)
    private String id;
    private String name;
    private double baseSalary;

    //constructor
    public Employee(String id, String name, double baseSalary){
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    //abstract method
    public abstract double calculatePay();

    //public getters
    public String getName(){
        return this.name;
    }

    public double getBaseSalary(){
        return this.baseSalary;
    }
}

//class Employee does not have to implement the work() method of the interface that it implements
//because Employee is an abstract class (it is also a template), so the Employee object itself cannot be instantiated
//therefore, there is no risk of accessinng a method that has not been implemented
//however, if a concrete class extends from the Employee class, it must implement the work() abstract method
//because since it is a concrete class, an object can be instantiated and therefore it can literally access a method that has not been initialized.