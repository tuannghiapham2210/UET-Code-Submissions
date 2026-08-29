public class Main {
    public static void main(String[] args){
        //instatiating the first employee (1/1/2000)
        MyDate birthday1 = new MyDate( 1, 1, 2000);
        Employee emp1 = new Employee("Employee1", birthday1);
        
        //instantiating the second employee using deep copy
        Employee emp2 = new Employee(emp1);

        //altering the birthday of the first employee to 2/2/2022 (DIRECTLY)
        emp1.updateBirthYear(2, 2, 2022);

        //printing out the birthday of the second employee
        System.out.println(emp2.getBirthday().displayDate());
    }
}
