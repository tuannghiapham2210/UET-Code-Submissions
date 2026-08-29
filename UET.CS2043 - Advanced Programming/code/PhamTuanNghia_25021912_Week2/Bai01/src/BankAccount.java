public class BankAccount{
    //attributes
    private final String accountNumber;
    private double balance;
    private String ownerName;

    //first constructor for a bank account
    public BankAccount(String accountNumber, String ownerName, double balance){
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;

        if (balance < 0){
            this.balance = 0;
            System.out.println("Error: the balance cannot be negative");
        } else{
            this.balance = balance;
        }
    }

    //second constructor for a bank account
    public BankAccount(String accountNumber, String ownerName){
        this(accountNumber, ownerName, 0.0);
    }

    //method to deposit money
    public void deposit(double amount){
        if (amount <= 0){
            System.out.println("Invalid amount, can't deposit");
        } else{
            this.balance += amount;
        }
    }

    //method to withdraw money
    public boolean withdraw(double amount){
        if (amount <= 0){
            System.out.println("Error: Invalid amount, please enter a valid amount!");
            return false;

        } else if (amount > this.balance){
            System.out.println("Error: you do not have enough money");
            return false;

        } else{
            this.balance -= amount;
            System.out.println("Successfully withdrew, your balance is: " + this.balance);
            return true;
        }
    }

    //method to get the balance while keeping it intact
    public double getBalance(){
        return this.balance;
    }
}