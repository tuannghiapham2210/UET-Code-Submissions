//Problem D: Large class with too many responsibilities 

// class Report {
//     private String title;
//     private String content;
//     private String authorEmail;
//     private String authorName;
//     private String authorPhone;
//     private String authorAddress;
// }


//refactored: used extract class technique
public class Report {
    private String title;
    private String content;
    private Author author;

    //constructor
    public Report(String title, String content, Author author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

}

class Author {
    private String name;
    private String phone;
    private String address;
    private String email;

    //constructor
    public Author(String name, String phone, String address, String email){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;

    }
}