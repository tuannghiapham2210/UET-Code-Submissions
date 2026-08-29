import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main{
    public static void main(String[] args) {
        //simulating the scenario in the assignment

        SupportSystem shopee = new SupportSystem();
        
        //customer A and B comes
        Customer A = new Customer("C01", "Tuannghia", new Ticket("A01", "Can't order"));
        Customer B = new Customer("C02", "PhamTuanNghia", new Ticket("A02", "Slow dilivery"));
        shopee.addCustomer(A);
        shopee.addCustomer(B);

        //processing the first customer
        shopee.processNextCustomer();

        //typing 3 messages 
        shopee.typeMessage("Hello, how can i help you?");
        shopee.typeMessage("This product is currently out of stock");
        shopee.typeMessage("See you later");

        //undo 1 line
        shopee.undoMessage();

        //peeking
        shopee.viewLast();

        //processing the last customer
        shopee.processNextCustomer();
    }
}

class Customer{
    //attributes
    protected String id;
    protected String name;
    Ticket ticket;

    //constructor
    public Customer(String id, String name, Ticket ticket){
        this.id = id;
        this.name = name;
        this.ticket = ticket;
    }

    @Override
    public String toString(){
        return this.name + " (ID: " + this.id + ") " + this.ticket;
    }
}

class Message{
    //attributes
    protected String id;
    protected String content;

    //constructor
    public Message(String id, String content){
        this.id = id;
        this.content = content;
    }

    @Override 
    public String toString(){
        return this.content; 
    }
}

class Ticket{
    //attributes
    String id;
    String content;
    LocalDateTime timestamp;

    //constructor
    public Ticket(String id, String content){
        this.id = id;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = timestamp.format(formatter);
        return "Ticket id: " + this.id + " Time created: " + formattedTime;
    }
}

class SupportSystem{
    Queue<Customer> customerQueue = new LinkedList<>();
    Stack<Message> messageHistory = new Stack<>();

    int messageID = 0;

    public void addCustomer(Customer customer){
        customerQueue.add(customer);
        System.out.println("Customer: " + customer + " has been added to the queue");

    }

    public void processNextCustomer(){
        if (customerQueue.isEmpty()){
            System.out.println("no customer left, can't process");

        } else{
            Customer currentCustomer = customerQueue.poll();
            System.out.println("Processing customer: " + currentCustomer);

            messageHistory.clear();
        }
    }

    public void typeMessage(String content){
        //creating a new message object
        Message message = new Message("ID" + ++messageID, content);
        messageHistory.push(message);
        System.out.println("Sent: " + message);
    }

    public void undoMessage(){
        if (messageHistory.isEmpty()){
            System.out.println("Message history is empty, can't undo");

        } else{
            Message removedMessage = messageHistory.pop();
            System.out.println("Removed: " + removedMessage);

        }
    }

    public void viewLast(){
        if (messageHistory.isEmpty()){
            System.out.println("Message history is empty, can't view");

        } else{
            Message lastMessage = messageHistory.peek();
            System.out.println("Last message: " + lastMessage);

        }
    }
}