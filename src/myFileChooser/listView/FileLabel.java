package myFileChooser.listView;

import myFileChooser.chooseModel.MyFileView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by USER on 19.06.15.
 */
public class FileLabel extends JPanel {
    public JLabel getLabel() {
          return label;
    }

    protected JLabel label;
    protected MyFileView fileView;

    public FileLabel(File file){
        setLayout(new BorderLayout());
        setSize(50, 32);
        Box box = Box.createVerticalBox();
        fileView = new MyFileView(file);
        String name;
        if(fileView.getFile().getName().equals("")){
            name = fileView.getFile().getAbsolutePath();
        }else{
            name = fileView.getFile().getName();
        }
        label = new JLabel(name,fileView.getIcon(),JLabel.LEFT);
        label.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        setBackground(Color.WHITE);
        box.add(label);
        add(box);
    }

    public FileLabel(MyFileView fileView){
        this.fileView = fileView;
        setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        String name;
        if(fileView.getFile().getName().equals("")){
            name = fileView.getFile().getAbsolutePath();
        }else{
            name = fileView.getFile().getName();
        }
        label = new JLabel(name,fileView.getIcon(),JLabel.LEFT);
        label.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        setBackground(Color.WHITE);
        box.add(label);
        add(box);
    }

    public MyFileView getFileView(){
        return fileView;
    }
}
