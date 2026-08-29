public class MathUtils {
    //creating a function that returns the sum of two integers
    public int sum(int a, int b){
        return a + b;
    }
}

//creating an advancedmath class inherits from mathutils
class AdvancedMath extends MathUtils{
    //overrding sum(int a, int b) method
    public int sum(int a, int b){
        return a + b + 10;
    }

    //overloading the sum() method (using different parameters)
    public double sum(double a, double b){
        return a + b;
    }
}


