public class Main {
    public static void main(String[] args) {
        // Generating sample data
        String name = "Nghia Pham";
        double baseSalary = 30000000;
        int workDays = 22;
        int totalDays = 24;
        double taxRate = 0.15;
        double bonus = 5000000;

        // Run Before Refactor
        OldPayroll oldSystem = new OldPayroll();
        oldSystem.printPayroll(name, baseSalary, workDays, totalDays, taxRate, bonus);

        // Run After Refactor (Output should be 100% identical)
        RefactoredPayroll newSystem = new RefactoredPayroll();
        newSystem.printPayroll(name, baseSalary, workDays, totalDays, taxRate, bonus);
    }
}