public class Student{
    //attrinutes (instance variables)
    private String id;
    private String name;
    private String email;
    private double gpa;

    //constructor with four parameters:
    public Student(String id, String name, String email, double gpa){
        this.id = id;
        this.name = name;
        this.email = email;
        this.setGpa(gpa);
    }

    //constructor with 2 parameters (using constructor overloading)
    public Student(String id, String name){
        this(id, name, null, 0.0);
    }

    //constructor with no parameter
    public Student() {}

    //copy constructor
    public Student(Student source){
        this(source.id, source.name, source.email, source.gpa);
    }

    //gpa setter
    public void setGpa(double gpa){
        if (gpa < 0 || gpa > 4){
            System.out.println("Error: could not set new gpa, the number you entered is invalid");
        } else{
            this.gpa = gpa;
        }
    }

    //email setter
    public void setEmail(String email){
        this.email = email;
    }

    //gpa getter
    public double getGpa(){
        return this.gpa;
    }

    //email getter
    public String getEmail(){
        return this.email;
    }

    //display information method
    public void displayInfo(){
        System.out.println("ID: " + this.id + " | Name: " + this.name + " | Email: " + this.email + " | GPA: " + this.gpa);
    }
}