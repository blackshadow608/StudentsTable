package myFileChooser.chooseModel;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 04.06.15.
 */
public class MyFileView{
    private File file;
    private Icon atrIcon;
    private Icon diskIcon = new ImageIcon("driveIcon.png");
    private Icon folderIcon = new ImageIcon("folderIcon.jpg");
    private Icon fileIcon = new ImageIcon("fileIcon.png");
    private boolean isSelected;


    public MyFileView(File file){
        this.file = file;
        if(file.isDirectory()){
            atrIcon = folderIcon;
        }else{
            atrIcon = fileIcon;
        }
    }

    public MyFileView(File file, boolean roots){
        this.file = file;
        if(roots){
            atrIcon = diskIcon;
        }
    }

    public List<File> getFileList(){
        String[] namesFile = file.list();
        List<File> listFiles = new ArrayList<File>();
        File view;
        for(int i = 0; i< namesFile.length; i++){
            String path = namesFile[i];
            view = new File(this.getFile().getAbsolutePath() + "\\" + path);
            listFiles.add(view);
        }
        return listFiles;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public Icon getIcon(){
        return atrIcon;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
