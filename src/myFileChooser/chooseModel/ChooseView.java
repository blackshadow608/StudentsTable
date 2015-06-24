package myFileChooser.chooseModel;

import myFileChooser.ChooseDialog;
import myFileChooser.PathName;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 21.06.15.
 */
public abstract class ChooseView extends JPanel{

    protected PathName root = new PathName();
    protected List<File> listFolders = root.getPathList() ;
    protected ChooseDialog dialog;
    protected File fileCurrentDir;
    protected MyFilter filter;

     public void paintDirectory(MyFileView fileView){
         dialog.setPath(fileCurrentDir);
        List<File> namesFile = fileView.getFileList();
        if(filter != null){
            for(int currName = 0; currName< namesFile.size(); currName++){
                if(filter.isFileAccept(namesFile.get(currName))){
                    MyFileView fileLabel = new MyFileView(namesFile.get(currName));
                addListeners(fileLabel);
                }
            }
        }else {
            for(int currName = 0; currName< namesFile.size(); currName++){
                MyFileView fileLabel = new MyFileView(namesFile.get(currName));
            addListeners(fileLabel);
            }
         }
         setContent();
    }

    public void setFilter(String[] ext){
        filter = new MyFilter(ext);
    }

    public File getCurrDir(){
        return fileCurrentDir;
    }

    public void createRoots(){
        newModel();
        for(int i =0 ; i < listFolders.size();i++){
            MyFileView diskLabel = new MyFileView(listFolders.get(i),true);
            addListeners(diskLabel);
        }
        setContent();
    }

    public void upPath(){
        if(fileCurrentDir!=null){
            File file = fileCurrentDir.getParentFile();
            fileCurrentDir = file;
            if(file != null){
                newModel();
                paintDirectory(new MyFileView(file));
            }else{
                createRoots();
            }
        }
    }
    public void setDirectory(File file){
        newModel();
        fileCurrentDir = file;
        paintDirectory(new MyFileView(file));
    }
    protected void newModel(){}
    public abstract void addListeners(MyFileView fileView);
    public abstract void setContent();
}
