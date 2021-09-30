package ocr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OcrService {

    private OcrInterface ocr;
    private String sourceDir;
    private String lang;
    private String destinationDir;
    private String ocrName;
    private String resultName;

    public OcrService(String implementation, String lang, String sourceDir, String destinationDir) {

        this.sourceDir = sourceDir;
        this.lang = lang;

        if (destinationDir == null)
            this.destinationDir = this.sourceDir;
        else
            this.destinationDir = destinationDir;

        if ("gocr".equalsIgnoreCase(implementation)) {
            ocr = new Gocr();
            ocrName = "GOCR";
        }
        else if("tesseract".equalsIgnoreCase(implementation)) {
            ocr = new Tesseract();
            ocrName = "Tesseract";
        }
        else if("tesseractAPI".equalsIgnoreCase(implementation)) {
            ocr = new TesseractAPI();
            ocrName = "TesseractAPI";
        }
        else
            System.out.println("Unknown ocr service! Check first parametr");
    }

    public void run(){

        File folder = new File(sourceDir);
        File[] listOfFiles = folder.listFiles();
        this.resultName = this.destinationDir+"/_summary_"+ocrName+".csv";

        double timeSum = 0, timeSumValid=0;
        try {
            FileWriter fw2 = new FileWriter(resultName, true);
            fw2.write(  ocrName+":\n");
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numberOfValid = 0, numberOfFailes=0;

        for(File file : listOfFiles) {
            if (file.isFile()) {

                if(file.getName().endsWith(".txt"))
                    continue;

                long start = System.nanoTime();

                boolean result = ocr.run(file, destinationDir, lang);

                long finish = System.nanoTime();
                double timeElapsed = (finish - start)/1000000000.0;

                if(result) {
                    System.out.println("Processing " + file.getName() + " using " + ocrName + " finished in: " + timeElapsed + "s.");
                    timeSumValid += timeElapsed;
                    numberOfValid++;
                }else {
                    System.out.println("Processing " + file.getName() + " using " + ocrName + " failed.");
                    numberOfFailes++;
                }
                    timeSum += timeElapsed;


                try {
                    FileWriter fw2 = new FileWriter(resultName, true);
                    fw2.write( timeElapsed + "s," + file.getName()+  "\n");
                    fw2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }

        System.out.println("Time sum: "+timeSum+", number of valid: "+numberOfValid+", number of failes: "+numberOfFailes+",  average valid time: " + timeSumValid/numberOfValid + "s \n\n");
        timeRaport(numberOfValid, timeSumValid);


    }

    private void timeRaport(int numberOfFiles, double timeSumValid ){


        try {
            FileWriter fw2 = new FileWriter(resultName, true);
            fw2.write("files: " + numberOfFiles  + " full time: "+timeSumValid+", average time: " + timeSumValid/numberOfFiles + "s \n\n");
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

