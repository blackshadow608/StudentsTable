package Model;

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

    public void deleteStudent (String name, Date birthday, Date enterDate, Date finishDate){
        for(int currentStudent = 0; currentStudent < students.size(); currentStudent++){
            if(name.equals(students.get(currentStudent).getName()) &&
                    birthday == students.get(currentStudent).getBirthday()&&
                    enterDate == students.get(currentStudent).getEnterDate() &&
                    finishDate == students.get(currentStudent).getFinishDate()){
                students.remove(currentStudent);
            }
        }
    }

    public Object[] getNames(){
        ArrayList<String> namesList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            namesList.add(students.get(numberOfStudent).getName());
        }

        return namesList.toArray();
    }

    public ArrayList getBirthdays(){
        ArrayList<Date> birthdaysList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            birthdaysList.add(students.get(numberOfStudent).getBirthday());
        }

        birthdaysList.toArray();
        return birthdaysList;
    }

    public ArrayList getEnterDate(){
        ArrayList<Date> enterDateList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            enterDateList.add(students.get(numberOfStudent).getEnterDate());
        }

        return enterDateList;
    }

    public ArrayList getFinishDate(){
        ArrayList<Date> finishDateList = new ArrayList();
        for(int numberOfStudent = 0; numberOfStudent < students.size(); numberOfStudent++ ){
            finishDateList.add(students.get(numberOfStudent).getFinishDate());
        }

        return finishDateList;
    }
}
