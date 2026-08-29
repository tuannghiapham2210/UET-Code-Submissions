public class OldPayroll {
    public void printPayroll(String name, double baseSalary, int workDays, int totalDays, double taxRate, double bonus) {
        System.out.println("=== BẢNG LƯƠNG (TRƯỚC REFACTOR) ===");
        System.out.println("Nhân viên: " + name);
        double actualSalary = baseSalary * workDays / totalDays;
        double insurance = actualSalary * 0.08 + actualSalary * 0.015;
        double taxableIncome = actualSalary - insurance - 11000000;
        double tax = 0;
        if (taxableIncome > 0) {
            if (taxableIncome <= 5000000) tax = taxableIncome * 0.05;
            else if (taxableIncome <= 10000000) tax = 250000 + (taxableIncome - 5000000) * 0.10;
            else tax = 750000 + (taxableIncome - 10000000) * taxRate;
        }
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