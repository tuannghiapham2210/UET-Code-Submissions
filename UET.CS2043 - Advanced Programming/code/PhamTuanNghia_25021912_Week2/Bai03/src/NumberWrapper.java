public class NumberWrapper{
    //attributes
    private int value;

    //constructor 
    NumberWrapper(int value){
        this.value = value;
    }

    //public setter
    public void setValue(int value){
        this.value = value;
    }

    //public getter
    public int getValue(){
        return this.value;
    }

    //the one requested (will not work because a, b are just local copies of n1, n2, altering them will not affect n1 and n2)
    public static void swap(NumberWrapper a, NumberWrapper b){
        NumberWrapper temp = a;
        a = b;
        b = temp;
    }

    //the one that will work (directly changing the value inside of the object)
    public static void swap2(NumberWrapper a, NumberWrapper b){
        int temp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(temp);
    }
}