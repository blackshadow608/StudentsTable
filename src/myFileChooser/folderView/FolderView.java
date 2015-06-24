package myFileChooser.folderView;

import myFileChooser.ChooseDialog;
import myFileChooser.listView.FileLabel;
import myFileChooser.listView.ListView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by USER on 24.06.15.
 */
public class FolderView extends ListView {
    private GridLayout gridLayout;

    public FolderView(ChooseDialog dialog){
        this.dialog = dialog;
        gridLayout = new GridLayout(0,3);
        setLayout(gridLayout);
        setBackground(Color.WHITE);
        createRoots();
    }
    public void setContent(){

        this.removeAll();
        int height;
        height = getHeight();
        int width;
        width = getWidth();
        for(int currentLabel = 0; currentLabel < listDisk.size(); currentLabel++){
            add(listDisk.get(currentLabel));

        }
        height = ( (listDisk.size() / 3) + 1) * 32;
        setPreferredSize(new Dimension(32 * 10, height));
        listDisk = new ArrayList<FileLabel>();
        updateUI();
    }
}
