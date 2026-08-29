import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the binary file name: (Enter numbers.bin) ");
        String fileName = scanner.nextLine();

        System.out.print("How many numbers? ");
        int n = scanner.nextInt();

        //opening the binary output stream
        try (DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(fileName))) {
            
            System.out.println("enter " + n + " integers:");
            for (int i = 0; i < n; i++) {
                int number = scanner.nextInt();
                //writing the data into the file
                dataOut.writeInt(number); 
            }

        } catch (IOException e) {
            System.out.println("Error while writing.");

        }

        //reading the data (retrieving)
        //opening the binary input stream
        try (DataInputStream dataIn = new DataInputStream(new FileInputStream(fileName))) {
            System.out.print("The numbers that you just entered are: ");
            //reading until EOFException is thrown
            while (true) {
                int number = dataIn.readInt();
                System.out.print(number + " ");
            }

        } catch (EOFException e) {
            System.out.println("\nreached the end of the file");

        } catch (IOException e) {
            System.out.println("Error while reading.");

        }

        scanner.close();
    }
}