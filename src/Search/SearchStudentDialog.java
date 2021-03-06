package search;


import mainPackage.Controller;
import pageView.PageViewComponent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 07.04.15.
 */
public class SearchStudentDialog extends JDialog {
    private JTextField firstName = new JTextField(20);
    private JTextField lastName = new JTextField(20);
    private JTextField secondName = new JTextField(20);
    private JButton deleteButton;

    private JButton findButton;
    private SearchDateComponent birthday;
    private SearchDateComponent dateEnter;
    private SearchDateComponent dateFinish;
    private Controller controller;
    private PageViewComponent pageViewComponent;
    PageViewComponent pageComponentOut;
    private JRadioButton nameBDay = new JRadioButton("ФИО + день рждения");
    private JRadioButton nameEnDay = new JRadioButton("ФИО + день поступления");
    private JRadioButton nameFinDay = new JRadioButton("ФИО + день окончания");
    private JRadioButton BDayMonth = new JRadioButton("день + месяц рождения");
    private JRadioButton EnDayMonth = new JRadioButton("день + месяц поступления");
    private JRadioButton FinDayMonth = new JRadioButton("день + месяц окончания");
    private JRadioButton BDayYear = new JRadioButton("день + год рождения");
    private JRadioButton EnDayYear = new JRadioButton("день + год поступления");
    private JRadioButton FinDayYear = new JRadioButton("день + год окончания");
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel namePanel = new JPanel();

    public SearchStudentDialog(Controller control, PageViewComponent pageComponentOut){
        this.pageComponentOut = pageComponentOut;
        this.setTitle("Поиск");
        this.controller = control;
        buttonGroup.add(nameBDay);
        buttonGroup.add(nameEnDay);
        buttonGroup.add(nameFinDay);
        buttonGroup.add(BDayMonth);
        buttonGroup.add(EnDayMonth);
        buttonGroup.add(FinDayMonth);
        buttonGroup.add(BDayYear);
        buttonGroup.add(EnDayYear);
        buttonGroup.add(FinDayYear);
        Box radioButtonBox = Box.createVerticalBox();
        radioButtonBox.setBorder(new TitledBorder("критерии поиска"));
        radioButtonBox.add(nameBDay);
        radioButtonBox.add(nameEnDay);
        radioButtonBox.add(nameFinDay);
        radioButtonBox.add(BDayMonth);
        radioButtonBox.add(EnDayMonth);
        radioButtonBox.add(FinDayMonth);
        radioButtonBox.add(BDayYear);
        radioButtonBox.add(EnDayYear);
        radioButtonBox.add(FinDayYear);
        this.controller = control;
        pageViewComponent = new PageViewComponent(controller, 1);

        deleteButton = new JButton("удалить");

        birthday = new SearchDateComponent();
        dateEnter = new SearchDateComponent();
        dateFinish = new SearchDateComponent();
        findButton = new JButton("Найти");
       // setResizable(false);
        Box greatestBox = Box.createVerticalBox();
        JPanel panel = new JPanel();
        Box boxMain = Box.createHorizontalBox();
        Box boxMan = Box.createVerticalBox();
        boxMain.add(radioButtonBox);
        boxMain.add(boxMan);
        boxMain.add(Box.createHorizontalStrut(5));
        panel.add(boxMain);
        Box labelBox = Box.createVerticalBox();
        Box fieldNameBox = Box.createVerticalBox();

        Box nameBox = Box.createHorizontalBox();
        nameBox.add(labelBox);
        nameBox.add(Box.createHorizontalStrut(10));
        nameBox.add(fieldNameBox);
        namePanel.add(nameBox);

        labelBox.add(new JLabel("Фамилия"));
        labelBox.add(Box.createVerticalStrut(10));
        labelBox.add(new JLabel("Имя"));
        labelBox.add(Box.createVerticalStrut(10));
        labelBox.add(new JLabel("Отчество"));

        boxMan.add(namePanel);
        boxMan.add(birthday);
        boxMan.add(dateEnter);
        boxMan.add(dateFinish);
        boxMan.add(findButton);

        fieldNameBox.add(lastName);
        fieldNameBox.add(Box.createVerticalStrut(10));
        fieldNameBox.add(firstName);
        fieldNameBox.add(Box.createVerticalStrut(10));
        fieldNameBox.add(secondName);
        birthday.setMainLabel("Дата рождения ");
        dateEnter.setMainLabel("Дата поступления ");
        dateFinish.setMainLabel("Дата окончания ");
        greatestBox.add(panel);
        greatestBox.add(pageViewComponent);
        greatestBox.add(deleteButton);
        add(greatestBox);

        initListeners();
        pack();
    }
    private void setVisibility(boolean flag){
        namePanel.setVisible(flag);
        birthday.setVisible(flag);
        dateEnter.setVisible(flag);
        dateFinish.setVisible(flag);
    }

    private void initListeners(){
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameBDay.isSelected()){
                String name = lastName.getText() + " " + firstName.getText() + " " + secondName.getText();
                controller.findWithNameAndBDay(1,name,birthday.getDate());
                }
                if(nameEnDay.isSelected()){
                    String name = lastName.getText() + " " + firstName.getText() + " " + secondName.getText();
                    controller.findWithNameAndBDay(2,name,dateEnter.getDate());
                }
                if(nameFinDay.isSelected()){
                    String name = lastName.getText() + " " + firstName.getText() + " " + secondName.getText();
                    controller.findWithNameAndBDay(3,name,dateFinish.getDate());
                }
                if(BDayMonth.isSelected()){
                  controller.findStudentWithDate(1, birthday.getDate(), birthday.getMonth());
                }
                if(EnDayMonth.isSelected()){
                    controller.findStudentWithDate(2, dateEnter.getDate(), dateEnter.getMonth());
                }
                if(FinDayMonth.isSelected()){
                    controller.findStudentWithDate(3, dateFinish.getDate(), dateFinish.getMonth());
                }
                if(BDayYear.isSelected()){
                    controller.findStudentWithDate(1, birthday.getDate(), birthday.getYearFrom(), birthday.getYearTo());
                }
                if(EnDayYear.isSelected()){
                    controller.findStudentWithDate(2, dateEnter.getDate(), dateEnter.getYearFrom(), dateEnter.getYearTo());
                }
                if(FinDayYear.isSelected()){
                    controller.findStudentWithDate(3, dateFinish.getDate(), dateFinish.getYearFrom(), dateFinish.getYearTo());
                }

                pageViewComponent.updateModel();

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.delStudents();
                pageViewComponent.updateModel();
                pageComponentOut.updateModel();
            }
        });
        nameBDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                namePanel.setVisible(true);
                birthday.setVisibleAll(false);
                birthday.setDayVisible(true);
                birthday.setVisible(true);
            }
        });
        nameEnDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                namePanel.setVisible(true);
                dateEnter.setVisibleAll(false);
                dateEnter.setDayVisible(true);
                dateEnter.setVisible(true);
            }
        });
        nameFinDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                namePanel.setVisible(true);
                dateFinish.setVisibleAll(false);
                dateFinish.setDayVisible(true);
                dateFinish.setVisible(true);
            }
        });
        BDayMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                birthday.setVisible(true);
                birthday.setVisibleAll(false);
                birthday.setDayVisible(true);
                birthday.setMonthVisible(true);
            }
        });
        EnDayMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                dateEnter.setVisible(true);
                dateEnter.setVisibleAll(false);
                dateEnter.setDayVisible(true);
                dateEnter.setMonthVisible(true);

            }
        });
        FinDayMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                dateFinish.setVisible(true);
                dateFinish.setVisibleAll(false);
                dateFinish.setDayVisible(true);
                dateFinish.setMonthVisible(true);
            }
        });
        BDayYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                birthday.setVisible(true);
                birthday.setVisibleAll(false);
                birthday.setDayVisible(true);
                birthday.setYearVisible(true);
            }
        });
        EnDayYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                dateEnter.setVisible(true);
                dateEnter.setVisibleAll(false);
                dateEnter.setDayVisible(true);
                dateEnter.setYearVisible(true);

            }
        });
        FinDayYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                dateFinish.setVisible(true);
                dateFinish.setVisibleAll(false);
                dateFinish.setDayVisible(true);
                dateFinish.setYearVisible(true);
            }
        });
    }
}