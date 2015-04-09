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

    public Controller(StudentList studentList){
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
        SimpleDateFormat format1 = new SimpleDateFormat("dd:MM:y");
        String [] row = {name,format1.format(birthday),format1.format(enterDate), format1.format(finishDate)};
        model.addRow(row);
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
    public DefaultTableModel Open(){
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

            return this.updateModel();
        }
        return this.updateModel();
    }

    public DefaultTableModel updateModel(){
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
        SimpleDateFormat format1 = new SimpleDateFormat("dd:MM:y");
        for (int row = 0 ; row < names.length; row++){

            String[] rowData = {names[row].toString(), format1.format(birthdays.get(row)),
                    format1.format(enterDates.get(row)), format1.format(finishDates.get(row))};
            model.addRow(rowData);
        }
        return model;

    }

}
