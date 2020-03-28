package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        try {
            File f = new File("/Users/macbookpro/Documents/ping/xy/out.txt");
            f.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(f);
            byte[] data = {88, 89};
            fos.write(data);
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
