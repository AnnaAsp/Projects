import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String link = "https://skillbox.ru/";
        SiteMap siteMap = new SiteMap(link);
        String map = new ForkJoinPool().invoke(new RecursiveClass(siteMap));

        File folder = new File("output");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            FileWriter file = new FileWriter("output/SiteMap.txt");
            file.write(map);
            file.flush();
            file.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
