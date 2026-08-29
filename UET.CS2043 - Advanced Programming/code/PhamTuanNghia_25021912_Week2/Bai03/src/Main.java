public class Main {
    public static void main(String[] args){
        //instantiating an object
        NumberWrapper n1 = new NumberWrapper(5);
        NumberWrapper n2 = new NumberWrapper(10);

        //testing the first swap method (followed the instructions)
        NumberWrapper.swap(n1, n2);

        //printing out the results
        System.out.println(n1.getValue());
        System.out.println(n2.getValue());
        System.out.println("does not work because a, b are just local copies of n1, n2, altering them will not affect n1 and n2");
        System.out.println("the correct solution can be found inside of my code");
    }
}
