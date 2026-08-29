//Product (abstract)
interface Notification{
    void send(String message);
}

//Creator (abstract)
abstract class NotificationApp{
    //abstract factory method
    public abstract Notification createNotification();

    //core logic (intact regardless of the subclasses)
    public void notifyUser(String message){
        Notification notification = createNotification();
        notification.send(message);
    }
}

//Concrete Products
class EmailNotification implements Notification {
    @Override 
    public void send(String message){
        System.out.println("Email: ".concat(message));
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String message){
        System.out.println("SMS: ".concat(message));
    }
}

//Concrete creator
class EmailApp extends NotificationApp{
    @Override
    public Notification createNotification(){
        return new EmailNotification();
    }
}

class SmsApp extends NotificationApp{
    @Override
    public Notification createNotification(){
        return new SmsNotification();
    }
}

public class Main{
    public static void main(String[] args){
        NotificationApp emailApp = new EmailApp();
        NotificationApp smsApp = new SmsApp();

        emailApp.notifyUser("This is a message from email");
        smsApp.notifyUser("This is a message from SMS");
    }
}