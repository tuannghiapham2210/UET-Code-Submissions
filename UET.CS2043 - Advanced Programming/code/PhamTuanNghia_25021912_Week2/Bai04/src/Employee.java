public class Employee{
    //instance variables
    private String name;
    private MyDate birthday;

    //constructor for an employee
    public Employee(String name, MyDate birthday){
        this.name = name;
        this.birthday = new MyDate(birthday);
    }

    //copy constructor for an employee  
    public Employee(Employee source){
        this.name = source.name;
        this.birthday = new MyDate(source.birthday);
    }

    //method to get the birthday of an employee
    public MyDate getBirthday(){
        return new MyDate(this.birthday);
    }

    //method to directly mutate the birthday of an employee
    public void updateBirthYear(int day, int month, int year){
        birthday.setDay(day);
        birthday.setMonth(month);
        birthday.setYear(year);
    }
}
