import java.io.*;

public class Test {

    public static void main(String[] args) {
        File f =  new File("/Users/macbookpro/Documents/ping/temp/dataInputStreamOut.txt");
        try (
                FileOutputStream fos = new FileOutputStream(f);
                DataOutputStream dos = new DataOutputStream(fos);
                FileInputStream fis = new FileInputStream(f);
                DataInputStream dis = new DataInputStream(fis);
        ) {
            dos.writeInt(31);
            dos.writeInt(13);
            System.out.println(dis.readInt());
            System.out.println(dis.readInt());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
