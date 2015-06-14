package myFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 04.06.15.
 */
public class FolderPanel{
    private PathName root;
    private String absolutePath = new String("");
    private String path;
    private List<File> listFolders;
    private List<JLabel> listDisk = new ArrayList<JLabel>();
    private Icon diskIcon = new ImageIcon("driveIcon.png");
    private Icon folderIcon = new ImageIcon("folderIcon.jpg");
    private Icon fileIcon = new ImageIcon("fileIcon.png");
    private Box box= Box.createVerticalBox();
    private ChooseDialog dialog;

    private JPanel panel;

    public FolderPanel(PathName root, JPanel panel, ChooseDialog dialog){
        this.root = root;
        listFolders = root.getPathList();
        this.panel = panel;
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        this.dialog = dialog;
        this.panel.add(box);
        createRoots();

    }

    public void createRoots(){

        for(int i =0 ; i < listFolders.size();i++){
            final JLabel diskLabel = new JLabel(listFolders.get(i).getPath(),diskIcon,JLabel.LEFT);
            addListenrs(diskLabel);

            box.add(diskLabel);
        }
        setContent();
        absolutePath = "";
        listDisk = new ArrayList<JLabel>();
        dialog.setPathLabel(absolutePath);
        panel.repaint();
    }

    public String[]  getInnerFiles(File file){
        return file.list();
    }
    public void addListenrs(final JLabel diskLabel){

        diskLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File file = new File(absolutePath + diskLabel.getText()+"\\");
                if(file.isDirectory()){
                    absolutePath=absolutePath + diskLabel.getText()+"\\";
                    paintDirectory();
                }else{
                    dialog.setFileName(absolutePath,diskLabel.getText());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // diskLabel.setText("Pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //  diskLabel.setText("Clicked");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // diskLabel.setText("Entered");
                diskLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //diskLabel.setText("Exited");
                diskLabel.setBorder(null);
            }
        });
        listDisk.add(diskLabel);
    }

    public void paintDirectory(){
        dialog.setPathLabel(absolutePath);
        File file = new File(absolutePath);
        if(file.isDirectory()){
            String[] namesFile = file.list();
            for(int i = 0; i< namesFile.length; i++){
                File currentFile = new File(absolutePath + namesFile[i]);
                String path = namesFile[i];
                if(currentFile.isDirectory()){
                    final JLabel dirLabel = new JLabel(path,folderIcon,JLabel.LEFT);
                    addListenrs(dirLabel);
                }else{
                    final JLabel fileLabel = new JLabel(path,fileIcon,JLabel.LEFT);
                    addListenrs(fileLabel);
                }
            }
            setContent();
        }
        panel.repaint();
    }
    public void setContent(){

        panel.remove(box);

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        box = Box.createVerticalBox();
        int height;
        height = panel.getHeight();
        int width;
        width = panel.getWidth();
        panel.add(box);
        for(int currentLabel = 0; currentLabel < listDisk.size(); currentLabel++){
            box.add(listDisk.get(currentLabel));
            if(listDisk.get(currentLabel).getWidth() > width){
                width = listDisk.get(currentLabel).getWidth();
            }
        }
        if(height < listDisk.size() * 32 + 5){
            height = listDisk.size() * 32 + 5;
        }
        panel.setPreferredSize(new Dimension(width,height));
        listDisk = new ArrayList<JLabel>();
    }
    public void updatePath(String path){
        absolutePath = path;
        if(path.equals("null\\")){
            createRoots();
        }else {
            paintDirectory();
        }
    }

    public String getPath(){
        return absolutePath;
    }
}
