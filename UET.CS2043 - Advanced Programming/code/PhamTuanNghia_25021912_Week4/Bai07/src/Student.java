public class Student {
    private String id;
    private String name;
    private double gpa;

    //constructor
    public Student(String id, String name, double gpa){
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    //public getters
    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getGpa(){
        return this.gpa;
    }

    //overriding toString method for a better visual
    @Override 
    public String toString(){
        return this.getId() + " " + this.getName() + " " + this.getGpa();
    }
}
