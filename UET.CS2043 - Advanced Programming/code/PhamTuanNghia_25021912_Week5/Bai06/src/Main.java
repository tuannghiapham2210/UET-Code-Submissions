import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            //performing the division
            System.out.println(a / b);

        } catch (InputMismatchException e){
            System.out.println(e);

        } catch (ArithmeticException e){
            System.out.println(e);

        } finally{
            System.out.println("Program finished");
        }
    }
}