package decipher;

/**
 * Created by macbookpro on 2020/4/3.
 */
public class Main {

    public static void main(String[] args) {
        Secret secret = new Secret();
        Container container = new Container();
        Guesser guesser = new Guesser(secret, container);
        Logger logger = new Logger(container);
        Thread guesserThread = new Thread(guesser);
        Thread loggerThread = new Thread(logger);
        loggerThread.setDaemon(true);
        guesserThread.start();
        loggerThread.start();
    }
}
