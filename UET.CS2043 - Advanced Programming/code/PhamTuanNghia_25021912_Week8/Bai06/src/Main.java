public class Main {
    public static void main(String[] args) {
        // creating separate objects
        Student student = new Student("25021912", "Phạm Tuấn Nghĩa", 3.2);
        Course course = new Course("INT2204", "Lập trình nâng cao", 3);
        GradeRecord grades = new GradeRecord(8.0, 8.0, 8.0);

        // give it to the manager
        StudentManager manager = new StudentManager(student, course, grades);
        manager.printTranscript();
    }
}