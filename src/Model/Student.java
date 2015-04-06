package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by USER on 05.04.15.
 */
public class Student {
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthday = new Date();
    private Date enterDate = new Date();
    private Date finishDate = new Date();

    public Student(){

    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday.toString();
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public String getEnterDate() {
        return enterDate.toString();
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getFinishDate() {
        return finishDate.toString();
    }


    public void setName( String lastName, String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public String getName(){
        return  (this.lastName + " " + this.firstName + " " + this.secondName);
    }


}
