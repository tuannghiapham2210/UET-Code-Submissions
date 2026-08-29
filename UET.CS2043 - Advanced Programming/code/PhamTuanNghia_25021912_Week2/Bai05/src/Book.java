public class Book{
    //attributes
    private String title, author;
    private double price;

    //constructor (3 parameters)
    public Book(String title, String author, double price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj){
        //if both points to the same object on the heap -> true
        if (this == obj) {return true;}

        //if the object is null or belongs to a different class
        if (obj == null || obj.getClass() != this.getClass()) {return false;}

        //typecasting
        Book other = (Book) obj;

        //checking the corresponding values
        return Double.compare(other.price, this.price) == 0 && other.author.equals(this.author) && other.title.equals(this.title);
    }
}