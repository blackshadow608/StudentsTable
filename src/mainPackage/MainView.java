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
    private JToolBar toolBar;
    private JButton saveButton = new JButton("Сохранить");
    private JButton openButton = new JButton("Открыть");
    private JButton addButton = new JButton("Добавить студента");
    private JButton findButton = new JButton("Найти");


    MainView(Controller controller){
        JPanel panel = new JPanel();
        this.toolBar = new JToolBar();
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.add(addButton);
        toolBar.add(findButton);
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
      // panel.add(box);

        box.add(pageComponent);
        box.add(toolBar);

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
                AddStudentDialog dialog = new AddStudentDialog(controller, pageComponent);
                dialog.setVisible(true);

            }
        });
        addButton.addActionListener(new ActionListener() {

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
        saveButton.addActionListener(new ActionListener() {
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
        openButton.addActionListener(new ActionListener() {
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
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchStudentDialog dialog = new SearchStudentDialog(controller, pageComponent);
                dialog.setVisible(true);
                pageComponent.updateModel();
            }
        });



    }
}
