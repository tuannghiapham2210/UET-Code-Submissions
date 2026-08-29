public abstract class Shape {
    //instance variable (attributes)
    protected int x, y;

    //constructor
    public Shape(int x, int y){
        this.x = x;
        this.y = y;
    }

    //abstract methods (sub-classes must provide specific implementations)
    public abstract void draw();
    public abstract void erase();

    //normal method
    public void moveTo(int newX, int newY){
        //erasing at the old coordinates
        this.erase();

        //updating new coordinates
        this.x = newX;
        this.y = newY;

        //drawing at new coordinates
        this.draw();
    }
}