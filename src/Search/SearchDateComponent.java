package search;

import javax.swing.*;

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
    private JLabel from = new JLabel("от:");
    private JLabel to =new JLabel("до:");
    private JLabel mainLabel = new JLabel("qweqweqwe");


    public SearchDateComponent(){

        JPanel dayPanel = new JPanel();
        dayPanel.add(dayField);
        Box box = Box.createHorizontalBox();
        box.add(mainLabel);
        box.add(Box.createHorizontalStrut(2));
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
        box.add(from);
        box.add(Box.createHorizontalStrut(2));
        box.add(yearFieldFrom);
        box.add(to);
        box.add(Box.createHorizontalStrut(2));
        box.add(yearFieldTo);
        box.add(Box.createHorizontalStrut(5));
        add(box);
    }
    public int getDate(){
        return Integer.parseInt(dayField.getText());
    }
    public int getMonth(){
        return Integer.parseInt(monthField.getText()) - 1;
    }
    public int getYearFrom(){
        return Integer.parseInt(yearFieldFrom.getText()) - 1900;
    }
    public int getYearTo(){
        return Integer.parseInt(yearFieldTo.getText()) - 1900;
    }
    public void setMainLabel(String text){
        mainLabel.setText(text);
    }
    public void setDayVisible(boolean flag){
        dayField.setVisible(flag);
        dayLabel.setVisible(flag);
    }
    public void setMonthVisible(boolean flag){
        monthField.setVisible(flag);
        monthLabel.setVisible(flag);
    }
    public void setYearVisible(boolean flag){
        yearFieldFrom.setVisible(flag);
        yearFieldTo.setVisible(flag);
        from.setVisible(flag);
        to.setVisible(flag);
        yearLabel.setVisible(flag);
    }
    public void setVisibleAll(boolean flag){
        dayField.setVisible(flag);
        dayLabel.setVisible(flag);
        monthField.setVisible(flag);
        monthLabel.setVisible(flag);
        yearFieldFrom.setVisible(flag);
        yearFieldTo.setVisible(flag);
        from.setVisible(flag);
        to.setVisible(flag);
        yearLabel.setVisible(flag);
    }
}
