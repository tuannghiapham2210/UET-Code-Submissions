public class Main {
    public static void main(String[] args){
        //instantiating a person object
        Person p = new Person("Person1");

        p.setMe(p);

        //printing out the result of getName() method through attribute "me" of object p
        System.out.println(p.getMe().getName());

        //setting p back to null
        p = null;

        System.out.println("Phần giải thích được viết ở trong chú thích code");

        //trả lời câu hỏi trên đề bài:
        //+) Sau khi setMe(p) thì chỉ có một đối tượng person trong bộ nhớ bởi vì bản thân cái thuộc tính me lưu địa chỉ của chính object chứa thuộc tính này 
        //+) Sau khi set biến p = null, đối tượng này sẽ bị GC xóa đi vì không có biến nào ở stack chứa địa chỉ của đối tượng này nữa 
        //mà GC sẽ thử các địa chỉ được lưu trong các biến ở stack, nếu nó mà truy cập được object ở heap thì nó sẽ đánh dấu là "active" 
        //còn nếu không truy cập được từ các biến chứa địa chỉ ở stack -> inactive -> bị xóa (nếu gần hết ram thì JVM sẽ gọi GC để dọn thì đối tượng sẽ bị xóa, còn nếu không thì chưa bị xóa ngay)
        //+) Không thể truy cập lại đối tượng Person do ta không còn tham chiếu nào đến đối tượng này (biến giữ địa chỉ của đối tượng ở heap không còn tồn tại ở stack)

    }
}
