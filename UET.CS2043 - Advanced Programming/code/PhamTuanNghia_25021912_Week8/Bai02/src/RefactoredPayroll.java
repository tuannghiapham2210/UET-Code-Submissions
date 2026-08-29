public class RefactoredPayroll {
    //declaring constants for better readability and for modification in the future
    private static final double SOCIAL_INSURANCE_RATE = 0.08;
    private static final double HEALTH_INSURANCE_RATE = 0.015;
    private static final double PERSONAL_DEDUCTION = 11000000;

    private static final double TAX_BRACKET_1 = 5000000;
    private static final double TAX_BRACKET_2 = 10000000;
    private static final double TAX_RATE_TIER_1 = 0.05;
    private static final double TAX_RATE_TIER_2 = 0.10; 

    private static final double MAX_TAX_TIER_1 = 250000; 
    private static final double MAX_TAX_TIER_2 = 750000;
    
    //helper methods
    private double calculateActualSalary(double baseSalary, int workDays, int totalDays){
        double actualSalary = baseSalary * workDays / totalDays;
        return actualSalary;
    }    

    private double calculateInsurance(double actualSalary){
        double insurance = actualSalary * (SOCIAL_INSURANCE_RATE + HEALTH_INSURANCE_RATE);
        return insurance; 
    }

    private double calculateTax(double actualSalary, double insurance, double marginalTaxRate){
        double taxableIncome = actualSalary - insurance - PERSONAL_DEDUCTION;
        double tax = 0;

        if (taxableIncome <= 0){
            return 0;
        }

        if (taxableIncome <= TAX_BRACKET_1){
            tax = taxableIncome * TAX_RATE_TIER_1;
            return tax;
        }

        else if (taxableIncome <= TAX_BRACKET_2){
            tax = MAX_TAX_TIER_1 + (taxableIncome - TAX_BRACKET_1) * TAX_RATE_TIER_2;
            return tax;
        }

        else {
            tax = MAX_TAX_TIER_2 + (taxableIncome - TAX_BRACKET_2) * marginalTaxRate;;
            return tax;
        }
    }

    public void printPayroll(String name, double baseSalary, int workDays, int totalDays, double taxRate, double bonus){
        double actualSalary = calculateActualSalary(baseSalary, workDays, totalDays);
        double insurance = calculateInsurance(actualSalary);
        double tax = calculateTax(actualSalary, insurance, taxRate);
        double netSalary = actualSalary - insurance - tax + bonus;

        System.out.println("Lương cơ bản: " + baseSalary); 
        System.out.println("Ngày công: " + workDays + "/" + totalDays); 
        System.out.println("Lương thực tế: " + actualSalary); 
        System.out.println("Bảo hiểm: " + insurance); 
        System.out.println("Thuế TNCN: " + tax); 
        System.out.println("Thưởng: " + bonus); 
        System.out.println("Thực nhận: " + netSalary); 
        System.out.println();
    }
}