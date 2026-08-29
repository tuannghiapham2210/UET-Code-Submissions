public class Main{
    public static void main(String[] args) {
        //instantiating a hero
        Hero myHero = new Hero();

        //safe down-casting
        if (myHero instanceof CanSwim){
            CanSwim heroSwim = (CanSwim) myHero;  
            heroSwim.swim();

        } 
        
        if (myHero instanceof CanFight){
            CanFight heroFight = (CanFight) myHero;
            heroFight.fight();
        }
    }
}

//to answer the question in the assignment, whether or not Hero has to implement its own version of fight(), depends entirely on the access modifiers of fight() method of the parent class (in this specific case)
//if it is just simply void fight(), and you did not declare an access modifier, it defaults to package-private, therefore access modifiers of the abstract method in the interface and the parent class
//are different at this point, and in java, you cannot reduce the visibility of an overriden method, therefore you would have to implement your own, or simply like i did, you just have to add a public
//access modifier to the method of the parent