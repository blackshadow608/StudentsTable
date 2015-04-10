package MainPackge;

import AddStudent.AddStudentDialog;
import Search.SearchStudentDialog;
import SettingsMenu.Settings;
//import Search.NameAndDate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by USER on 30.03.15.
 */
public class MainView {

    private JButton nextPage;
    private JButton previousPage;
    private JButton toFirstsPage;
    private JButton toLastPage;
    private JTable table;
    private JMenuItem addStudent = new JMenuItem("Добавить студента");
    private JMenuItem saveFile = new JMenuItem("Сохранить");
    private JMenuItem openFile = new JMenuItem("Открыть");
    private JMenuItem settingsMenu = new JMenuItem("Настройки таблицы");
    private JMenuItem find = new JMenuItem("Найти студента");
    private JLabel currentPage = new JLabel("Страница : ");
    private JLabel numOfRecords = new JLabel();
    private Controller controller;
    private DefaultTableModel model;

    MainView(Controller controller){
        this.controller = controller;
        currentPage.setText("Страница: " + String.valueOf(controller.getCurrentPage()) +
                " / " + String.valueOf(controller.getPage()));
        numOfRecords.setText("Всего записей: " + String.valueOf(controller.numOfAllStudents()) +
                "   Записей на странице: " + String.valueOf(controller.numOfRecordsOnPage()));
         previousPage = new JButton("<");
        toFirstsPage = new JButton("<<");
        nextPage = new JButton(">");
        toLastPage = new JButton(">>");
        JFrame mainFrame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenu searchMenu = new JMenu("Поиск");
        JMenu settings = new JMenu("Настройки");
        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        menuBar.add(settings);
        settings.add(settingsMenu);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        searchMenu.add(find);
        fileMenu.add(addStudent);
        model = controller.getModel();
        table = new JTable(model);
        Box box = Box.createVerticalBox();
        Box labelBox = Box.createHorizontalBox();
        labelBox.add(numOfRecords);
        box.add(labelBox);
        box.add(new JScrollPane(table));
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(toFirstsPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(previousPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(currentPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(nextPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(toLastPage);
        box.add(buttonBox);
        mainFrame.setTitle("Таблица студентов");
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(box);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();
        initListeners();
    }
    private void initListeners(){
        addStudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AddStudentDialog dialog = new AddStudentDialog(controller ,table, currentPage, numOfRecords);
                dialog.setVisible(true);
                numOfRecords.setText("Всего записей: " + String.valueOf(controller.numOfAllStudents()) +
                        "   Записей на странице: " + String.valueOf(controller.numOfRecordsOnPage()));
            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    controller.Save();

            }
        });

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.Open();
                table.setModel(controller.getModel());
                currentPage.setText("Страница: " + String.valueOf(controller.getCurrentPage()) +
                        " / " + String.valueOf(controller.getPage()));
                numOfRecords.setText("Всего записей: " + String.valueOf(controller.numOfAllStudents()) +
                        "   Записей на странице: " + String.valueOf(controller.numOfRecordsOnPage()));
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchStudentDialog dialog = new SearchStudentDialog(controller,model);
                dialog.setVisible(true);
            }
        });

        settingsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settingsDialog = new Settings(controller, table, currentPage, numOfRecords);
                settingsDialog.setVisible(true);
                table.setModel(controller.getModel());
            }
        });

        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nextPage();
                currentPage.setText("Страница: " + String.valueOf(controller.getCurrentPage()) +
                        " / " + String.valueOf(controller.getPage()));
                table.setModel(controller.getModel());
                numOfRecords.setText("Всего записей: " + String.valueOf(controller.numOfAllStudents()) +
                        "   Записей на странице: " + String.valueOf(controller.numOfRecordsOnPage()));
            }
        });

        previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.prevPage();
                currentPage.setText("Страница: " + String.valueOf(controller.getCurrentPage()) +
                        " / " + String.valueOf(controller.getPage()));
                table.setModel(controller.getModel());
                numOfRecords.setText("Всего записей: " + String.valueOf(controller.numOfAllStudents()) +
                        "   Записей на странице: " + String.valueOf(controller.numOfRecordsOnPage()));
            }
        });

        toLastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.lastPage();
                currentPage.setText("Страница: " + String.valueOf(controller.getCurrentPage()) +
                        " / " + String.valueOf(controller.getPage()));
                table.setModel(controller.getModel());
                numOfRecords.setText("Всего записей: " + String.valueOf(controller.numOfAllStudents()) +
                        "   Записей на странице: " + String.valueOf(controller.numOfRecordsOnPage()));
            }
        });

        toFirstsPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.firstPage();
                currentPage.setText("Страница: " + String.valueOf(controller.getCurrentPage()) +
                        " / " + String.valueOf(controller.getPage()));
                table.setModel(controller.getModel());
                numOfRecords.setText("Всего записей: " + String.valueOf(controller.numOfAllStudents()) +
                        "   Записей на странице: " + String.valueOf(controller.numOfRecordsOnPage()));
            }
        });
    }
}
