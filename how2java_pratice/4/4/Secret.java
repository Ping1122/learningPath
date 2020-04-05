package decipher;

import java.util.Random;

/**
 * Created by macbookpro on 2020/4/3.
 */
public class Secret {
    private String secret;
    private Random random = new Random();

    public Secret() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append((char) random.nextInt(256));
        }
        this.secret = sb.toString();
        System.out.println("Secret: created the secret " + this.secret);
    }

    public boolean guess(String answer) {
        return secret.equals(answer);
    }
}
