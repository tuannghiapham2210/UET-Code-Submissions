interface Notifier {
    void send(String message);

}

//the innermost layer (default behavor)
class EmailNotifier implements Notifier{
    @Override
    public void send(String message){
        System.out.println("Email: ".concat(message));
    }
}

//abstract class for decorators
abstract class NotifierDecorator implements Notifier{
    //holding a Notifer inside
    protected Notifier component;

    //constructor
    public NotifierDecorator(Notifier n){
        component = n;
    }

    @Override
    public void send(String message){
        component.send(message); //delegates it to the component inside
    }
}

//creating decorators
class SMSNotifier extends NotifierDecorator{
    //constructor
    public SMSNotifier(Notifier n){
        super(n);
    }

    @Override
    public void send(String message){
        super.send(message); //delegates the work to the innerclass first
        System.out.println("SMS: ".concat(message));
    }
}

class FacebookNotifier extends NotifierDecorator{
    //constructor
    public FacebookNotifier(Notifier n){
        super(n);
    }

    @Override
    public void send(String message){
        super.send(message);
        System.out.println("Facebook: ".concat(message));
    }
}

public class Main{
    public static void main(String[] args){
        Notifier notifier = new EmailNotifier();
        notifier = new SMSNotifier(notifier);
        notifier = new FacebookNotifier(notifier);

        notifier.send("hello, this is a notification");
    }
}