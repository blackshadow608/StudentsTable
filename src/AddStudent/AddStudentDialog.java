package AddStudent;

import MainPackge.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 03.04.15.
 */
public class AddStudentDialog extends JDialog {
    private JTextField name;

    private JButton addButton;
    private EnterDateComponent birthday;
    private EnterDateComponent dateEnter;
    private EnterDateComponent dateFinish;
    private Controller controller;
    private JTable table;
    private JLabel currentPage;


    public AddStudentDialog(Controller control,JTable table, JLabel currentPage){
        this.currentPage = currentPage;
        this.table = table;
        this.controller = control;
        name = new JTextField(30);
        birthday = new EnterDateComponent();
        dateEnter = new EnterDateComponent();
        dateFinish = new EnterDateComponent();
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
        initListeners();
        pack();
    }
    private void initListeners(){
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controller.addNewStudent(name.getText(), birthday.getDate(), dateEnter.getDate(),
                       dateFinish.getDate());
                table.setModel(controller.getModel());
                currentPage.setText("Страница: " + String.valueOf(controller.getCurrentPage()) +
                        " / " + String.valueOf(controller.getPage()));
            }
        });

}

}
