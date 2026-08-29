import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your input: ");
        String line = scanner.nextLine();

        String[] input = line.split("\\s+");

        char type = input[0].charAt(0);
        
        int numNights = Integer.parseInt(input[1]);

        Room room = null;

        if (type == 'S'){
            room = new Standard(numNights);

        } else if (type == 'V'){
            room = new Vip(numNights);
        }

        if (room != null){
            System.out.println((long) room.getPrice());
        }
    }    
}
