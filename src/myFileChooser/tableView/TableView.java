package myFileChooser.tableView;

import myFileChooser.ChooseDialog;
import myFileChooser.chooseModel.ChooseView;
import myFileChooser.chooseModel.MyFileView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by USER on 21.06.15.
 */
public class TableView extends ChooseView {

    private JTable table;
    private DefaultTableModel model;
    private List<MyFileView> listFiles = new ArrayList<MyFileView>();

    public TableView(ChooseDialog dialog){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        this.dialog = dialog;
        model = new DefaultTableModel();
        table= new JTable(model);
        model.addColumn("Имя");
        model.addColumn("расширение");
        model.addColumn("размер");
        model.addColumn("дата последнего изменения");
        createRoots();
        add(new JScrollPane(table));
    }

    protected void newModel(){
        model = new DefaultTableModel();
        model.addColumn("Имя");
        model.addColumn("расширение");
        model.addColumn("размер");
        model.addColumn("дата последнего изменения");
        table.setModel(model);
        listFiles = new ArrayList<MyFileView>();
    }
    @Override
    public void addListeners(MyFileView fileView) {

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                //table.clearSelection();
                if(row > -1){
                    MyFileView myFileView = listFiles.get(row);

                File file = myFileView.getFile();
                if(file.isDirectory()){
                    newModel();
                    fileCurrentDir = myFileView.getFile();
                    paintDirectory(myFileView);
                }else{
                    dialog.setFile(myFileView.getFile());
                }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        listFiles.add(fileView);

    }


    @Override
    public void setContent() {

        String[] row = new String[4];
        for(int currentRow = 0; currentRow < listFiles.size(); currentRow++){
            File file = listFiles.get(currentRow).getFile();
            if(file.getName().equals("")){
                row[0] =file.getAbsolutePath();
            }else{
                row[0]= file.getName();
            }
            String extension = "";

            int i = file.getName().lastIndexOf('.');
            if (i > 0) {
                extension = file.getName().substring(i + 1);
            }
            if(extension.equals("")){
                row[1] = "папка";
            }else {
                row[1] = extension;
            }
            row[2] = Objects.toString(file.length(), null) ;
            Date date = new Date(file.lastModified());
            DateFormat format = new SimpleDateFormat("dd.mm.y");
            row[3] = format.format(date);
            model.addRow(row);
        }
    }
}