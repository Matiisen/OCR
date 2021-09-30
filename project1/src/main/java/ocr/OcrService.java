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

    public OcrService(String implementation, String lang, String sourceDir, String... destinationDir){

        this.sourceDir = sourceDir;
        this.lang = lang;

        if(destinationDir.length == 0)
            this.destinationDir = this.sourceDir;
        else
            this.destinationDir = destinationDir[0];

        this.resultName = this.destinationDir+"/_summary_"+ocrName+".csv";
        switch(implementation){
            case "GOCR":
            case "Gocr":
            case "gocr":{
                ocr = new Gocr();
                ocrName = "GOCR";
                break;
            }
            case "Tesseract":
            case "tesseract":{
                ocr = new Tesseract();
                ocrName = "Tesseract";
                break;
            }
            default:
            case "TesseractAPI":
            case "tesseractAPI": {
                ocr = new TesseractAPI();
                ocrName = "TesseractAPI";
                break;
            }
        }
    }

    public void run(){

        File folder = new File(sourceDir);
        File[] listOfFiles = folder.listFiles();

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

