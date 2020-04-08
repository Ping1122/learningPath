package multiplethread;

import java.io.File;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by macbookpro on 2020/4/7.
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedTransferQueue<Runnable>());
        File folder = new File("/Users/macbookpro/Documents/ping/temp");
        File[] files = folder.listFiles();
        for (File f : files) {
            FileSearcher fs = new FileSearcher(f, "if");
            tpe.execute(fs);
        }
    }
}
