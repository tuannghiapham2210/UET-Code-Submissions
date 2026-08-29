//problem C: complex conditionals (violated open/closed principle)

//original code:
// public double getArea(String shapeType, double a, double b) { 
// if (shapeType.equals("rectangle")) return a * b; 
// if (shapeType.equals("triangle"))  return 0.5 * a * b; 
// if (shapeType.equals("circle"))    
// return -1; 
// } 

//refactored: used polymorphism
public interface Shape {
    double getArea();
}

class Rectangle implements Shape {
    private double width;
    private double height;

    //constructor
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;

    }

    @Override
    public double getArea(){
        return this.width * this.height;
    }
}

class Triangle implements Shape {
    private double height;
    private double base;

    public Triangle (double height, double base){
        this.height = height;
        this.base = base;

    }

    @Override 
    public double getArea(){
        return 0.5 * this.base * this.height;
    }
}

class Circle implements Shape {
    private double radius;

    public Circle (double radius){
        this.radius = radius;

    }

    @Override
    public double getArea(){
        return 3.14159 * radius * radius;
    }
}
