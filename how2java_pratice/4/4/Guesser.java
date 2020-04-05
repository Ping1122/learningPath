package decipher;

/**
 * Created by macbookpro on 2020/4/3.
 */
public class Guesser implements Runnable{

    private Secret secret;
    private Container container;

    public Guesser(Secret secret, Container container) {
        this.secret = secret;
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append((char)i);
                    sb.append((char)j);
                    sb.append((char)k);
                    if (secret.guess(sb.toString())) {
                        System.out.println("Guesser: Found the secret " + sb.toString());
                        break;
                    }
                    container.put(sb.toString());
                }
            }
        }
    }
}
