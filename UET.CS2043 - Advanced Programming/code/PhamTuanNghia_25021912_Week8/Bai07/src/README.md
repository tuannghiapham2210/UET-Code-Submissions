### 1. Phân tích Lớp tương đương (Equivalence Partitioning - EP) cho tham số `price`
Dựa vào đặc tả hệ thống, tham số `price` quyết định tỷ lệ chiết khấu với 2 mốc thay đổi logic chính là 0 và 100. Do đó, ta chia `price` thành 3 lớp tương đương (Equivalence Classes - EC):

* **EC1 (Lớp không hợp lệ):** `price < 0` -> Ném `IllegalArgumentException`.
* **EC2 (Lớp hợp lệ 1):** `0 <= price < 100` -> Áp dụng mức chiết khấu thấp.
* **EC3 (Lớp hợp lệ 2):** `price >= 100` -> Áp dụng mức chiết khấu cao.

*(Ghi chú: Đối với tham số `memberType`, ta cũng có 4 lớp tương đương định danh: `GUEST`, `MEMBER`, `VIP`, và Lớp không hợp lệ ví dụ như `UNKNOWN`).*

---

### 2. Bảng Test Case theo EP
Mỗi test case dưới đây đại diện cho việc lấy một giá trị từ mỗi lớp tương đương của `price` kết hợp với một loại `memberType` để đảm bảo độ phủ.

| Mã TC | Mô tả | `price` | `memberType` | Kết quả mong đợi |
| :--- | :--- | :--- | :--- | :--- |
| TC_EP_01 | Kiểm tra giá âm (ngoại lệ) | -10.0 | "MEMBER" | Ném `IllegalArgumentException` |
| TC_EP_02 | Kiểm tra giá hợp lệ dưới 100 | 50.0 | "VIP" | Chiết khấu 15% (Trả về 7.5) |
| TC_EP_03 | Kiểm tra giá hợp lệ từ 100 trở lên | 200.0 | "MEMBER" | Chiết khấu 10% (Trả về 20.0) |
| TC_EP_04 | Kiểm tra loại thành viên không hợp lệ | 50.0 | "UNKNOWN" | Ném `IllegalArgumentException` |

---

### 3. Phân tích Giá trị biên (Boundary Value Analysis - BVA) cho tham số `price`
Áp dụng BVA tại 2 ranh giới theo yêu cầu là `price = 0` và `price = 100`. (Giả sử hệ thống xử lý số thực với độ chính xác 2 chữ số thập phân).

**Tại ranh giới 0:**
* **min-** (Ngay dưới biên): `-0.01` -> Ném `IllegalArgumentException`.
* **min** (Tại biên): `0.00` -> Không lỗi, chiết khấu 0 (vì giá trị = 0).
* **min+** (Ngay trên biên): `0.01` -> Xử lý chiết khấu bình thường theo EC2.

**Tại ranh giới 100:**
* **max-** (Ngay dưới biên): `99.99` -> Mức chiết khấu EC2 (VD: VIP = 15%).
* **max** (Tại biên): `100.00` -> Mức chiết khấu EC3 (VD: VIP = 20%).
* **max+** (Ngay trên biên): `100.01` -> Mức chiết khấu EC3 (VD: VIP = 20%).

---

### 4. Kiểm thử tổ hợp 2 chiều (2-Way Combinatorial Testing)
Để đảm bảo mọi cặp giá trị của `(price, memberType)` đều xuất hiện ít nhất một lần, ta thực hiện tích Đề-các (Cartesian product) giữa 3 lớp tương đương của `price` và 4 lớp tương đương của `memberType`. 

Tổng cộng cần thiết kế **12 Test Cases** tối thiểu:

| Mã TC | `price` (Đại diện) | `memberType` | Kết quả mong đợi |
| :--- | :--- | :--- | :--- |
| TC_CM_01 | -10.0 (Âm) | "GUEST" | Ném `IllegalArgumentException` |
| TC_CM_02 | -10.0 (Âm) | "MEMBER" | Ném `IllegalArgumentException` |
| TC_CM_03 | -10.0 (Âm) | "VIP" | Ném `IllegalArgumentException` |
| TC_CM_04 | -10.0 (Âm) | "UNKNOWN" | Ném `IllegalArgumentException` |
| TC_CM_05 | 50.0 (< 100) | "GUEST" | 0% |
| TC_CM_06 | 50.0 (< 100) | "MEMBER" | 5% |
| TC_CM_07 | 50.0 (< 100) | "VIP" | 15% |
| TC_CM_08 | 50.0 (< 100) | "UNKNOWN" | Ném `IllegalArgumentException` |
| TC_CM_09 | 150.0 (>= 100) | "GUEST" | 0% |
| TC_CM_10 | 150.0 (>= 100) | "MEMBER" | 10% |
| TC_CM_11 | 150.0 (>= 100) | "VIP" | 20% |
| TC_CM_12 | 150.0 (>= 100) | "UNKNOWN" | Ném `IllegalArgumentException` |