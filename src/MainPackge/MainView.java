package MainPackge;

import AddStudent.AddStudentDialog;
import PageView.PageViewComponent;
import Search.SearchStudentDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 30.03.15.
 */
public class MainView {

    private JButton nextPage;
    private JButton previousPage;
    private JButton toFirstsPage;
    private JButton toLastPage;
    private JButton applyPages;
    private JTable table;
    private JMenuItem addStudent = new JMenuItem("Добавить студента");
    private JMenuItem saveFile = new JMenuItem("Сохранить");
    private JMenuItem openFile = new JMenuItem("Открыть");
    private JMenuItem find = new JMenuItem("Найти студента");
    private JLabel currentPage = new JLabel("Страница : ");
    private JLabel numOfRecords = new JLabel();
    private JTextField numOfRows = new JTextField(3);
    private Controller controller;
    private PageViewComponent pageComponent;
    private DefaultTableModel model;

    MainView(Controller controller){
        this.controller = controller;

         previousPage = new JButton("<");
        toFirstsPage = new JButton("<<");
        nextPage = new JButton(">");
        toLastPage = new JButton(">>");
        applyPages = new JButton("применить");
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
        model = controller.getModel();
        table = new JTable(model);
        pageComponent = new PageViewComponent(model, table, controller , 0);
        pageComponent.getLabels(numOfRecords,currentPage);
        pageComponent.updateLabels();
        Box box = Box.createVerticalBox();

        box.add(new JScrollPane(table));
        JPanel buttonsPanel = new JPanel();
        Box numOfPagesBox = Box.createHorizontalBox();
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(numOfRecords);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonsPanel.add(buttonBox);
        buttonBox.add(toFirstsPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(previousPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(currentPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(nextPage);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(toLastPage);

        JPanel panelRows = new JPanel();
        panelRows.add(numOfPagesBox);

        numOfPagesBox.add(new JLabel("Записей на странице: "));
        numOfPagesBox.add(numOfRows);
        numOfRows.setText("10");
        numOfPagesBox.add(applyPages);
        buttonBox.add(panelRows);
        box.add(buttonsPanel);
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
                pageComponent.updateLabels();
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchStudentDialog dialog = new SearchStudentDialog(controller,model);
                dialog.setVisible(true);
            }
        });


        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageComponent.nextPage();
            }
        });

        previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               pageComponent.prevPage();
            }
        });

        toLastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               pageComponent.lastPage();
            }
        });

        toFirstsPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageComponent.firstPage();
            }
        });

        applyPages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageComponent.setNumOfRecords(Integer.parseInt(numOfRows.getText()));
            }
        });
    }
}
