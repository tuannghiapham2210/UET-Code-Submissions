import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        //to get the "\n" inside of the scanner buffer
        scanner.nextLine();

        //creating a generic Robot array
        Robot[] array = new Robot[n];

        for (int i = 0; i < n; ++i){
            String line = scanner.nextLine();

            String[] information = line.split("\\s+");

            String type = information[0];
            int id = Integer.parseInt(information[1]);
            String modelName = information[2];

            if (type.equals("DR")){
                array[i] = new DroneRobot(id, modelName);

            } else if (type.equals("FR")){
                array[i] = new FishRobot(id, modelName);

            } else if (type.equals("AR")){
                array[i] = new AmphibiousRobot(id, modelName);

            }
        }

        //iterating through the array and call method performMainTask()
        //dynamic binding will handle all the method calls correctly
        for (int i = 0; i < n; i++){
            array[i].performMainTask();
            System.out.print("\n\n");
        }

        //using instanceof operator to check the type before downcasting (to downcast safely)
        for (int i = 0; i < n; ++i){
            Robot current = array[i];

            if (current instanceof Flyable){
                Flyable flyRobot = (Flyable) current;
                flyRobot.fly();
            } else if (current instanceof Swimmable){
                Swimmable swimRobot = (Swimmable) current;
                swimRobot.swim();
            }
        }
    }
}

//answering the questions on the assignment
//question 1: "Lấy một đối tượng Robot (nhưng thực chất là DroneRobot) ra khỏi danh sách. Thử gọi hàm fly(). Có gọi được không?"
//answer: this gonna lead to compile error because when compiling your .java code, the compiler (javac) only cares about the variable reference type (it does not care about the actual object living in the heap)
//therefore, it sees the variable reference type is robot, and robot does not have the fly() meothod. Therefore, this will lead to a compile error


//question 2: "Giả sử bạn có thêm một lớp trừu tượng nữa là ElectronicDevice (Thiết bị điện tử) có phương thức turnOn(). Thử cho DroneRobot kế thừa cả Robot và ElectronicDevice. Kết quả là gì?
//answer: java does not support multiple inheritance of classes. A java class can only extend a single parent class, this helps evade the "diamond problem"

//question 3: "Thay vì để ElectronicDevice là lớp trừu tượng, hãy chuyển nó thành Interface. Bây giờ hãy cho DroneRobot vừa extends Robot vừa implements Flyable, GPS, ElectronicDevice. Kết quả thế nào?"
//answer: java will let you do this, this is perfectly valid and your program will not crash