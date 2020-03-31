import java.io.*;

public class Test {

    public static void main(String[] args) {
        File f =  new File("/Users/macbookpro/Documents/ping/temp/dataInputStreamOut.txt");
        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);
        ) {
            pw.println("31@13");
            pw.flush();
            String line = br.readLine();
            String[] numbers = line.split("@");
            System.out.println(Integer.parseInt(numbers[0]));
            System.out.println(Integer.parseInt(numbers[1]));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
