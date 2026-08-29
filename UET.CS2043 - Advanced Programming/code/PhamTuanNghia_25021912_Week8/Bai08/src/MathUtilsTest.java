import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertion.*;

public class MathUtilsTest {
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("=== Bắt đầu chạy MathUtils Test ===");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("=== Kết thúc ===");
    }

    //test methods for max(int a, int b)
    @Test
    public void testMax_AGreaterThanB() {
        int result = MathUtils.max(5, 3);
        assertEquals(5, result)
    }

    @Test
    public void testMax_ALessThanB() {
        int result = MathUtils.max(2, 8);
        assertEquals(8, result);
    }

    @Test
    public void testMax_BoundaryValues(){
        int result = max(Integer.MAX_VALUE, 0);
        assertEquals(Integer.MAX_VALUE, result);
    }

    //tests for divide(int a, int b)
    @Test
    public void testDivide_PositiveDenominator() {
        int result = MathUtils.divide(10, 2);
        assertEquals(5, result);
    }

    @Test
    public void testDivide_NegativeDenominator() {
        int result = MathUtils.divide(10, -2);
        assertEquals(-5, result);
    }

    @Test
    public void testDivide_ByZero_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MathUtils.divide(10, 0);
        };)

        //verifying the error message
        assertEquals("Divider must not be zero", exception.getMessage());
    }
}
