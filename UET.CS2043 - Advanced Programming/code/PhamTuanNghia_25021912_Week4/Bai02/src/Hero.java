public class Hero extends ActionCharacter implements CanFly, CanSwim, CanFight{
    //implementing abstract methods of interfaces
    @Override
    public void fly(){
        System.out.println("Flying...");
    }

    @Override
    public void swim(){
        System.out.println("Swimming...");
    }
}
