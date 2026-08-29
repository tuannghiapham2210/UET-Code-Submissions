public class Main {
    public static void main(String[] args) {
        System.out.println("Test cases are scrutinized inside of readme file");
        System.out.println("Checking (Fixed version):");
        System.out.println("GPA 4.9: " + GradeClassifier.classifyGrade(4.9)); // Yếu
        System.out.println("GPA 5.0: " + GradeClassifier.classifyGrade(5.0)); // Trung bình
        System.out.println("GPA 6.5: " + GradeClassifier.classifyGrade(6.5)); // Khá
        System.out.println("GPA 8.0: " + GradeClassifier.classifyGrade(8.0)); // Giỏi
    }
}