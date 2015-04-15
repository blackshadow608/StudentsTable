package mainPackage;

import addStudent.AddStudentDialog;
import pageView.PageViewComponent;
import search.SearchStudentDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 30.03.15.
 */
public class MainView {

    private JMenuItem addStudent = new JMenuItem("Добавить студента");
    private JMenuItem saveFile = new JMenuItem("Сохранить");
    private JMenuItem openFile = new JMenuItem("Открыть");
    private JMenuItem find = new JMenuItem("Найти студента");
    private Controller controller;
    private PageViewComponent pageComponent;


    MainView(Controller controller){
        this.controller = controller;
        JFrame mainFrame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenu searchMenu = new JMenu("Поиск");
        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        searchMenu.add(find);
        fileMenu.add(addStudent);
        pageComponent = new PageViewComponent(controller , 0);
        Box box = Box.createVerticalBox();
      //  box.setLayout(new BorderLayout());
        box.add(pageComponent);
        mainFrame.setTitle("Таблица студентов");
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(pageComponent);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();
        initListeners();
    }
    private void initListeners(){
        addStudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AddStudentDialog dialog = new AddStudentDialog(controller, pageComponent);
                dialog.setVisible(true);

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
                pageComponent.updateModel();
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchStudentDialog dialog = new SearchStudentDialog(controller, pageComponent);
                dialog.setVisible(true);
                pageComponent.updateModel();
            }
        });



    }
}
