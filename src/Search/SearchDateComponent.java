package Search;

import javax.swing.*;
import java.util.Date;

/**
 * Created by USER on 08.04.15.
 */
public class SearchDateComponent extends JPanel {
    private JTextField dayField = new JTextField(2);
    private JLabel dayLabel = new JLabel("День");
    private JTextField monthField = new JTextField(2);
    private JLabel monthLabel = new JLabel("Месяц");
    private JTextField yearFieldFrom = new JTextField(4);
    private JTextField yearFieldTo = new JTextField(4);
    private JLabel yearLabel = new JLabel("Год");

    public SearchDateComponent(){
        JPanel panel = new JPanel();
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
        box.add(new JLabel("от:"));
        box.add(Box.createHorizontalStrut(2));
        box.add(yearFieldFrom);
        box.add(new JLabel("до:"));
        box.add(Box.createHorizontalStrut(2));
        box.add(yearFieldTo);
        box.add(Box.createHorizontalStrut(5));
        add(box);
    }
    public int getDate(){
        return Integer.parseInt(dayField.getText());
    }
    public int getMonth(){
        return Integer.parseInt(monthField.getText());
    }
    public int getYearFrom(){
        return Integer.parseInt(yearFieldFrom.getText());
    }
    public int getYearTo(){
        return Integer.parseInt(yearFieldTo.getText());
    }
}
