package myFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 15.06.15.
 */
public class TreePanel extends JPanel {
    private Icon diskIcon = new ImageIcon("driveIcon.png");
    private Icon folderIcon = new ImageIcon("folderIcon.jpg");
    private Icon fileIcon = new ImageIcon("fileIcon.png");
    private List<File> listFolders;
    private List<MyFileView> listDisk = new ArrayList<MyFileView>();
    private PathName root;

    public TreePanel(PathName root){
        this.root = root;
        listFolders = root.getPathList();
        for(int i =0 ; i < listFolders.size();i++){
            final MyFileView diskLabel = (MyFileView) new JLabel(listFolders.get(i).getPath(),diskIcon,JLabel.LEFT);
            diskLabel.setFile(listFolders.get(i));
            addListenrs(diskLabel);
        }
        listDisk = new ArrayList<MyFileView>();
    }

    public void addListenrs(final MyFileView diskLabel){

        diskLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!diskLabel.isSelected()){
                    File file = diskLabel.getFile();
                    diskLabel.setSelected(true);
                    if(file.isDirectory()){
                        String[] namesFile = file.list();
                        for(int i = 0; i< namesFile.length; i++){
                            File currentFile = new File(file.getAbsolutePath() + namesFile[i]);
                            String path = namesFile[i];
                            if(currentFile.isDirectory()){
                                final MyFileView dirLabel =(MyFileView) new JLabel(path,folderIcon,JLabel.LEFT);
                                dirLabel.setFile(currentFile);
                                add(dirLabel);
                                addListenrs(dirLabel);
                            }
                        }
                    }
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
       // listDisk.add(diskLabel);
    }

}
