package mati;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract.*;

import java.io.*;
import java.io.FileWriter;
import java.util.Arrays;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;
import static org.junit.Assert.*;

public class MainClass {
    public static void main(String[] args) {



        //gocr(args[0], args[1]);
        tesseract2(args[0], args[1]);
        //tesseract(args[0], args[1]);


    }
    public static void tesseract(String dir, String dirResult){
        BytePointer outText = null;
        TessBaseAPI api = new TessBaseAPI();
        if (api.Init("","pol") != 0){
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }

        lept.PIX image = null;
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        double timeSum = 0;
        try {
            FileWriter fw2 = new FileWriter("time.csv", true);
            fw2.write(  "TesseractAPI:\n");
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(File file : listOfFiles) {
            if(file.isFile()){
                long start = System.nanoTime();


                image = pixRead(dir + "/" + file.getName());
                api.SetImage(image);
                //Get OCR result

                outText = api.GetUTF8Text();
                String string = outText.getString();
                assertEquals(false, string.isEmpty());
                System.out.println("OCR output:\n" + string);
                try {
                    FileWriter fw = new FileWriter(dirResult + "/tesseractAPI/" + file.getName() + ".txt");
                    fw.write(string);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                long finish = System.nanoTime();
                double timeElapsed = (finish - start)/1000000000.0;

                timeSum += timeElapsed;

                try {
                    FileWriter fw2 = new FileWriter("time.csv", true);
                    fw2.write( timeElapsed + "s," + file.getName()+  "\n");
                    fw2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }
        timeRaport(listOfFiles, timeSum);



        //Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);

    }
    public static void gocr(String dir, String dirResult){

        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        double timeSum = 0;
        try {
            FileWriter fw2 = new FileWriter("time.csv", true);
            fw2.write(  "GOCR:\n");
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(File file : listOfFiles) {
            if (file.isFile()) {

                long start = System.nanoTime();

                try {
                    Process process = new ProcessBuilder("/bin/sh", "-c", "gocr "+dir+"/"+file.getName()+ " >> "+dirResult+"/gocr/"+file.getName()+".txt").start();

                    process.waitFor();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                long finish = System.nanoTime();
                double timeElapsed = (finish - start)/1000000000.0;

                timeSum += timeElapsed;

                try {
                    FileWriter fw2 = new FileWriter("time.csv", true);
                    fw2.write( timeElapsed + "s," + file.getName()+  "\n");
                    fw2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }
        timeRaport(listOfFiles, timeSum);





    }

    public static void tesseract2(String dir, String dirResult){

        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        double timeSum = 0;
        try {
            FileWriter fw2 = new FileWriter("time.csv", true);
            fw2.write(  "Tesseract:\n");
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(File file : listOfFiles) {
            if(file.isFile()) {

                long start = System.nanoTime();

                try {
                    Process process = new ProcessBuilder("/bin/sh", "-c", "tesseract "+dir+"/"+file.getName()+ " "+dirResult+"/tesseract/"+file.getName() + " -l pol").start();

                    process.waitFor();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }


                long finish = System.nanoTime();
                double timeElapsed = (finish - start)/1000000000.0;

                timeSum += timeElapsed;

                try {
                    FileWriter fw2 = new FileWriter("time.csv", true);
                    fw2.write( timeElapsed + "s," + file.getName()+  "\n");
                    fw2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }

        timeRaport(listOfFiles, timeSum);

    }
    public static void timeRaport(File[] listOfFiles, double timeSum ){
        try {
            FileWriter fw2 = new FileWriter("time.csv", true);
            fw2.write("files: " + listOfFiles.length  + "full time: "+timeSum+", average time: " + timeSum/listOfFiles.length + "s \n\n");
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
