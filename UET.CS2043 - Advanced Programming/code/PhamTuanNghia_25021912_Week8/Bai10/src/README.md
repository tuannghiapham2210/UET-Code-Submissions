#### 1. Kiểm thử hàm `deposit(double amount)`
[cite_start]Hàm này chỉ phụ thuộc vào giá trị đầu vào `amount` [cite: 1094-1100]. Ranh giới logic duy nhất là 0.

**Phân tích Lớp tương đương (EP):**
* Lớp ngoại lệ: `amount <= 0` -> Ném `IllegalArgumentException`.
* Lớp hợp lệ: `amount > 0` -> Cộng tiền vào `balance`.

**Phân tích Giá trị biên (BVA) tại ranh giới 0:**
* `min-` (-0.01): Ném ngoại lệ.
* `min` (0.00): Ném ngoại lệ.
* `min+` (0.01): Nạp tiền thành công.

#### 2. Kiểm thử hàm `withdraw(double amount)`
[cite_start]Hàm này phức tạp hơn vì nó phụ thuộc vào 2 biến: `amount` (đầu vào) và `balance` (trạng thái hiện tại của object) [cite: 1101-1111]. Ranh giới logic nằm ở 0 và giá trị `balance` hiện tại.

**Phân tích Lớp tương đương (EP):**
Giả sử trạng thái `balance` đang là 500.
* EC1 (Ngoại lệ): `amount <= 0` -> Ném `IllegalArgumentException`.
* EC2 (Hợp lệ, Thành công): `0 < amount <= balance` (VD: 200) -> Trả về `true`, trừ tiền.
* EC3 (Hợp lệ, Thất bại do thiếu tiền): `amount > balance` (VD: 600) -> Trả về `false`, không trừ tiền.

**Phân tích Giá trị biên (BVA):**
* Tại ranh giới 0:
  * `-0.01` (Ngoại lệ), `0.00` (Ngoại lệ), `0.01` (Rút thành công).
* Tại ranh giới `balance` (Giả sử balance = 500):
  * `max-` (499.99): Rút thành công, `balance` còn 0.01.
  * `max` (500.00): Rút thành công, `balance` còn 0.00.
  * `max+` (500.01): Rút thất bại, trả về `false`, `balance` giữ nguyên 500.00.