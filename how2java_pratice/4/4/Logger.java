package decipher;

/**
 * Created by macbookpro on 2020/4/3.
 */
public class Logger implements Runnable {

    private Container container;

    public Logger(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            String str = container.get();
            while (str == null) {
                System.out.println("Logger: run out of answer wait 1 second");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                str = container.get();
            }
            //System.out.println("Logger: tried string " + str);
        }
    }
}
