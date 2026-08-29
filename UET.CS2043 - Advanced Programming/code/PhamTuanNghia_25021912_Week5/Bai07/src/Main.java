import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the source file path: (Enter test.txt)");
        String sourcePath = scanner.nextLine();

        System.out.print("Enter the destination path: ");
        String destPath = scanner.nextLine();

        BufferedReader reader = null;
        PrintWriter writer = null;
        
        try{
            reader = new BufferedReader(new FileReader(sourcePath));
            writer = new PrintWriter(new BufferedWriter(new FileWriter(destPath)));

            int lineCount = 0;
            String line;

            while ((line = reader.readLine()) != null){
                writer.println(line);
                lineCount++;
            }

            System.out.println("Copied " + lineCount + " lines");

        } catch (FileNotFoundException e){
            System.out.println("Source file not found. or Cannot create destination file.");

        } catch (IOException e){
            System.out.println("I/O error.");
            e.printStackTrace();

        } finally{
            try {
                if (reader != null){
                    reader.close();
                }
                if (writer != null){
                    writer.close();
                }

            } catch (IOException e) {
                System.out.println("Error while trying to close the file");
            }

            scanner.close();
        }
    }
}