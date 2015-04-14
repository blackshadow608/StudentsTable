package PageView;

import javax.swing.*;

/**
 * Created by USER on 14.04.15.
 */
public class ButtonsPanel extends JComponent {
    private JButton nextPage;
    private JButton previousPage;
    private JButton toFirstsPage;
    private JButton toLastPage;
    private JButton applyPages;
    private JLabel currentPage = new JLabel("Страница : ");
    private JLabel numOfRecords = new JLabel();
    private JTextField numOfRows = new JTextField(3);

    public ButtonsPanel(){
        previousPage = new JButton("<");
        toFirstsPage = new JButton("<<");
        nextPage = new JButton(">");
        toLastPage = new JButton(">>");
        applyPages = new JButton("применить");
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
    }

}
