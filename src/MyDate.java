public class MyDate implements Printable {
    int year;
    int month;
    int day;

    public MyDate(int day, int month, int year) {
        this.year = year;
        this.day = day;
        this.month = month;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public void print() {
        System.out.println(+this.day+"/"+this.month+"/"+this.year) ;
    }
}
