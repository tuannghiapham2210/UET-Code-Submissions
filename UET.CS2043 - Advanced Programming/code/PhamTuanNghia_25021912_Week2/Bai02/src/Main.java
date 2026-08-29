public class Main {
    public static void main(String[] args){
        //instantiating three students and try assigning negative gpas
        Student student1 = new Student();

        Student student2 = new Student("25021912", "TuanNghia");

        Student student3 = new Student("25021912", "TuanNghia", "25021912@vnu.edu.vn", 4.0);

        student1.setGpa(-1);
        student1.displayInfo();
        student2.setGpa(-1);
        student2.displayInfo();
        student3.setGpa(-1);
        student3.displayInfo();
    }
}
