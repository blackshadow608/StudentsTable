package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 05.04.15.
 */
public class StudentList {
    private List<Student> students;

    public StudentList(){
        students = new ArrayList<Student>();
    }

    public void addStudent (String firstName,String secondName, String lastName, Date birthday, Date enterDate, Date finishDate){
        Student newStudent = new Student();
        newStudent.setName(lastName, firstName, secondName);
        newStudent.setBirthday(birthday);
        newStudent.setEnterDate(enterDate);
        newStudent.setFinishDate(finishDate);

        students.add(newStudent);
    }
    public void addStudent(Student student){
        students.add(student);
    }

    public void deleteStudent (int index){
            {
                students.remove(index);
            }
    }
    public List<String> getNames(){
        ArrayList<String> namesList = new ArrayList();
        for (Student student : students) {
            namesList.add(student.getName());
        }

        return namesList;
    }

    public void deleteAll(){
        students.removeAll(students);
    }

    public Student getStudent(int index){
        return students.get(index);
    }

    public int size(){
        return students.size();
    }
}