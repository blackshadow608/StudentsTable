package pageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 14.04.15.
 */
public class ButtonsPanel extends JPanel {
    private JButton nextPage;
    private JButton previousPage;
    private JButton toFirstsPage;
    private JButton toLastPage;
    private JButton applyPages;
    private JLabel currentPage = new JLabel("Страница : ");
    private JLabel numOfRecords = new JLabel();
    private JTextField numOfRows = new JTextField(3);
    private PageViewComponent pageComponent;
    private int pages;
    private int records;
    private int current;

    public ButtonsPanel(PageViewComponent pageView){
        pages = 1;
        records = 0;
        current = 1;
        this.pageComponent = pageView;
        previousPage = new JButton("<");
        toFirstsPage = new JButton("<<");
        nextPage = new JButton(">");
        toLastPage = new JButton(">>");
        applyPages = new JButton("применить");
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
        add(buttonBox);
        initListeners();
    }

    private void initListeners(){
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

    public void updateLabels(){
         currentPage.setText("Страница: " + String.valueOf(current) +
                " / " + String.valueOf(pages));
        numOfRecords.setText("Всего записей: " + String.valueOf(records));
    }
    public void setPagesAndRecords(int pages, int records, int currentPage){
        this.pages = pages;
        this.records = records;
        this.current = currentPage;
    }
}