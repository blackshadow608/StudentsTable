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
import javax.xml.stream.XMLStreamConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private DefaultTableModel model = new DefaultTableModel();
    private StudentList studentList;
    private String dateFormat;
    private String separator;
    private int page;
    private int currentPage;
    private int numOfRows;
    private ArrayList<Integer> numbersOfSearchElements;
    private DefaultTableModel modelOfSearchTable;

    public Controller(StudentList studentList){
        this.separator = ":";
        this.dateFormat = "dd" + separator + "MM" + separator + "y";
        this.page = 1;
        this.currentPage = 1;
        this.numOfRows = 38;
        this.studentList = studentList;
        model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");

    }
    public DefaultTableModel getModel(){
        return model;
    }

    public void addNewStudent(String name, Date birthday, Date enterDate, Date finishDate){
        studentList.addStudent(name,birthday,enterDate,finishDate);

//        SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
//        String [] row = {name,format1.format(birthday),format1.format(enterDate), format1.format(finishDate)};
//        model.addRow(row);
        this.updateModel();

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

             this.updateModel();
        }
         this.updateModel();
    }

    public void updateModel(){
        page = (studentList.size() / numOfRows) + 1;
        Object [] names = studentList.getNames();
        ArrayList<Date> birthdays = studentList.getBirthdays();
        ArrayList<Date> enterDates = studentList.getEnterDate();
        ArrayList<Date> finishDates = studentList.getFinishDate();

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
        int rows;
        rows = (currentPage - 1) * (numOfRows);
        int numOfLastRow;
       numOfLastRow = rows + numOfRows;
       if(names.length < numOfLastRow) {
           numOfLastRow = names.length;
       }
        for (int row = rows ; row < numOfLastRow; row++){

            String[] rowData = {names[row].toString(), format1.format(birthdays.get(row)),
                    format1.format(enterDates.get(row)), format1.format(finishDates.get(row))};
            model.addRow(rowData);
        }


    }

    private void setRows(int rows){
        this.numOfRows = rows;
    }

    public void setSetting(String[] settings){

        if(!settings[0].equals("")){
        setRows(Integer.parseInt(settings[0]));
        }
        if(!settings[1].equals("")) {
            this.separator = settings[1];
            this.dateFormat = "dd" + separator + "MM" + separator + "y";
        }
        if(!settings[2].equals("")) {
            this.dateFormat = settings[2];
        }
        updateModel();
    }

    public void findWithNameAndBDay(int numberSearch, String name, int date){
        Object [] names = studentList.getNames();
        numbersOfSearchElements = new ArrayList<Integer>();
        int birthdaySearch = 1;
        int enterDateSearch = 2;
        int finishDateSearch = 3;
        ArrayList<Date> birthdays = studentList.getBirthdays();
        ArrayList<Date> enterDates = studentList.getEnterDate();
        ArrayList<Date> finishDates = studentList.getFinishDate();
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < birthdays.size(); currentStudent++){
                if(date == birthdays.get(currentStudent).getDate() && name.equals(names[currentStudent])){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < enterDates.size(); currentStudent++){
                if(date == enterDates.get(currentStudent).getDate() && name.equals(names[currentStudent])){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < finishDates.size(); currentStudent++){
                if(date == finishDates.get(currentStudent).getDate() && name.equals(names[currentStudent])){
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
        ArrayList<Date> birthdays = studentList.getBirthdays();
        ArrayList<Date> enterDates = studentList.getEnterDate();
        ArrayList<Date> finishDates = studentList.getFinishDate();
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < birthdays.size(); currentStudent++){
                if(date == birthdays.get(currentStudent).getDate() && month == birthdays.get(currentStudent).getMonth()){
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
        ArrayList<Date> birthdays = studentList.getBirthdays();
        ArrayList<Date> enterDates = studentList.getEnterDate();
        ArrayList<Date> finishDates = studentList.getFinishDate();
        if(numberSearch == birthdaySearch){
            for(int currentStudent = 0; currentStudent < birthdays.size(); currentStudent++){
                if(date == birthdays.get(currentStudent).getDate() && yearFrom <= birthdays.get(currentStudent).getYear()
                        && birthdays.get(currentStudent).getYear() >= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == enterDateSearch){
            for(int currentStudent = 0; currentStudent < enterDates.size(); currentStudent++){
                if(date == enterDates.get(currentStudent).getDate() && yearFrom <=  enterDates.get(currentStudent).getYear()
                        &&  enterDates.get(currentStudent).getYear() >= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
        if(numberSearch == finishDateSearch){
            for(int currentStudent = 0; currentStudent < finishDates.size(); currentStudent++){
                if(date == finishDates.get(currentStudent).getDate() && yearFrom <= finishDates.get(currentStudent).getYear()
                        && finishDates.get(currentStudent).getYear() >= yearTo){
                    numbersOfSearchElements.add(currentStudent);
                }
            }
        }
    }

    public DefaultTableModel  getModelOfSearchTable(){
        modelOfSearchTable = new DefaultTableModel();
        modelOfSearchTable.addColumn("ФИО");
        modelOfSearchTable.addColumn("Дата рождения");
        modelOfSearchTable.addColumn("Дата поступления");
        modelOfSearchTable.addColumn("Дата окончания");
        Object [] names = studentList.getNames();
        ArrayList<Date> birthdays = studentList.getBirthdays();
        ArrayList<Date> enterDates = studentList.getEnterDate();
        ArrayList<Date> finishDates = studentList.getFinishDate();
        SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
        for (Integer numbersOfSearchElement : numbersOfSearchElements) {
            String[] rowData = {names[numbersOfSearchElement].toString(),
                    format1.format(birthdays.get(numbersOfSearchElement)),
                    format1.format(enterDates.get(numbersOfSearchElement)),
                    format1.format(finishDates.get(numbersOfSearchElement))};
            modelOfSearchTable.addRow(rowData);
        }
        return modelOfSearchTable;
    }

    public void nextPage(){
        page = (studentList.size() / numOfRows) + 1;
        if(currentPage < page)
        this.currentPage++;
        updateModel();
    }

    public void prevPage(){
        page = (studentList.size() / numOfRows) + 1;
        if(currentPage > 1)
            this.currentPage--;
        updateModel();
    }

    public int getCurrentPage(){
        return currentPage;
    }

    public int getPage(){
        return page;
    }

    public void firstPage(){
        currentPage = 1;
        updateModel();
    }

    public void lastPage(){
        currentPage = page;
        updateModel();
    }

    public int numOfAllStudents(){
      return studentList.size();
    }

    public int numOfRecordsOnPage(){
        if(currentPage == page){

           return  studentList.size() - numOfRows * (page - 1);

        }
        return numOfRows;
    }

}
