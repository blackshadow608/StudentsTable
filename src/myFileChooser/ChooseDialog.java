package myFileChooser;

import myFileChooser.chooseModel.ChooseView;
import myFileChooser.folderView.FolderView;
import myFileChooser.listView.ListView;
import myFileChooser.tableView.TableView;
import myFileChooser.treeView.TreePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by USER on 04.06.15.
 */
public class ChooseDialog {
    private boolean isSuccess;
    private JButton okButton;
    private JButton cancelButton;
    private JButton upDirButton;
    private JButton listViewButton = new JButton(new ImageIcon("listIcon.png"));
    private JButton folderViewButton = new JButton(new ImageIcon("tileIcon.png"));
    private JButton tableViewButton = new JButton(new ImageIcon("tableIcon.png"));
    private JButton homeButton = new JButton(new ImageIcon("homeIcon.png"));
    private PathName directory;
    private JTextField fileNameField;
    private ChooseView tableChooseView;
    private ChooseView currentView;
    private ChooseView listChooseView;
    private ChooseView folderChooseView;
    private JDialog dialog;
    private JLabel pathLabel = new JLabel("root");
    private Box pathBox = Box.createHorizontalBox();
    private File chooseDir;
    private File chooseFile;
    private TreePanel treePanel ;
    private JScrollPane scrollPaneList;
    private JScrollPane scrollPaneFolders;
    private JPanel tablePanel = new JPanel();
    private File home;


    public ChooseDialog(String[] ext, String home){
        this.home = new File(home);
        dialog = new JDialog();
        dialog.setModal(true);
        directory = new PathName();
        tableChooseView = new TableView(this);
        tablePanel.setVisible(false);
        tablePanel.add(tableChooseView);
        listChooseView = new ListView(this);
        folderChooseView = new FolderView(this);
        currentView = folderChooseView;
        scrollPaneFolders = new JScrollPane(folderChooseView);
        scrollPaneFolders.setVisible(true);
        scrollPaneList = new JScrollPane(listChooseView);
        scrollPaneList.setVisible(false);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        Box mainBox = new Box(BoxLayout.Y_AXIS);
        Box buttonBox = Box.createVerticalBox();
        upDirButton = new JButton(new ImageIcon("folder_up.png"));
        upDirButton.setPreferredSize(new Dimension(32,32));
        listViewButton.setPreferredSize(new Dimension(32,32));
        folderViewButton.setPreferredSize(new Dimension(32,32));
        tableViewButton.setPreferredSize(new Dimension(32,32));
        homeButton.setPreferredSize(new Dimension(32, 32));
        JPanel pathPanel = new JPanel();
        mainBox.add(pathPanel);
        mainBox.add(pathLabel);
        pathPanel.add(pathBox);
        pathBox.add(listViewButton);
        pathBox.add(Box.createHorizontalStrut(5));
        pathBox.add(tableViewButton);
        pathBox.add(Box.createHorizontalStrut(5));
        pathBox.add(folderViewButton);
        pathBox.add(Box.createHorizontalStrut(5));
        pathBox.add(upDirButton);
        pathBox.add(Box.createHorizontalStrut(5));
        pathPanel.add(homeButton);


        treePanel = new TreePanel(this, listChooseView);
        treePanel.addChooseView(tableChooseView);
        treePanel.addChooseView(folderChooseView);
        Box areasBox = new Box(BoxLayout.X_AXIS);
        mainBox.add(areasBox);
       // treePanel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        scrollPaneList.setPreferredSize(new Dimension(450, 450));
        scrollPaneFolders.setPreferredSize(new Dimension(450, 450));
        JScrollPane treeScroll = new JScrollPane(treePanel);
        treeScroll.setPreferredSize(new Dimension(250, 250));
        areasBox.add(treeScroll);

        areasBox.add(scrollPaneList);
        areasBox.add(scrollPaneFolders);
        areasBox.add(tablePanel);
        fileNameField = new JTextField(40);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        Box buttons = Box.createHorizontalBox();
        buttons.add(okButton);
        buttons.add(Box.createHorizontalStrut(5));
        buttons.add(cancelButton);
        Box fieldBox = Box.createHorizontalBox();
        fieldBox.add(new JLabel("File: "));
        fieldBox.add(fileNameField);
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonBox);
        buttonBox.add(fieldBox);
        buttonBox.add(buttons);
        mainBox.add(panelButtons);
        dialog.add(mainBox);
        if(ext != null)setFilter(ext);
        initListeners();
        dialog.pack();
        dialog.setVisible(true);

}
    public void setFilter(String[] ext){
        tableChooseView.setFilter(ext);
        folderChooseView.setFilter(ext);
        listChooseView.setFilter(ext);
    } 
    public void setPath(File file){
        pathLabel.setText(file.getAbsolutePath());
    }


    public File getSelectedFile(){
       return chooseFile;
    }

    public void setFile(File file){
        chooseDir = file.getParentFile();
        fileNameField.setText(file.getName());
        dialog.repaint();
    }

    private void initListeners(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseDir = currentView.getCurrDir();
                chooseFile = new File(chooseDir.getAbsolutePath() + "\\" + fileNameField.getText());
                dialog.setVisible(false);
            }
        });
        upDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentView.upPath();currentView.updateUI();
            }
        });
        listViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentView = listChooseView;
                scrollPaneList.setVisible(true);
                tablePanel.setVisible(false);
                scrollPaneFolders.setVisible(false);
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableChooseView.setDirectory(home);
                folderChooseView.setDirectory(home);
                listChooseView.setDirectory(home);
            }
        });
        tableViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentView = tableChooseView;
                tablePanel.setVisible(true);
                scrollPaneList.setVisible(false);
                scrollPaneFolders.setVisible(false);
                dialog.setSize(new Dimension(tableChooseView.getWidth() * 2, tableChooseView.getHeight() + 170));
            }
        });
        folderViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentView = folderChooseView;
                scrollPaneFolders.setVisible(true);
                tablePanel.setVisible(false);
                scrollPaneList.setVisible(false);

            }
        });
    }
}
