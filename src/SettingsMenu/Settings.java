package SettingsMenu;

import MainPackge.Controller;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 09.04.15.
 */
public class Settings extends JDialog {
    private JTextField numOfRows = new JTextField(3);
    private JTextField dateFormat = new JTextField(10);
    private JTextField separator = new JTextField(3);
    private JButton applyButton = new JButton("Применить");
    private Controller controller;

    public Settings(Controller controller){
        this.controller = controller;
        Box rowBox = Box.createHorizontalBox();
        rowBox.add(new JLabel("Количество записей на странице:"));
        rowBox.add(Box.createHorizontalStrut(5));
        rowBox.add(numOfRows);
//
//        Box dateFormatBox = Box.createHorizontalBox();
//        dateFormatBox.add(new JLabel("Формат даты:"));
//        dateFormatBox.add(Box.createHorizontalStrut(5));
//        dateFormatBox.add(dateFormat);
//
//        Box separatorBox = Box.createHorizontalBox();
//        separatorBox.add(new JLabel("Разделитель даты:"));
//        separatorBox.add(Box.createHorizontalStrut(5));
//        separatorBox.add(separator);

        Box mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(rowBox);
        mainBox.add(Box.createVerticalStrut(5));
       // mainBox.add(dateFormatBox);
        mainBox.add(Box.createVerticalStrut(5));
       // mainBox.add(separatorBox);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(applyButton);

        add(mainBox);
        setResizable(false);
        pack();
    }

    private void initListeners(){
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] settings = new String[3];
                int rows = Integer.parseInt(numOfRows.getText());
//                settings[1] = separator.getText();
//                settings[2] = dateFormat.getText();
                controller.setSetting(Integer.parseInt(numOfRows.getText()));
            }
        });
    }
}