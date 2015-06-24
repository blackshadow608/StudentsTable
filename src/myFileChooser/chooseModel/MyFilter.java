package myFileChooser.chooseModel;

import java.io.File;

/**
 * Created by USER on 24.06.15.
 */
public class MyFilter {
    private String[] extensions;

    public MyFilter(String [] extensions){
        this.extensions = extensions;
    }

    public boolean isFileAccept(File file){
        if(!file.isDirectory()){
            String extension = "";

            int lastDot = file.getName().lastIndexOf('.');
            if (lastDot > 0) {
                extension = file.getName().substring(lastDot + 1);
            }
            for(int currExt = 0; currExt < extension.length(); currExt++){
                if(extensions[currExt].equals(extension)){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
