public class Student implements Comparable<Student> {
    private String name;
    private double gpa;
    private String id;

    //constructor
    public Student(String name, double gpa, String id){
        this.name = name;
        this.gpa = gpa;
        this.id = id;
    }
    
    public int compareTo(Student student){
        if (this.gpa > student.gpa){
            return 1;

        } else if (this.gpa < student.gpa){
            return -1;
        }

        return 0;
    }

    @Override
    public String toString(){
        return this.name + " - " + this.gpa;
    }
}
