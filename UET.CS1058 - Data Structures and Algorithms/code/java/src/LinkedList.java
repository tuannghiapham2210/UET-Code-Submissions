//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

import java.util.Objects;

public class LinkedList {

    // Lớp Node đại diện cho một phần tử trong danh sách liên kết
    public static class Node {
        public String data;
        public Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }


        public String getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        @Override
        public String toString() {
            return data;
        }
    }

    private Node first; // chỉ có first, không có last

    // Constructor khởi tạo danh sách rỗng
    public LinkedList() {
        this.first = null;
    }

    // Thêm vào đầu danh sách
    public void addFirst(String data) {
        Node newNode = new Node(data);
        newNode.next = first;
        first = newNode;
    }

    // Gắn vào cuối danh sách (duyệt từ first vì danh sách không có con trỏ last)
    public void append(String data) {
        Node newNode = new Node(data);
        if (first == null) {
            first = newNode;
            return;
        }
        Node current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Trả về nút đầu tiên chứa data (trả về null nếu không tìm thấy)
    public Node findFirst(String data) {
        Node current = first;
        while (current != null) {
            if (Objects.equals(current.data, data)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Xóa nút đầu tiên chứa dữ liệu data
    public void deleteFirst(String data) {
        if (first == null) {
            return;
        }

        // Trường hợp nút cần xóa là nút đầu tiên (first)
        if (Objects.equals(first.data, data)) {
            first = first.next;
            return;
        }

        // Tìm nút đứng ngay trước nút cần xóa
        Node current = first;
        while (current.next != null && !Objects.equals(current.next.data, data)) {
            current = current.next;
        }

        // Nếu tìm thấy nút cần xóa (current.next != null), tiến hành ngắt liên kết
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Xóa tất cả các nút chứa dữ liệu data
    public void deleteAll(String data) {
        // Xóa các nút trùng ở đầu danh sách
        while (first != null && Objects.equals(first.data, data)) {
            first = first.next;
        }

        // Xóa các nút trùng ở phần còn lại của danh sách
        Node current = first;
        while (current != null && current.next != null) {
            if (Objects.equals(current.next.data, data)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    // Phương thức in danh sách để kiểm tra kết quả
    public void printList() {
        if (first == null) {
            System.out.println("[] (Danh sách rỗng)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node current = first;
        while (current != null) {
            sb.append("[").append(current.data).append("]");
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    // Lấy nút đầu tiên
    public Node getFirst() {
        return first;
    }

    // Kiểm tra danh sách rỗng
    public boolean isEmpty() {
        return first == null;
    }

    // Hàm main chạy thử nghiệm các phương thức
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        System.out.println("--- 1. Kiểm tra addFirst và append ---");
        list.append("B");
        list.append("C");
        list.addFirst("A");
        list.append("A");
        list.append("D");
        list.append("A");
        // Danh sách dự kiến: [A] -> [B] -> [C] -> [A] -> [D] -> [A]
        System.out.print("Danh sách ban đầu: ");
        list.printList();

        System.out.println("\n--- 2. Kiểm tra findFirst ---");
        Node found = list.findFirst("C");
        System.out.println("Tìm 'C': " + (found != null ? "Tìm thấy nút có data = " + found.data : "Không tìm thấy"));
        Node notFound = list.findFirst("Z");
        System.out.println("Tìm 'Z': " + (notFound != null ? "Tìm thấy nút" : "Không tìm thấy"));

        System.out.println("\n--- 3. Kiểm tra deleteFirst(\"A\") ---");
        list.deleteFirst("A");
        // Xóa 'A' đầu tiên: [B] -> [C] -> [A] -> [D] -> [A]
        System.out.print("Sau khi xóa nút 'A' đầu tiên: ");
        list.printList();

        System.out.println("\n--- 4. Kiểm tra deleteAll(\"A\") ---");
        list.deleteAll("A");
        // Xóa tất cả các nút 'A' còn lại: [B] -> [C] -> [D]
        System.out.print("Sau khi xóa tất cả nút 'A': ");
        list.printList();

        System.out.println("\n--- 5. Kiểm tra deleteAll(\"B\") (nút ở đầu danh sách) ---");
        list.deleteAll("B");
        // Kết quả: [C] -> [D]
        System.out.print("Sau khi xóa 'B': ");
        list.printList();

        System.out.println("\n--- 6. Kiểm tra xóa nút không tồn tại ---");
        list.deleteFirst("XYZ");
        list.printList();
    }
}
