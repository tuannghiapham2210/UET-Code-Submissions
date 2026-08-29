#### 1. Thiết kế Test Case (Dựa hoàn toàn vào JavaDoc)
Dựa vào đặc tả JavaDoc, tham số `gpa` có các ranh giới là: 0.0, 5.0, 6.5, 8.0, và 10.0. 

**Phân tích Lớp tương đương (EP):**
* Lớp ngoại lệ: `gpa < 0.0` hoặc `gpa > 10.0` -> `IllegalArgumentException`
* Lớp "Yếu": `0.0 <= gpa < 5.0`
* Lớp "Trung bình": `5.0 <= gpa < 6.5`
* Lớp "Khá": `6.5 <= gpa < 8.0`
* Lớp "Giỏi": `8.0 <= gpa <= 10.0`

**Phân tích Giá trị biên (BVA) tại các mốc thay đổi logic:**
* Ranh giới 5.0: `4.9` (Yếu), `5.0` (Trung bình), `5.1` (Trung bình)
* Ranh giới 6.5: `6.4` (Trung bình), `6.5` (Khá), `6.6` (Khá)
* Ranh giới 8.0: `7.9` (Khá), `8.0` (Giỏi), `8.1` (Giỏi)

#### 2. Phân tích lỗi (The Buggy Trap)
Sau khi chạy bộ test dựa trên thiết kế trên với source code gốc, các test case tại mốc `5.0` và `6.5` đã bị **FAIL**.

* **Test FAIL 1:** Input: `5.0`. 
  * Kết quả thực tế (Actual): `"Yếu"`
  * Kết quả mong đợi (Expected): `"Trung bình"` (Vì JavaDoc ghi `[5.0, 6.5)` tức là bao gồm 5.0).
* **Test FAIL 2:** Input: `6.5`. 
  * Kết quả thực tế (Actual): `"Trung bình"`
  * Kết quả mong đợi (Expected): `"Khá"` (Vì JavaDoc ghi `[6.5, 8.0)` tức là bao gồm 6.5).

**Mô tả lỗi logic:** Lập trình viên đã sử dụng sai toán tử so sánh tại các mốc phân loại dưới. Thay vì dùng toán tử nhỏ hơn hẳn (`<`) để loại trừ mốc cận trên, lập trình viên lại dùng nhỏ hơn hoặc bằng (`<=`). Điều này khiến những sinh viên đạt điểm tròn mốc (như 5.0 hay 6.5) bị đẩy xuống phân loại học lực thấp hơn.