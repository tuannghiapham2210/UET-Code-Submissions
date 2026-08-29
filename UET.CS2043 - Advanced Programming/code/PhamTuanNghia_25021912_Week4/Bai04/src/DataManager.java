public class DataManager implements IData {
    //the code below will lead to compile error because when you dont explicitly declare an access modifier, it defaults to package-private
    //therefore, the access modifers wont match so the compiler javac wont compile your code
    // void show(){
    //     System.out.println("Show data");
    // }

    //the correct way to do it
    public void show(){
        System.out.println("Show data");
    }
}
