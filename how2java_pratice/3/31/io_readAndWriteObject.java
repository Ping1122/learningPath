import java.io.*;

import character.Hero;

public class Test {

    public static void main(String[] args) {
        File f =  new File("/Users/macbookpro/Documents/ping/temp/objectInputStreamOut.txt");
        try (
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            int heroNum = 10;
            Hero[] heros = new Hero[heroNum];
            for (int i = 0; i < heros.length; i++) {
                Hero h = new Hero();
                h.name = Integer.toString(i);
                h.hp = (float)Math.random() * 1000;
                heros[i] = h;
            }
            for (Hero h : heros) {
                oos.writeObject(h);
            }
            Hero[] copy = new Hero[heroNum];
            for (int i = 0; i < heroNum; i++) {
                Hero h = (Hero) ois.readObject();
                System.out.println("Hero " + h.name + " " + h.hp);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
