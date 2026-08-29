public abstract class Employee {
    protected String name;
    protected Date birthday;
    protected String id;

    //constructor 
    public Employee(String name){
        this.name = name;
    }

    abstract protected double getSalary();
    abstract protected String getInfo();
}

class FullTimeEmployee extends Employee{
    protected double baseSalary;
    protected double bonus;
    protected double penalty;

    //constructor
    public FullTimeEmployee(String name, double baseSalary, double bonus, double penalty){
        super(name);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.penalty = penalty;
    }

    public double getSalary(){
        return this.baseSalary + (this.bonus - this.penalty);
    } 

    public String getInfo(){
        return this.name + " - " + "Full-time " + "- " + this.getSalary();
    }
}

class PartTimeEmployee extends Employee{
    protected double workingHours;
    protected double hourlyRate;

    //constructor
    public PartTimeEmployee(String name, double workingHours, double hourlyRate){
        super(name);
        this.workingHours = workingHours;
        this.hourlyRate = hourlyRate;
    }

    public double getSalary(){
        return this.workingHours * this.hourlyRate;
    }

    public String getInfo(){
        return this.name + " - " + "Part-time " + "- " + this.getSalary();
    }
}