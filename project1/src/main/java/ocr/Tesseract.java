package ocr;

import java.io.File;
import java.io.IOException;

public class Tesseract implements OcrInterface{
    @Override
    public boolean run(File sourceFile, String destinationDirPath, String language) {

        try {
            Process process = new ProcessBuilder("/bin/sh", "-c", "tesseract "+sourceFile.getAbsolutePath()+" "+destinationDirPath+"/"+sourceFile.getName() + "_Tesseract -l " + language).start();
            process.waitFor();
            return true;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }
}
