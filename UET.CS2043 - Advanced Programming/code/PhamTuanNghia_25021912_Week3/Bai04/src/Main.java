public class Main{
    public static void main(String[] args){
        //upcasting (safe)
        Animal a = new Dog();

        //downcasting (risky)
        //Cat c = (Cat) a; -> this is going to cause ClassCastException

        //calling method
        //c.makeSound(); 

        //safe way to downcast
        if (a instanceof Cat){
            Cat c = (Cat) a;
            c.makeSound();
        } else{
            System.out.println("Đây không phải là Mèo!");
        }
    }
}