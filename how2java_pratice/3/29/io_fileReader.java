import java.io.*;

public class Test {

    public static void main(String[] args) {
        File fin =  new File("/Users/macbookpro/Documents/ping/temp/activityController.py");
        File fout = new File("/Users/macbookpro/Documents/ping/temp/activityControllerLocked.py");
        try (FileReader fr = new FileReader(fin)) {
            char[] all = new char[(int)fin.length()];
            fr.read(all);
            for (int i = 0; i < all.length; i++) {
                char c = all[i];
                if (Character.isDigit(c) || Character.isLetter(c)) {
                    if (c == '9') c = '0';
                    else if (c == 'z') c = 'a';
                    else if (c == 'Z') c = 'A';
                    else c += 1;
                }
                all[i] = c;
            }
            try (FileWriter fw = new FileWriter(fout)) {
                fw.write(all);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
