package myFileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * Created by USER on 04.06.15.
 */
public class MyFileView extends JLabel{
    private File file;


    private boolean isSelected;


    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
