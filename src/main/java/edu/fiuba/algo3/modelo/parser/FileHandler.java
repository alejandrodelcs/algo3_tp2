package edu.fiuba.algo3.modelo.parser;

import edu.fiuba.algo3.modelo.exceptions.FileDoesNotExist;
import edu.fiuba.algo3.modelo.exceptions.FileIsEmpty;
import edu.fiuba.algo3.modelo.exceptions.InvalidExtension;

import java.io.File;

public class FileHandler {
    String fileSource;
    public FileHandler(String file){
        this.fileSource = file;
        validJSONFile();
    }
    private void validJSONFile() {

        File file = new File(fileSource);
        String extension = "";
        if(!file.exists()) {
            throw new FileDoesNotExist();
        }
        if(file.length() == 0){
            throw new FileIsEmpty();
        }
        int i = fileSource.lastIndexOf('.');
        if (i > 0) {
            extension = fileSource.substring(i+1);
            if(!extension.equals("json")){
                throw new InvalidExtension();
            }
        }
    }
}
