package addStudent;

import javax.swing.*;
import java.util.Date;

/**
 * Created by USER on 06.04.15.
 */
public class EnterDateComponent extends JPanel {
    private JTextField dayField = new JTextField(2);
    private JLabel dayLabel = new JLabel("День");
    private JTextField monthField = new JTextField(2);
    private JLabel monthLabel = new JLabel("Месяц");
    private JTextField yearField = new JTextField(4);
    private JLabel yearLabel = new JLabel("Год");

    public EnterDateComponent(){

        Box box = Box.createHorizontalBox();
        box.add(dayLabel);
        box.add(Box.createHorizontalStrut(2));
        box.add(dayField);
        box.add(Box.createHorizontalStrut(5));
        box.add(monthLabel);
        box.add(Box.createHorizontalStrut(2));
        box.add(monthField);
        box.add(Box.createHorizontalStrut(5));
        box.add(yearLabel);
        box.add(Box.createHorizontalStrut(2));
        box.add(yearField);
        box.add(Box.createHorizontalStrut(5));
       add(box);
    }
    public Date getDate(){
        Date date = new Date(Integer.parseInt(yearField.getText()) - 1900, Integer.parseInt(monthField.getText()) - 1,
                Integer.parseInt(dayField.getText()));
        return date;
    }
}
