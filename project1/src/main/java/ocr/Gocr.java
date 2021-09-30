package ocr;

import java.io.File;
import java.io.IOException;

public class Gocr implements OcrInterface{
    @Override
    public boolean run(File sourceFile, String destinationDirPath, String language) {
        try {
            Process process = new ProcessBuilder("/bin/sh", "-c", "gocr " + sourceFile.getAbsolutePath() + " >> " + destinationDirPath + "/" + sourceFile.getName() + "_Gocr.txt").start();
            process.waitFor();
            return true;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }

}
