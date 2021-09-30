package ocr;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;
import static org.junit.Assert.assertEquals;

public class TesseractAPI implements OcrInterface{

    @Override
    public boolean run(File sourceFile, String destinationDirPath, String language) {

        BytePointer outText = null;
        tesseract.TessBaseAPI api = new tesseract.TessBaseAPI();
        if (api.Init("/home/student/IdeaProjects/project1/src/main/resources/tessdata",language) != 0){
            System.err.println("Could not initialize tesseract.");
            return false;
        }

        lept.PIX image = pixRead(sourceFile.getAbsolutePath());
        api.SetImage(image);
                //Get OCR result

        outText = api.GetUTF8Text();
        String string = outText.getString();
        assertEquals(false, string.isEmpty());
        //System.out.println("OCR output:\n" + string);
        try {
            FileWriter fw = new FileWriter(destinationDirPath + "/" + sourceFile.getName() + "_TesseractAPI.txt");
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
        return true;
    }
}
