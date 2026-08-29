import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Main{
    public static void main(String[] args){
        //creating several books
        Book book1 = new Book("B04", "Clean Code", "Robert C. Martin", 2008);
        Book book2 = new Book("B01", "The Pragmatic Programmer", "David Thomas", 1999);
        Book book3 = new Book("B05", "Effective Java", "Joshua Bloch", 2001);
        Book book4 = new Book("B02", "Design Patterns", "Erich Gamma", 1994);
        Book book5 = new Book("B03", "Introduction to Algorithms", "Thomas H. Cormen", 1989);

        // //adding the books into the first library (implemented using ArrayList)
        // LibraryArrayList library1 = new LibraryArrayList();
        // library1.addBook(book1);
        // library1.addBook(book2);
        // library1.addBook(book3);
        // library1.addBook(book4);
        // library1.addBook(book5);

        // //testing the methods
        // System.out.println(library1.findBook("B02"));

        // library1.removeBook("B01");

        // library1.displayBook();

        // //adding books to the second library
        // LibraryHashMap library2 = new LibraryHashMap();
        // library2.addBook(book1);
        // library2.addBook(book2);
        // library2.addBook(book3);
        // library2.addBook(book4);
        // library2.addBook(book5);

        // //testing the methods
        // System.out.println(library2.findBook("B02"));

        // library2.removeBook("B01");

        // library2.displayBook();

        //adding books to the second library
        LibraryTreeMap library3 = new LibraryTreeMap();
        library3.addBook(book1);
        library3.addBook(book2);
        library3.addBook(book3);
        library3.addBook(book4);
        library3.addBook(book5);

        //testing the methods
        System.out.println(library3.findBook("B02"));

        library3.removeBook("B01");

        library3.displayBook();
    }
}

class Book {
    //attributes of a book
    private String id;
    private String title;
    private String author;
    private int year;

    //constructor for a book
    public Book(String id, String title, String author, int year){
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    //public getters
    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public int getYear(){
        return this.year;
    }

    @Override 
    public String toString(){
        return "ID : " + this.getId() + " | " +  "Title: " + this.getTitle() + " | " + "Author: " + this.getAuthor() + " | " + "Year: " + this.getYear();
    }
}

class LibraryArrayList {
    //the internal state of the class
    private ArrayList<Book> archive = new ArrayList<>();

    //method to add the book
    public void addBook(Book book){
        archive.add(book);
    }

    //method to find a particular book using id (returns null if not found) O(N)
    public Book findBook(String id){
        for (Book book : archive){;
            if (book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }

    //method to delete a book using id O(N)
    public void removeBook(String id){
        for (Book book : archive){
            if (book.getId().equals(id)){
                archive.remove(book);
                return;
            }
        }
        return;
    }

    public void displayBook(){
        System.out.println("Collection of books: ");
        for (Book book : archive){
            System.out.println(book);
        }
    }
}

class LibraryHashMap {
    private HashMap<String, Book> archive = new HashMap<>();

    public void addBook(Book book){
        archive.put(book.getId(), book);
    }

    //method to find a specific book using id (returns null if not found) O(1)
    public Book findBook(String id){
        return archive.getOrDefault(id, null);
    }

    public void removeBook(String id){
        archive.remove(id);
    }

    public void displayBook(){
        System.out.println("Collection of books: ");
        for (Book book : archive.values()){
            System.out.println(book);
        }
    }
}

class LibraryTreeMap {
    private TreeMap<String, Book> archive = new TreeMap<>();

    public void addBook(Book book){
        archive.put(book.getId(), book);
    }

    //method to find a paricular boook using id (returns null if not found) O(LogN)
    public Book findBook(String id){
        return archive.getOrDefault(id, null);
    }

    public void removeBook(String id){
        archive.remove(id);
    }

    public void displayBook(){
        System.out.println("Collection of books");
        for (Book book : archive.values()){
            System.out.println(book);
        }
    }
}