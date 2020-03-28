package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        File f1 = new File("/Users/macbookpro/Documents/ping/academic");
        List<File> files = new ArrayList<>(Arrays.asList(f1.listFiles()));
        String maxFileName = "";
        long maxLength = -1;
        String minFileName = "";
        long minLength = Long.MAX_VALUE;

        for (int i = 0; i < files.size(); i++) {
            File f = files.get(i);
            if (f.isFile()) {
                if (f.length() > maxLength) {
                    maxFileName = f.getAbsolutePath();
                    maxLength = f.length();
                }
                if (f.length() < minLength) {
                    minFileName = f.getAbsolutePath();
                    minLength = f.length();
                }
            }
            if (f.isDirectory()) {
                files.addAll(Arrays.asList(f.listFiles()));
            }
        }

        System.out.println("The largest file is" + maxFileName + ", with size " + maxLength + " bytes.");
        System.out.println("The smallest file is" + minFileName + ", with size " + minLength + " bytes.");

    }
}
