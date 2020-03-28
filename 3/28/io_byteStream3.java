package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        try {
            String filename = "/Users/macbookpro/Documents/ping/temp";
            File d = new File(filename);
            String[] filenameArr = d.list();
            List<String> filenames = new ArrayList<String>();
            for (String fn : filenameArr)
                if (fn.split("-").length == 2) filenames.add(fn);
            Collections.sort(filenames, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int n1 = Integer.parseInt(o1.split("-")[1]);
                    int n2 = Integer.parseInt(o2.split("-")[1]);
                    return n1-n2;
                }
            });
            File outputFile = new File(filename+"/merged.py");
            FileOutputStream fos = new FileOutputStream(outputFile);
            for (int i = 0; i < filenames.size(); i++) {
                System.out.println(filenames.get(i));
                File inputFile = new File(d, filenames.get(i));
                FileInputStream fis = new FileInputStream(inputFile);
                byte[] all = new byte[(int)inputFile.length()];
                fis.read(all);
                fos.write(all);
                fis.close();
            }
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
