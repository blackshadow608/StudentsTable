package PageView;

import MainPackge.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11.04.15.
 */
public class PageViewComponent {
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

    public PageViewComponent(DefaultTableModel model, JTable table, Controller controller, int TypeOfRowData){
        this.TypeOfRowData = TypeOfRowData;
        this.controller = controller;
        this.model = model;
        this.table = table;
        currentPage = 1;
        pages = 1;
        numOfRecords = 10;
    }

    public void updateModel(){
        model = null;
        model = new DefaultTableModel();
        model.addColumn("ФИО");
        model.addColumn("Дата рождения");
        model.addColumn("Дата поступления");
        model.addColumn("Дата окончания");
        table.setModel(model);
        List<String[]> rowsValue = controller.getRows(TypeOfRowData);
        allRecords = rowsValue.size();
        pages = (rowsValue.size() / numOfRecords) + 1;
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

    public void getLabels(JLabel numOfRecords, JLabel numOfPages){
        this.numOfRecordsLabel = numOfRecords;
        this.numOfPagesLabel = numOfPages;
    }
    public void updateLabels(){
        numOfPagesLabel.setText("Страница: " + String.valueOf(currentPage) +
                " / " + String.valueOf(pages));
        numOfRecordsLabel.setText("Всего записей: " + String.valueOf(allRecords));
    }

    public void nextPage(){
        if(currentPage < pages)
        currentPage++;
        updateModel();
        updateLabels();
    }
    public void prevPage(){
        if(currentPage > 1)
        currentPage--;
        updateModel();
        updateLabels();
    }
    public void firstPage(){
        currentPage = 1;
        updateModel();
        updateLabels();
    }
    public void lastPage(){
        currentPage = pages;
        updateModel();
        updateLabels();
    }

    public void setNumOfRecords(int numOfRecords){
        this.numOfRecords = numOfRecords;
        this.currentPage = 1;
        updateModel();
        updateLabels();
    }

}
