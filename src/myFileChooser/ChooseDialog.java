package myFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by USER on 04.06.15.
 */
public class ChooseDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JButton upDirButton;
    private JButton listViewButton = new JButton(new ImageIcon("listIcon.png"));
    private JButton folderViewButton = new JButton(new ImageIcon("tileIcon.png"));
    private JButton tableViewButton = new JButton(new ImageIcon("tableIcon.png"));
    private PathName directory;
    private JTextField fileNameField;
    private FolderPanel disk;
    private JDialog dialog;
    private JPanel panel;
    private JLabel pathLabel = new JLabel("");
    private File inputFile;
    private Box pathBox = Box.createHorizontalBox();
    private String pathSelectedFile;
    private String fileName;

    public ChooseDialog(){
        dialog = new JDialog();
        dialog.setModal(true);
        directory = new PathName();
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        Box mainBox = new Box(BoxLayout.Y_AXIS);
        mainBox.add(new TreePanel(directory));
        Box buttonBox = Box.createVerticalBox();
        upDirButton = new JButton(new ImageIcon("folder_up.png"));
        upDirButton.setPreferredSize(new Dimension(32,32));
        listViewButton.setPreferredSize(new Dimension(32,32));
        folderViewButton.setPreferredSize(new Dimension(32,32));
        tableViewButton.setPreferredSize(new Dimension(32,32));
        JPanel pathPanel = new JPanel();
        mainBox.add(pathPanel);
        pathPanel.add(pathBox);
        pathBox.add(listViewButton);
        pathBox.add(Box.createHorizontalStrut(5));
        pathBox.add(tableViewButton);
        pathBox.add(Box.createHorizontalStrut(5));
        pathBox.add(folderViewButton);
        pathBox.add(Box.createHorizontalStrut(5));
        pathBox.add(upDirButton);
        pathBox.add(Box.createHorizontalStrut(10));
        pathPanel.add(pathLabel);

        panel = new JPanel();

        disk = new FolderPanel(directory,panel,this);
        mainBox.add(new JScrollPane(panel));
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

        initListeners();
        dialog.pack();
        dialog.setVisible(true);
}


    public File getSelectedFile(){
       return inputFile;
    }

    public void setPathLabel(String path){
        pathLabel.setText(path);
        dialog.repaint();
    }

    public void setFileName(String pathName,String fileN){
        pathSelectedFile = pathName;
        fileNameField.setText(fileN);
        dialog.repaint();
    }

    private void initListeners(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pathSelectedFile = disk.getPath();
                inputFile = new File(pathSelectedFile + fileNameField.getText());
                getSelectedFile();
                dialog.setVisible(false);

            }
        });
        upDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pathLabel.getText().equals("")){
                    String path = disk.getPath();
                    File file = new File(path);
                    path = file.getParent();
                    disk.updatePath(path+"\\");
                }
            }
        });
    }
}
