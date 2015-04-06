package Model;

import java.util.Date;

/**
 * Created by USER on 06.04.15.
 */
public class MyDate extends Date {
    public MyDate(){
        setYear(00);
        setMonth(00);
        setDate(0000);
    }
    public MyDate(int year,int month,int day){
        setYear(year);
        setMonth(month);
        setDate(day);
    }
    public String toString(){
        return getDay()+ "." +getMonth()+ "."+ getYear();
    }
}
