import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SiteMap {
    private volatile String mainLink;
    private volatile List<SiteMap> nodeList = new ArrayList<>();
    private static List<String> linkList = new ArrayList<>();
    private int depth = 0;

    public SiteMap(String mainLink) {
        this.mainLink = mainLink;
    }

    public String getMainLink() {
        return mainLink;
    }

    public int getDepth() {
        depth = mainLink.length() - mainLink.replaceAll("/", "").length() - 2;
        return depth;
    }

    public List<SiteMap> getNodeList() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Document doc = null;
        try {
            doc = Jsoup.connect(mainLink).get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Elements links = doc.select("a[href]");
        links.forEach(l -> {
            String link = l.absUrl("href");
            if (link.matches(mainLink + "[^#]+/") && !linkList.contains(link)) {
                linkList.add(link);
                SiteMap newList = new SiteMap(link);
                nodeList.add(newList);
            }
        });
        return nodeList;
    }

}
