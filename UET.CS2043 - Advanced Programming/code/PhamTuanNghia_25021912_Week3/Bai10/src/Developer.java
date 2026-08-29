public class Developer extends Employee{
    //attributes
    private int overtimeHours;

    //constructor
    public Developer(String name, double baseSalary, int overtimeHours){
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculateBonus(){
        return (0.1 * this.getBaseSalary()) + (this.overtimeHours * 200000);
    }
}
