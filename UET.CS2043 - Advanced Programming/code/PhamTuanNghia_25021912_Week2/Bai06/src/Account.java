import java.util.Arrays;

public class Account {
    //attributes (instance variables)
    private String accountId;
    private double balance;
    private Transaction[] history;
    private int transactionCount = 0;

    //constructor for an account
    public Account(String accountId, double balance){
        this.accountId = accountId;
        this.balance = balance;
        this.history = new Transaction[100];
        this.transactionCount = 0;
    }

    //method to add a transaction into the history
    public void addTransaction(Transaction t){
        if(this.transactionCount < this.history.length){
            history[transactionCount] = t;
            ++transactionCount;
        } else{
            System.out.println("Error: Transaction history is full");
        }
    }

    //method to get the transaction history
    public Transaction[] getHistory(){
        return Arrays.copyOf(this.history, transactionCount);
    }
}