public class Student extends Person {
    private double gpa;

    //constructor
    public Student(String id, String name, double gpa){
        super(id, name);
        this.gpa = gpa;
    }

    public double getGga(){
        return gpa;
    }

    @Override
    public String toString(){
        return "Sinh viên: " + this.getName() + " (" + this.getId() + ")";
    }
}

class TeachingAssistant extends Person{
    private double salary;

    public TeachingAssistant(String id, String name, double salary){
        super(id, name);
        this.salary = salary;
    }

    public double getSalary(){
        return salary;
    }
}

class Course {
    private String courseId;
    private String courseName;
    private int credits;

    //constructor
    public Course(String courseId, String courseName, int credits){
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;

    }

    public String getCourseId(){
        return courseId;
    }

    public String getCourseName(){
        return courseName;
    }

    public int getCredits(){
        return credits;
    }

    @Override
    public String toString() {
        return "Môn học: " + courseName + " (" + courseId + ") - " + credits + " tín chỉ";
    }
}

class GradeRecord {
    private double midtermScore;
    private double finalScore;
    private double assignmentScore;

    //constructor
    public GradeRecord(double midtermScore, double finalScore, double assignmentScore){
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
        this.assignmentScore = assignmentScore;

    }

    //getters
    public double getAssignmentScore() {
        return assignmentScore; 
    }
    public double getMidtermScore() { 
        return midtermScore; 
    }
    public double getFinalScore() { 
        return finalScore;
    }

    //method to calculate the final grade
    public double calculateFinalGrade(){
        return assignmentScore * 0.2 + midtermScore * 0.3 + finalScore * 0.5;
    }

    //method to get the academic status of the student
    public String getAcademicStatus() {
        double grade = calculateFinalGrade();
        if (grade >= 8.5) return "Giỏi";
        if (grade >= 7.0) return "Khá";
        if (grade >= 5.5) return "Trung bình";
        return "Yếu";
    }

    @Override
    public String toString(){
        return "Điểm GK: " + midtermScore + " | Điểm CK: " + finalScore + " | Điểm BT: " + assignmentScore + "\n" + String.format("Điểm tổng kết: %.1f - Học Lực: %s%n", calculateFinalGrade(), getAcademicStatus());
    }
}

class StudentManager {
    private Student student;
    private Course course;
    private GradeRecord gradeRecord;

    public StudentManager(Student student, Course course, GradeRecord gradeRecord){
        this.student = student;
        this.course = course;
        this.gradeRecord = gradeRecord;

    }

    public void printTranscript() {
        System.out.println(student);
        System.out.println(course);
        System.out.println(gradeRecord);
    }
}