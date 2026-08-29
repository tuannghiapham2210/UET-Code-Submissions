public class Transaction {
    //attributes (instance variables)
    private final String transactionID;
    private final String amount;
    private final String timestamp;

    //constructor
    public Transaction(String transactionID, String amount, String timestamp){
        this.transactionID = transactionID;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    //getter methods for the purpose of reading only
    public String getTransactionID(){
        return this.transactionID;
    }

    public String getAmount(){
        return this.amount;
    }

    public String getTimestamp(){
        return this.timestamp;
    }
}
