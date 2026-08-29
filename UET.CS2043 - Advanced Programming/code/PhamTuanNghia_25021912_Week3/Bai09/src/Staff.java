public abstract class Staff implements IPayable {
    //instance variables (attributes)
    private String id;
    private String name;

    //constructor
    public Staff(String id, String name){
        this.id = id;
        this.name = name;
    }

    //public getters/setters
    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    //not overriding getPaymentAmount(); (delegating to the child classes)
}   