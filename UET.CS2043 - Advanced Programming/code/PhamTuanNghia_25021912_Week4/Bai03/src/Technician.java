public class Technician extends Employee{
    //attributes
    private int overtimeHours;

    //constructor
    public Technician(String id, String name, double baseSalary, int overtimeHours){
        super(id, name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    //implemeting abstract methods
    @Override
    public double calculatePay(){
        return this.getBaseSalary() + this.overtimeHours * 20000;
    }

    @Override
    public void work(){
        System.out.println("installing device");
    }
}
