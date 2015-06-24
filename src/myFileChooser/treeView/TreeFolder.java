package myFileChooser.treeView;

import myFileChooser.chooseModel.MyFileView;
import myFileChooser.listView.FileLabel;

import javax.swing.*;
import java.io.File;

/**
 * Created by USER on 21.06.15.
 */
public class TreeFolder extends Box {
    public boolean isSelected = false;
    private FileLabel fileLabel;

    public TreeFolder(File file){
        super(BoxLayout.Y_AXIS);
        if(file.isDirectory()){
            fileLabel = new FileLabel(file);
            add(fileLabel);
        }
    };
    public TreeFolder(MyFileView fileView){
        super(BoxLayout.Y_AXIS);
        fileLabel = new FileLabel(fileView);
        add(fileLabel);
    };
    public MyFileView getFileView(){
        return fileLabel.getFileView();
    }
    public FileLabel getLabel(){
        return fileLabel;
    }
}
