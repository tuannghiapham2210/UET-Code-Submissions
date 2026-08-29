public class OfficeWorker extends Employee{
    //constructor (explicit)
    public OfficeWorker (String id, String name, double baseSalary){
        super(id, name, baseSalary);
    }

    @Override
    public void work(){
        System.out.println("drafting a document");
    }

    @Override
    public double calculatePay(){
        return this.getBaseSalary();
    }
}
