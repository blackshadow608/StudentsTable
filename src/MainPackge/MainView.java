package MainPackge;

import AddStudent.AddStudentDialog;
//import Search.NameAndDate;

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
    private JTable table;
    private JMenuItem addStudent = new JMenuItem("Добавить студента");
    private Controller controller;
    private DefaultTableModel model;

    MainView(Controller controller){
        this.controller = controller;
        JPanel panel = new JPanel();

        previousPage = new JButton("Предыдущая страница");
        nextPage = new JButton("Следующая страница");

        JFrame mainFrame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenu searchMenu = new JMenu("Поиск");

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);

        JMenuItem openFile = new JMenuItem("Открыть");
        fileMenu.add(openFile);
        JMenuItem saveFile = new JMenuItem("Сохранить");
        fileMenu.add(saveFile);
        JMenuItem find = new JMenuItem("Найти студента");
        searchMenu.add(find);

        fileMenu.add(addStudent);

        model = controller.getModel();
        table = new JTable(model);
        table.setSize(900,500);
        /*model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");*/

        Box box = Box.createVerticalBox();
        box.add(new JScrollPane(table));
        panel.add(new JScrollPane(table));
        box.setSize(800,800);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(previousPage);
        buttonBox.add(nextPage);
        box.add(buttonBox);

        mainFrame.add(panel);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(panel);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();
        initListeners();
    }
    private void initListeners(){
        addStudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AddStudentDialog dialog = new AddStudentDialog(controller,model);
                dialog.setVisible(true);
               // model.addRow(dialog.ser());
            }
        });
    }
}
