public class Date {
    protected int day, month, year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }


    //copy constructor
    public Date(Date source){
        this.day = source.day;
        this.month = source.month;
        this.year = source.year;
    }

    public Date getDate(){
        return new Date(this);
    }

}
