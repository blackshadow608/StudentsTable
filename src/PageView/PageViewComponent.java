package pageView;

import mainPackage.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by USER on 11.04.15.
 */
public class PageViewComponent extends JPanel {

    private int currentPage;
    private int numOfRecords;
    private JLabel numOfRecordsLabel;
    private JLabel numOfPagesLabel;
    private ButtonsPanel buttonsPanel = new ButtonsPanel(this);
    private TableComponent tableComponent;

    public PageViewComponent( Controller controller, int TypeOfRowData){
        currentPage = 1;
        numOfRecords = 10;
        tableComponent = new TableComponent(controller, currentPage, numOfRecords,TypeOfRowData);
        Box box = Box.createVerticalBox();
        box.add(tableComponent);
        box.add(buttonsPanel);
        add(box);
        //buttonsPanel.getLabels(numOfRecordsLabel,numOfPagesLabel);
    }

    public void updateModel(){
        tableComponent.updateModel(currentPage, numOfRecords);
    }

    public void getLabels(JLabel numOfRecords, JLabel numOfPages){
        this.numOfRecordsLabel = numOfRecords;
        this.numOfPagesLabel = numOfPages;
    }


    public void nextPage(){
        if(currentPage < tableComponent.getPages())
            currentPage++;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.updateLabels();
    }
    public void prevPage(){
        if(currentPage > 1)
            currentPage--;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.updateLabels();
    }
    public void firstPage(){
        currentPage = 1;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.updateLabels();
    }
    public void lastPage(){
        currentPage = tableComponent.getPages();
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.updateLabels();
    }

    public void setNumOfRecords(int numOfRecords){
        this.numOfRecords = numOfRecords;
        currentPage = 1;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.updateLabels();
    }


}
