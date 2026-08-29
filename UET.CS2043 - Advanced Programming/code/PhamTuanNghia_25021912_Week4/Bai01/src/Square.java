public class Square extends Shape{
    //constructor
    public Square(int x, int y){
        super(x, y);
    }

    //implementing draw();
    @Override
    public void draw(){
        System.out.println("Drawing square at: " + "(" + this.x + "," + this.y + ")");
    }

    //implementing erase();
    @Override
    public void erase(){
        System.out.println("Erasing square at: " + "(" + this.x + "," + this.y + ")");
    }
}
