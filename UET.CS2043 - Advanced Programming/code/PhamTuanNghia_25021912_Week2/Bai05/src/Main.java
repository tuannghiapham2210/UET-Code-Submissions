public class Main {
    public static void main(String[] args){
        //instantiating 2 books object with the same contents
        Book book1 = new Book("title", "author", 5.0);
        Book book2 = new Book("title", "author", 5.0);

        //comparing the two objects using "==" operator
        System.out.println(book1 == book2);

        //comparing the two objects using "equals" method that i just wrote
        System.out.println(book1.equals(book2));

        //trả lời câu hỏi "==" và "equals()" khác nhau như nào: "==" thì sẽ so sánh địa chỉ bộ nhớ mà bản thân cái biến đang giữ. Nếu cùng địa chỉ bộ nhớ (cùng trỏ đến một object trong heap thì trả về true, còn ko thì false)
        //còn phương thức "equals()" bản đã được override thì sẽ so sánh cái giá trị thực sự mà bản thân cái đối tượng đang nắm giữ, chứ không đơn thuần là địa chỉ bộ nhớ.
        //còn phương thức equals() gốc của class Object thì mặc định y hệt như "=="

    }
}
