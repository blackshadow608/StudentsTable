package Search;

import AddStudent.EnterDateComponent;
import MainPackge.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by USER on 07.04.15.
 */
public class SearchStudentDialog extends JDialog {
    private JTextField name;

    private JButton addButton;
    private SearchDateComponent birthday;
    private SearchDateComponent dateEnter;
    private SearchDateComponent dateFinish;
    private Controller controller;
    private DefaultTableModel model;


    public SearchStudentDialog(Controller control, DefaultTableModel model){
        this.setTitle("Поиск");
        this.model = model;
        this.controller = control;
        name = new JTextField(30);
        birthday = new SearchDateComponent();
        dateEnter = new SearchDateComponent();
        dateFinish = new SearchDateComponent();
        addButton = new JButton("Добавить");
        setResizable(false);

        Box boxMain = Box.createHorizontalBox();
        Box labelBox = Box.createVerticalBox();
        Box dataBox = Box.createVerticalBox();


        boxMain.add(labelBox);
        boxMain.add(dataBox);


        labelBox.add(new JLabel("ФИО"));
        labelBox.add(Box.createVerticalStrut(20));
        dataBox.add(Box.createVerticalStrut(15));
        dataBox.add(name);
        dataBox.add(Box.createVerticalStrut(10));
        labelBox.add(new JLabel("Дата Рождения"));
        labelBox.add(Box.createVerticalStrut(20));
        dataBox.add(birthday);
        dataBox.add(Box.createVerticalStrut(10));
        labelBox.add(new JLabel("Дата поступления"));
        labelBox.add(Box.createVerticalStrut(20));
        dataBox.add(dateEnter);
        dataBox.add(Box.createVerticalStrut(10));
        labelBox.add(new JLabel("Дата окончания"));
        labelBox.add(Box.createVerticalStrut(20));
        dataBox.add(dateFinish);
        dataBox.add(Box.createVerticalStrut(0));

        dataBox.add(addButton);

        add(new JPanel().add(boxMain));
        //initListeners();
        pack();
    }

}
