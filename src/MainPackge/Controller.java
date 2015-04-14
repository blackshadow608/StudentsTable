package MainPackge; /**
 * Created by USER on 06.04.15.
 */
import Model.Student;
import Model.StudentList;
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
        List<String> names = studentList.getNames();
        List<Date> birthdays = studentList.getBirthdays();
        List<Date> enterDates = studentList.getEnterDate();
        List<Date> finishDates = studentList.getFinishDate();
        List<String[]> rowData = new ArrayList<String[]>() ;

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
            for (int row = 0 ; row < names.size(); row++){
            String [] rData = {names.get(row), format1.format(birthdays.get(row)),
                    format1.format(enterDates.get(row)), format1.format(finishDates.get(row))};
            // model.addRow(rowData);
            rowData.add(rData);
        }
        }
        if(TypeOfRows == 1){
            if(numbersOfSearchElements != null){
                for (Integer numbersOfSearchElement : numbersOfSearchElements) {
                    String[] rData = {names.get(numbersOfSearchElement),
                            format1.format(birthdays.get(numbersOfSearchElement)),
                            format1.format(enterDates.get(numbersOfSearchElement)),
                            format1.format(finishDates.get(numbersOfSearchElement))};
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
        List<Date> birthdays = studentList.getBirthdays();
        List<Date> enterDates = studentList.getEnterDate();
        List<Date> finishDates = studentList.getFinishDate();
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < birthdays.size(); currentStudent++){
                if(date == birthdays.get(currentStudent).getDate() && name.contains(names.get(currentStudent))){
                    numbersOfSearchElements.add(currentStudent);
                }
            }

        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < enterDates.size(); currentStudent++){
                if(date == enterDates.get(currentStudent).getDate() && name.equals(names.get(currentStudent))){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < finishDates.size(); currentStudent++){
                if(date == finishDates.get(currentStudent).getDate() && name.equals(names.get(currentStudent))){
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
        List<Date> birthdays = studentList.getBirthdays();
        List<Date> enterDates = studentList.getEnterDate();
        List<Date> finishDates = studentList.getFinishDate();
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < birthdays.size(); currentStudent++){
               if(date == birthdays.get(currentStudent).getDate())
                    if( month == birthdays.get(currentStudent).getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < enterDates.size(); currentStudent++){
                if(date == enterDates.get(currentStudent).getDate() && month == enterDates.get(currentStudent).getMonth()){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < finishDates.size(); currentStudent++){
                if(date == finishDates.get(currentStudent).getDate() && month == finishDates.get(currentStudent).getMonth()){
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
        List<Date> birthdays = studentList.getBirthdays();
        List<Date> enterDates = studentList.getEnterDate();
        List<Date> finishDates = studentList.getFinishDate();
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < birthdays.size(); currentStudent++){
                if(date == birthdays.get(currentStudent).getDate() && yearFrom <= birthdays.get(currentStudent).getYear()
                        && birthdays.get(currentStudent).getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < enterDates.size(); currentStudent++){
                if(date == enterDates.get(currentStudent).getDate() && yearFrom <=  enterDates.get(currentStudent).getYear()
                        &&  enterDates.get(currentStudent).getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < finishDates.size(); currentStudent++){
                if(date == finishDates.get(currentStudent).getDate() && yearFrom <= finishDates.get(currentStudent).getYear()
                        && finishDates.get(currentStudent).getYear() <= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
    }

    public DefaultTableModel  getRowsOfSearchTable(){
        modelOfSearchTable = new DefaultTableModel();
        modelOfSearchTable.addColumn("ФИО");
        modelOfSearchTable.addColumn("Дата рождения");
        modelOfSearchTable.addColumn("Дата поступления");
        modelOfSearchTable.addColumn("Дата окончания");
        List<String> names = studentList.getNames();
        List<Date> birthdays = studentList.getBirthdays();
        List<Date> enterDates = studentList.getEnterDate();
        List<Date> finishDates = studentList.getFinishDate();
        SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);

        return modelOfSearchTable;
    }




}
