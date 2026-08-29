class BankAccount {
    private int balance;

    //constructor
    public BankAccount(){
        this.balance = 0;
    }

    public synchronized void deposit(int amount){
        this.balance += amount;
    }

    public synchronized void withdraw(int amount){
        this.balance -= amount;
    }

    public int getBalance(){
        return this.balance;
    }
}

public class Main {
    public static void main(String[] args){
        BankAccount account = new BankAccount();
        
        Runnable taskA = () -> {
            for (int i = 0; i < 1000; ++i){
                account.deposit(100);
            }
        };

        Runnable taskB = () -> {
            for (int i = 0; i < 1000; i++){
                account.withdraw(100);
            }
        };

        Thread threadA = new Thread(taskA);
        Thread threadB = new Thread(taskB);

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        System.out.println("Final balance: " + account.getBalance());
    }
}