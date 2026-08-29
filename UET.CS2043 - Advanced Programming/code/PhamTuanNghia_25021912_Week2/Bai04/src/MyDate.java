public class MyDate{
    //attributes (instance variables)
    private int day, month, year;

    //constructor for a MyDate object
    public MyDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //copy constructor for a MyDate object
    public MyDate(MyDate source){
        this.day = source.day;
        this.month = source.month;
        this.year = source.year;
    }

    //method to display date
    public String displayDate(){
        return this.day + "/" + this.month + "/" + this.year;
    }
    
    //implementing setters
    public void setDay(int day){
        this.day = day;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public void setYear(int year){
        this.year = year;
    }
}

