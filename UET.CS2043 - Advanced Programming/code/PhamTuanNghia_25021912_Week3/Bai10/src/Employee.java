public class Employee {
    //attributes (instance variables)
    private String name;
    private double baseSalary;

    //constructor
    public Employee(String name, double baseSalary){
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double calculateBonus(){
        return 0.1 * baseSalary;
    }

    //public getters/setters
    public double getBaseSalary(){
        return this.baseSalary;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBaseSalary(double baseSalary){
        this.baseSalary = baseSalary;
    }
}