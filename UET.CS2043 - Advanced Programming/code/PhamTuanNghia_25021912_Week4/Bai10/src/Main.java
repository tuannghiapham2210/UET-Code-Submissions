import java.util.ArrayList;
import java.util.List;

abstract class MediaItem{
    //attributes (instance variables)
    protected String id;
    protected String name;

    //constructor
    public MediaItem(String id, String name){
        this.id = id;
        this.name = name;
    }
}

class Book extends MediaItem{
    //attributes
    String author;
    int pages;

    //constructor
    public Book(String id, String name, String author, int pages){
        super(id, name);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public String toString(){
        return this.name + " - " + this.author + " - " + this.pages;
    }
}

class DVD extends MediaItem{
    //attributes
    String director;
    int duration;

    //constructor
    public DVD(String id, String name, String director, int duration){
        super(id, name);
        this.director = director;
        this.duration = duration;
    }

    @Override
    public String toString(){
        return this.name + " - " + this.director + " - " + this.duration;
    }
}

//creating class library that only accepts items of type Book or DVD
class LibrarySection<T extends MediaItem>{
    private List<T> library = new ArrayList<>();

    public void addItem(T item){
        library.add(item);
    }

    public void removeItem(T item){
        library.remove(item);
    }

    public void displayItems(){
        for (T item : library){
            System.out.println(item);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LibrarySection<Book> bookSection = new LibrarySection<>();
        bookSection.addItem(new Book("B01", "Java Programming", "John Doe", 500));
        bookSection.addItem(new Book("B02", "Data Structures", "Jane Smith", 450));

        LibrarySection<DVD> dvdSection = new LibrarySection<>();
        dvdSection.addItem(new DVD("D01", "Inception", "Christopher Nolan", 148));
        dvdSection.addItem(new DVD("D02", "The Matrix", "Wachowskis", 136));

        bookSection.displayItems();
        dvdSection.displayItems();
    }
}
