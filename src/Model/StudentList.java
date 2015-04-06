package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by USER on 05.04.15.
 */
public class StudentList {
    private ArrayList<Student> students;

    public StudentList(){
        students = new ArrayList<Student>();
    }

    public void addStudent (String name, Date birthday, Date enterDate, Date finishDate){
        Student newStudent = new Student();
        String [] names = name.split("\\s+");
        newStudent.setName(names[0], names[1], names [2]);
        newStudent.setBirthday(birthday);
        newStudent.setEnterDate(enterDate);
        newStudent.setFinishDate(finishDate);

        students.add(newStudent);
    }

    public Object[] getNames(){
        ArrayList<String> namesList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            namesList.add(students.get(numberOfStudent).getName());
        }

        return namesList.toArray();
    }

    public Object[] getBirthdays(){
        ArrayList<String> birthdaysList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            birthdaysList.add(students.get(numberOfStudent).getBirthday());
        }

        return birthdaysList.toArray();
    }

    public Object[] getEnterDate(){
        ArrayList<String> enterDateList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            enterDateList.add(students.get(numberOfStudent).getEnterDate());
        }

        return enterDateList.toArray();
    }

    public Object[] getFinishDate(){
        ArrayList<String> finishDateList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            finishDateList.add(students.get(numberOfStudent).getFinishDate());
        }

        return finishDateList.toArray();
    }
}
