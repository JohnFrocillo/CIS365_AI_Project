import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private boolean isVisited;
    private List<Node> children;
    
    public Node (String name) {
        this.name = name;
        this.children = new ArrayList<Node>();
    }
    public String getName() {
        return name;
    }
    public boolean getVisited() {
        return isVisited;
    }
    public List<Node> getChildren() {
        return children;
    }
    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
    public void addChild (Node child) {
        this.children.add(child);
    }
    @Override
    public String toString() {
        return name;
    }

}
