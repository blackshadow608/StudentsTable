package model;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }


    public void setName( String lastName, String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public String getName(){
        return  (this.lastName + " " + this.firstName + " " + this.secondName);
    }
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getSecondName(){return this.secondName;}
}