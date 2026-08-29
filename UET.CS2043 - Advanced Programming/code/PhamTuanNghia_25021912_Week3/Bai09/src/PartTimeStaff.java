public class PartTimeStaff extends Staff{
    //attributes 
    private int workingHours;
    private double hourlyRate;

    //constructor
    public PartTimeStaff(String id, String name, int workingHours, double hourlyRate){
        super(id, name);
        this.workingHours = workingHours;
        this.hourlyRate = hourlyRate;
    }

    //implementing getPaymentAmount
    @Override
    public double getPaymentAmount() {
        return workingHours * hourlyRate;
    }

    public String getPaymentDetails(){
        return "PartTimeStaff " + this.getName() + " - " + "Payment: " + this.getPaymentAmount();
    }
}
