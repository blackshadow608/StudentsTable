package mainPackage; /**
 * Created by USER on 06.04.15.
 */
import model.Student;
import model.StudentList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private DefaultTableModel model = new DefaultTableModel();
    private StudentList studentList;
    private String dateFormat;
    private String separator;
    private ArrayList<Integer> numbersOfSearchElements;
    private DefaultTableModel modelOfSearchTable;

    public Controller(StudentList studentList){
        this.separator = ":";
        this.dateFormat = "dd" + separator + "MM" + separator + "y";

        this.studentList = studentList;
        model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");

    }
    public DefaultTableModel getModel(){
        return model;
    }

    public void addNewStudent (String firstName,String secondName, String lastName, Date birthday, Date enterDate, Date finishDate){
        studentList.addStudent(firstName,secondName,lastName,birthday,enterDate,finishDate);
    }

    public void delStudents(){
        if(numbersOfSearchElements.size() > 0){
        for (int number = numbersOfSearchElements.size() - 1; number >= 0 ; number--){
            studentList.deleteStudent(numbersOfSearchElements.get(number));
        }
        }
        numbersOfSearchElements = null;
    }

    public void Save() {

        try {
            //File file = new File("D:/таблица.xml");
            JFileChooser fileChooser = new JFileChooser();

            int returnVal =  fileChooser.showDialog(null, "Сохранить");
            FileNameExtensionFilter filter;
            filter = new FileNameExtensionFilter("xml","XML");
            fileChooser.addChoosableFileFilter(filter);
            File file;
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            if(filter.accept(file)){
                XStream xstream = new XStream(new StaxDriver());
                xstream.alias("student", Student.class);
                xstream.alias("studentList", StudentList.class);
                xstream.toXML(studentList, new FileOutputStream(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Open(){
        //("D:/таблица.xml");
        JFileChooser fileChooser = new JFileChooser();

        int returnVal =  fileChooser.showDialog(null, "Открыть");
        FileNameExtensionFilter filter;
        filter = new FileNameExtensionFilter("xml","XML");
        fileChooser.addChoosableFileFilter(filter);
        File file;
        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
        if(filter.accept(file)){
            XStream xstream = new XStream(new StaxDriver());
            xstream.alias("student", Student.class);
            xstream.alias("studentList", StudentList.class);

            xstream.fromXML(file, studentList);

            // this.updateModel();
        }
        // this.updateModel();
    }

    public List<String[]> getRows( int TypeOfRows){
        List<String[]> rowData = new ArrayList<String[]>() ;
        Student student;

        model = null;
        model = new DefaultTableModel();
        model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");
//        for (int rowCount = 0; rowCount < model.getRowCount(); rowCount++){
//            model.removeRow(rowCount);
//        }
        SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
        if(TypeOfRows == 0){
            for (int row = 0 ; row < studentList.size(); row++){
                student = studentList.getStudent(row);
            String [] rData = {student.getName(), format1.format(student.getBirthday()),
                    format1.format(student.getEnterDate()), format1.format(student.getFinishDate())};
            // model.addRow(rowData);
            rowData.add(rData);
        }
        }
        if(TypeOfRows == 1){
            if(numbersOfSearchElements != null){
                for (Integer numbersOfSearchElement : numbersOfSearchElements) {
                    student = studentList.getStudent(numbersOfSearchElement);
                    String[] rData = {student.getName(),
                            format1.format(student.getBirthday()),
                            format1.format(student.getEnterDate()),
                            format1.format(student.getFinishDate())};
                    rowData.add(rData);
                }
            }else{return null;}
        }

        return rowData;
    }


    public void findWithNameAndBDay(int numberSearch, String name, int date){
        List<String> names = studentList.getNames();
        numbersOfSearchElements = new ArrayList<Integer>();
        int birthdaySearch = 1;
        int enterDateSearch = 2;
        int finishDateSearch = 3;
        Student student;
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getBirthday().getDate()
                        && name.equals(student.getName())){
                    numbersOfSearchElements.add(currentStudent);
                }
            }

        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getEnterDate().getDate() && name.equals(student.getName())){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getFinishDate().getDate() && name.equals(student.getName())){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }

    }

    public void findStudentWithDate(int numberSearch, int date, int month){
        numbersOfSearchElements = new ArrayList<Integer>();
        int birthdaySearch = 1;
        int enterDateSearch = 2;
        int finishDateSearch = 3;
        Student student;
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
               if(date == student.getBirthday().getDate())
                    if( month == student.getBirthday().getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getEnterDate().getDate() && month == student.getEnterDate().getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getFinishDate().getDate() && month == student.getFinishDate().getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
    }
    public void findStudentWithDate(int numberSearch, int date, int yearFrom, int yearTo){
        numbersOfSearchElements = new ArrayList<Integer>();
        int birthdaySearch = 1;
        int enterDateSearch = 2;
        int finishDateSearch = 3;
        Student student;
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getBirthday().getDate() && yearFrom <= student.getBirthday().getYear()
                        && student.getBirthday().getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getEnterDate().getDate() && yearFrom <=  student.getEnterDate().getYear()
                        &&  student.getEnterDate().getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < studentList.size(); currentStudent++){
                student = studentList.getStudent(currentStudent);
                if(date == student.getFinishDate().getDate() && yearFrom <= student.getFinishDate().getYear()
                        && student.getFinishDate().getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
    }



}
