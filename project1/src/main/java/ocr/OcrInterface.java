package ocr;

import java.io.File;

public interface OcrInterface {
    public boolean run(File sourceFile , String destinationDirPath, String language);
}
