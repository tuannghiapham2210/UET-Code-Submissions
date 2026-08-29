public class Main{
    public static void main(String[] args){
        MathUtils m = new AdvancedMath();

        System.out.println(m.sum(5, 5));

        //the line below is gonna lead to a compile error
        //explanation: the compiler does not care about the object type (the actual object living in the heap)
        //the compiler only cares about the variable reference type, so it only checks if the MathUtils class has the sum(double doubld) method or not, since it does not -> it throws a compile error
        //System.out.println(m.sum(5.5, 5.5));
    }
}