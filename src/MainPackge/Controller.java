package MainPackge; /**
 * Created by USER on 06.04.15.
 */
import Model.StudentList;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
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

}
