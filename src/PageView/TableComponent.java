package pageView;

import mainPackage.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Created by USER on 14.04.15.
 */
public class TableComponent {
    private DefaultTableModel model;
    private JTable table;
    private int currentPage;
    private int pages;
    private int numOfRecords;
    private int allRecords;
    private JLabel numOfRecordsLabel;
    private JLabel numOfPagesLabel;
    private Controller controller;
    private int TypeOfRowData;

    public TableComponent(Controller controller, int currentPage, int numOfRecords, int TypeOfRowData, JTable table){
        this.TypeOfRowData = TypeOfRowData;
        model = new DefaultTableModel();
        this.table = table;
        this.controller = controller;
        this.currentPage = currentPage;
        this.pages = 1;
        this.numOfRecords = numOfRecords;
    }
    public void updateModel(int currentPage, int numOfRecords){
        this.currentPage = currentPage;
        //this.pages = pages;
        this.numOfRecords = numOfRecords;
        model = null;
        model = new DefaultTableModel();
        model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");
        table.setModel(model);
        List<String[]> rowsValue = controller.getRows(TypeOfRowData);
        if(rowsValue != null){
            allRecords = rowsValue.size();
            if(rowsValue.size() % numOfRecords== 0){
                this.pages = (rowsValue.size() / numOfRecords);
            }
            else{
                this.pages = (rowsValue.size() / numOfRecords) + 1;
            }
            int rows;
            rows = (currentPage - 1) * (numOfRecords);
            int numOfLastRow;
            numOfLastRow = rows + numOfRecords;
            if(rowsValue.size() < numOfLastRow) {
                numOfLastRow = rowsValue.size();
            }
            for(int row = rows ; row < numOfLastRow; row++ ){
                model.addRow(rowsValue.get(row));
            }
        }

    }
    public int getPages(){
        return pages;
    }
    public int getNumOfRecords(){
        return allRecords;
    }
}