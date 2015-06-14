package addStudent;

import mainPackage.Controller;
import pageView.PageViewComponent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 03.04.15.
 */
public class AddStudentDialog extends JDialog {
    private JTextField firstName = new JTextField(10);
    private JTextField lastName = new JTextField(10);
    private JTextField secondName = new JTextField(10);
    private JButton addButton;
    private EnterDateComponent birthday;
    private EnterDateComponent dateEnter;
    private EnterDateComponent dateFinish;
    private Controller controller;
    private PageViewComponent pageComponent;


    public AddStudentDialog(Controller control, PageViewComponent pageComponent){
        this.pageComponent = pageComponent;
        this.setTitle("Добавить студента");
        this.controller = control;
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
        labelBox.add(new JLabel("Фамилия"));
        labelBox.add(Box.createVerticalStrut(20));
        dataBox.add(Box.createVerticalStrut(15));
        dataBox.add(lastName);
        labelBox.add(new JLabel("Имя"));
        labelBox.add(Box.createVerticalStrut(20));
        dataBox.add(Box.createVerticalStrut(15));
        dataBox.add(firstName);
        labelBox.add(new JLabel("Отчество"));
        labelBox.add(Box.createVerticalStrut(20));
        dataBox.add(Box.createVerticalStrut(15));
        dataBox.add(secondName);
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
               controller.addNewStudent(firstName.getText(),lastName.getText(),secondName.getText()
                       , birthday.getDate(), dateEnter.getDate(),
                       dateFinish.getDate());
                pageComponent.updateModel();

            }
        });

}

}
