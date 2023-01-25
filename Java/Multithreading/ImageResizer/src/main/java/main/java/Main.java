package main.java;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "/Users/annadmitrieva/Dropbox/Мой Mac (MacBook Pro — Анна)/Desktop/src";
        String dstFolder = "/Users/annadmitrieva/Dropbox/Мой Mac (MacBook Pro — Анна)/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int quarter = files.length / 4;

        File[] files1 = new File[quarter];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImageResizer resizer1 = new ImageResizer(files1, dstFolder, newWidth, start);
        new Thread(resizer1).start();

        File[] files2 = new File[quarter];
        System.arraycopy(files, quarter, files2, 0, files2.length);
        ImageResizer resizer2 = new ImageResizer(files2, dstFolder, newWidth, start);
        new Thread(resizer2).start();

        File[] files3 = new File[quarter];
        System.arraycopy(files, quarter * 2, files3, 0, files3.length);
        ImageResizer resizer3 = new ImageResizer(files3, dstFolder, newWidth, start);
        new Thread(resizer3).start();

        File[] files4 = new File[files.length - quarter * 3];
        System.arraycopy(files, quarter * 3, files4, 0, files4.length);
        ImageResizer resizer4 = new ImageResizer(files4, dstFolder, newWidth, start);
        new Thread(resizer4).start();

    }
}
