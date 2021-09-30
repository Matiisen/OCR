package ocr;


public class MainClass {
    public static void main(String[] args) {


        OcrService ocr=null;
        if(args.length > 3)
            ocr = new OcrService(args[0], args[1], args[2], args[3]);
        else if(args.length == 3)
            ocr = new OcrService(args[0], args[1], args[2]);
        else
            System.exit(-1);

        ocr.run();

        System.exit(1);

    }

}
