import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class RecursiveClass extends RecursiveTask<String> {
    private SiteMap linkList;

    public RecursiveClass(SiteMap linkList) {
        this.linkList = linkList;
    }

    @Override
   protected String compute() {
        String map = linkList.getMainLink();
        StringBuilder builder = new StringBuilder();
        List<RecursiveClass> taskList = new ArrayList<>();
        for (SiteMap link : linkList.getNodeList()) {
            RecursiveClass task = new RecursiveClass(link);
            task.fork();
            taskList.add(task);
        }
        for (RecursiveClass task : taskList) {
            int depth = linkList.getDepth();
            String tabs = getIndent(depth);
            builder.append(System.lineSeparator()).append(tabs).append(task.join());
        }
        map += builder.toString();
        return map;
    }

    private String getIndent(int depth) {
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        return tabs;
    }
    
}
