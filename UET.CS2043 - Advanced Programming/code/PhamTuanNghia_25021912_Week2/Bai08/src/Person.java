public class Person {
    //attributes
    private String name;
    private Person me;

    //constructor
    public Person(String name){
        this.name = name;
    }

    public void setMe(Person other){
        me = other;
    }

    public Person getMe(){
        return this.me;
    }

    String getName(){
        return this.name;
    }

    

}
