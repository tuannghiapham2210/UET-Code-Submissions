public class Animal {
    public void makeSound(){
        System.out.println("Animal sound");
    }
}

//creating class dog inherits from Animal class
class Dog extends Animal{
    //overriding makesound() method of the super class
    public void makeSound(){
        System.out.println("Woof woof");
    }
}

//creating class cat inherits from animal class
class Cat extends Animal{
    //overriding
    public void makeSound(){
        System.out.println("Meows meows");
    }
}

class Duck extends Animal{
    
}

