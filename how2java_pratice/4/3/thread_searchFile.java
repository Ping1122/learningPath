package multiplethread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by macbookpro on 2020/4/3.
 */
public class FileSearcher implements Runnable{

    private String target;
    private File f;

    public FileSearcher(File f, String target) {
        this.f = f;
        this.target = target;
    }

    public void run() {
        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
        ){
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                if (line.contains(target)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Thread.currentThread().getId());
                    sb.append(" ");
                    sb.append(f.getName() + " contains " + target);
                    System.out.println(sb.toString());
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(Thread.currentThread().getId());
            sb.append(" ");
            sb.append(f.getName() + " does not contain " + target);
            System.out.println(sb.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
