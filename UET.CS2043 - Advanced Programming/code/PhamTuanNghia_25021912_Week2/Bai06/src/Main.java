public class Main {
    public static void main(String[] args){
        //instatiating an Account
        Account account = new Account("1234", 100.0);

        //instantiating a transaction
        Transaction transaction = new Transaction("123", "20.0", "8/3/2026");

        //adding a transaction
        account.addTransaction(transaction);

        //exploiting 
        Transaction[] exploit = account.getHistory();

        exploit[0] = null;

        //testing if the original array is intact
        Transaction[] testArray = account.getHistory();
        
        //checking if the first element of the array is still intact
        System.out.println(testArray[0].getTransactionID());
        System.out.println(testArray[0].getAmount());
        System.out.println(testArray[0].getTimestamp());
        
    }
}
