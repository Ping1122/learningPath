import java.io.*;

public class Test {

    public static void main(String[] args) {
        File fin =  new File("/Users/macbookpro/Documents/ping/temp/Category.java");
        File fout = new File("/Users/macbookpro/Documents/ping/temp/CategoryRemovedAnnotation.java");
        try (
                FileReader fr = new FileReader(fin);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(fout);
                PrintWriter pw = new PrintWriter(fw);
        ) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                if (line.trim().startsWith("@")) continue;
                pw.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
