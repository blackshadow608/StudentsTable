package myFileChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by USER on 04.06.15.
 */
public class TableNavigate extends JPanel {
    private JTable table = new JTable();
    private DefaultTableModel model = new DefaultTableModel();

    public TableNavigate(){
        table.setModel(model);
       // model.addColumn();
    }
}
