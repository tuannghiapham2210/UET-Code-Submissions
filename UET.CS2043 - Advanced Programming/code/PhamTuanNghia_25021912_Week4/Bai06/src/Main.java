public class Main{
    public static void main(String[] args) {
        //instantiating an array of integers
        Integer[] IntegerArray = {5, 1, 3, 2, 4, 6, 7, 9, 8};

        //instantiating an array of Strings
        String[] StringArray = {"Java", "C++", "Python"};
        
        //creating some students
        Student student1 = new Student("A", 4.0, "123");
        Student student2 = new Student("B", 3.8, "234");
        Student student3 = new Student("C", 3.6, "456");

        //creating a student array
        Student[] StudentArray = {student1, student2, student3};

        //sorting
        ArrayUtils.sort(IntegerArray);
        ArrayUtils.sort(StringArray);
        ArrayUtils.sort(StudentArray);

        for (int x : IntegerArray){
            System.out.print(x + " ");
        }

        System.out.println("\n");

        for (String x : StringArray){
            System.out.print(x + " ");
        }
        
        System.out.println("\n");

        for (Student student : StudentArray){
            System.out.println(student);
        }
    }
}