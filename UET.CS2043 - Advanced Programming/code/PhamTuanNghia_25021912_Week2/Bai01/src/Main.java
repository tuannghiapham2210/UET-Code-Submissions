public class Main {
    public static void main(String args[]){
        //instantiating a BankAccount object:
        BankAccount account = new BankAccount("12345", "TuanNghia", 100);

        //depositing a negative amount of money:
        account.deposit(-100);

        //withdrawing an amount that is larger than the current balance
        account.withdraw(100000);

        //withdrawing a valid amount of money
        account.withdraw(10);
    }
}
