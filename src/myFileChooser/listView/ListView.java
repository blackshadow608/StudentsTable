package myFileChooser.listView;

import myFileChooser.ChooseDialog;
import myFileChooser.chooseModel.ChooseView;
import myFileChooser.chooseModel.MyFileView;
import myFileChooser.listView.FileLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 04.06.15.
 */
public class ListView extends ChooseView {

    protected List<FileLabel> listDisk = new ArrayList<FileLabel>();
    private Box box = Box.createVerticalBox();

    public ListView(ChooseDialog dialog){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        this.dialog = dialog;
        add(box);
        createRoots();
    }
    public ListView(){}

    public void addListeners(MyFileView fileView){
        final FileLabel diskLabel = new FileLabel(fileView);
        diskLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File file = diskLabel.getFileView().getFile();
                if(file.isDirectory()){
                    fileCurrentDir = diskLabel.getFileView().getFile();
                    paintDirectory(diskLabel.getFileView());
                }else{
                    dialog.setFile(diskLabel.getFileView().getFile());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                diskLabel.getLabel().setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                diskLabel.getLabel().setBorder(null);
            }
        });
        listDisk.add(diskLabel);
    }

    public void setContent(){
        remove(box);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        box = Box.createVerticalBox();
        int height;
        height = getHeight();
        int width;
        width = getWidth();
        add(box);
        for(int currentLabel = 0; currentLabel < listDisk.size(); currentLabel++){
            box.add(listDisk.get(currentLabel));
            if(listDisk.get(currentLabel).getWidth() > width){
                width = listDisk.get(currentLabel).getWidth();
            }
        }
        height = listDisk.size() * 32 + 5;
        setPreferredSize(new Dimension(width, height));
        listDisk = new ArrayList<FileLabel>();
        updateUI();
    }
}
