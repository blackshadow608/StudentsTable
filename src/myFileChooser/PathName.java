package myFileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 04.06.15.
 */
public class PathName {
    private File[] roots;
    private List<File> list;

    public PathName(){
        roots = File.listRoots();
        list = new ArrayList<File>();
        for(int currentItem = 0; currentItem < roots.length; currentItem++){
            list.add(roots[currentItem]);
        }
    }


    public List<File> getPathList(){
        return list;
    }
}
