package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            String filename = "/Users/macbookpro/Documents/ping/temp/merged.py";
            File inputFile = new File(filename);
            fis = new FileInputStream(inputFile);
            byte[] all = new byte[(int)inputFile.length()];
            fis.read(all);
            for (int i = 0; i < inputFile.length()/100+1; i++) {
                File outputFile = new File(filename+ "-" +i);
                fos = new FileOutputStream(outputFile);
                fos.write(all, i*100, (int)((inputFile.length()-i*100 > 100)? 100:inputFile.length()-i*100));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
