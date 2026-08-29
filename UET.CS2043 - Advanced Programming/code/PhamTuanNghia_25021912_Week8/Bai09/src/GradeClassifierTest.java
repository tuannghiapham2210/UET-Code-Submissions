import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeClassifierTest {
    //implememting test methods

    @Test
    public void testGrade_Yeu_Normal() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(2.5));
    }

    @Test
    public void testGrade_Yeu_BoundaryUpper() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(4.9));
    }

    @Test
    public void testGrade_TrungBinh_BoundaryLower() {
        // Test này sẽ FAIL ở code gốc, nhưng PASS ở code đã sửa
        assertEquals("Trung bình", GradeClassifier.classifyGrade(5.0));
    }

    @Test
    public void testGrade_TrungBinh_Normal() {
        assertEquals("Trung bình", GradeClassifier.classifyGrade(6.0));
    }

    @Test
    public void testGrade_Kha_BoundaryLower() {
        // Test này sẽ FAIL ở code gốc, nhưng PASS ở code đã sửa
        assertEquals("Khá", GradeClassifier.classifyGrade(6.5));
    }

    @Test
    public void testGrade_Kha_BoundaryUpper() {
        assertEquals("Khá", GradeClassifier.classifyGrade(7.9));
    }

    @Test
    public void testGrade_Gioi_BoundaryLower() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(8.0));
    }

    @Test
    public void testGrade_Gioi_BoundaryUpper() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(10.0));
    }

    // --- Yêu cầu 5: Thêm test kiểm tra ngoại lệ ---

    @Test
    public void testException_GpaBelowZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            GradeClassifier.classifyGrade(-0.1);
        });
        // Đảm bảo kiểm tra cả nội dung thông báo lỗi
        assertEquals("GPA không hợp lệ: -0.1", exception.getMessage());
    }

    @Test
    public void testException_GpaAboveTen() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            GradeClassifier.classifyGrade(10.1);
        });
        assertEquals("GPA không hợp lệ: 10.1", exception.getMessage());
    }
}
