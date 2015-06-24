package myFileChooser.treeView;

import myFileChooser.ChooseDialog;
import myFileChooser.PathName;
import myFileChooser.chooseModel.ChooseView;
import myFileChooser.chooseModel.MyFileView;
import myFileChooser.listView.FileLabel;

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
    private List<File> listFolders;
    private List<TreeFolder> listDisk = new ArrayList<TreeFolder>();
    private PathName root = new PathName();
    private ChooseDialog dialog;
    private Box box = new Box(BoxLayout.Y_AXIS);
    private List <ChooseView> listView = new ArrayList<ChooseView>();

    public TreePanel(ChooseDialog dialog, ChooseView view){
        listView.add(view);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        add(box);
        this.dialog = dialog;
        listFolders = root.getPathList();
        for(int i =0 ; i < listFolders.size();i++){
            MyFileView fileView = new MyFileView(listFolders.get(i),true);
            final TreeFolder folderView =  new TreeFolder(fileView);
            folderView.setAlignmentX(JComponent.LEFT_ALIGNMENT);
            addListeners(folderView);
            box.add(folderView);
        }
        listDisk = new ArrayList<TreeFolder>();
    }

    public void addChooseView(ChooseView view){
        listView.add(view);
    }

    private void updateViews(File file){
        for(int i = 0; i < listView.size(); i++){
            listView.get(i).setDirectory(file);
          //  listView.get(i).updateUI();
        }
    }

    public void updateContent(TreeFolder treeFolder){
        if(treeFolder.isSelected){
            treeFolder.removeAll();
            treeFolder.add(treeFolder.getLabel());
            treeFolder.isSelected = false;
        }else {
            treeFolder.isSelected = true;
            listFolders = treeFolder.getFileView().getFileList();
            updateViews(treeFolder.getFileView().getFile());
            for(int currentDir = 0; currentDir < listFolders.size(); currentDir++){
                final TreeFolder currentFolder = new TreeFolder(listFolders.get(currentDir));
                treeFolder.add(Box.createHorizontalStrut(10));
                currentFolder.setAlignmentX(JComponent.LEFT_ALIGNMENT);
                treeFolder.add(currentFolder);
                addListeners(currentFolder);
            }
        }
    }

    public void addListeners(final TreeFolder diskLabel){

        diskLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateContent(diskLabel);
                updateUI();
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

}
