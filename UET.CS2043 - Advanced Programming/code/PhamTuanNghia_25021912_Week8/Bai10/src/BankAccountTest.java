import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    //creating a new account with a balance of 500 before each test 

    @BeforeEach
    public void setUp() {
        // runs automatically before each test method to ensure a fresh account state
        account = new BankAccount("123456789", "Nghia Pham", 500.0);
    }

    //checking deposit method

    @Test
    public void testDeposit_ValidAmount() {
        account.deposit(200.0);
        assertEquals(700.0, account.getBalance(), "Nạp 200 vào 500 phải thành 700");
    }

    @Test
    public void testDeposit_Boundary_Zero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0.0);
        });
        assertEquals("Số tiền nạp phải lớn hơn 0.", exception.getMessage());
    }

    @Test
    public void testDeposit_Boundary_Negative() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-0.01);
        });
    }

    //checking withdraw method

    @Test
    public void testWithdraw_ValidAmount_BelowBalance() {
        boolean result = account.withdraw(200.0);
        assertTrue(result, "Rút 200 từ 500 phải thành công");
        assertEquals(300.0, account.getBalance(), "Số dư còn lại phải là 300");
    }

    @Test
    public void testWithdraw_Boundary_ExactBalance() {
        boolean result = account.withdraw(500.0);
        assertTrue(result, "Rút đúng 500 từ 500 phải thành công");
        assertEquals(0.0, account.getBalance(), "Số dư còn lại phải là 0");
    }

    @Test
    public void testWithdraw_Amount_ExceedsBalance() {
        boolean result = account.withdraw(500.01);
        assertFalse(result, "Rút quá số dư phải trả về false");
        assertEquals(500.0, account.getBalance(), "Số dư phải được bảo toàn");
    }

    @Test
    public void testWithdraw_Boundary_Zero() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0.0);
        });
    }

    @Test
    public void testWithdraw_Boundary_Negative() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        });
    }


    
    @Test
    public void testConsistency_SequenceOperations() {
        // creating a brand new account only for this test
        BankAccount sequenceAccount = new BankAccount("999", "Tester", 0.0);
        
        // Step 1: Deposit 500
        sequenceAccount.deposit(500.0);
        assertEquals(500.0, sequenceAccount.getBalance());
        
        // Step 2: Withdraw 200 (should succeed)
        boolean w1 = sequenceAccount.withdraw(200.0);
        assertTrue(w1);
        
        // Step 3: Withdraw 400 (should fail because only 300 left)
        boolean w2 = sequenceAccount.withdraw(400.0);
        assertFalse(w2);
        
        // Step 4: Check final balance (should still be 300)
        assertEquals(300.0, sequenceAccount.getBalance(), "Số dư cuối cùng phải đúng bằng 300");
    }
}
