
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private boolean             isVisited;
    private List<tutoring.Node> children;

    // This is for A*
    private int           cost;
    private tutoring.Node parent = null;

    public Node (String name) {
        this.name = name;
        this.children = new ArrayList<tutoring.Node>();
        this.cost = 0;
    }
    public tutoring.Node getParent()
    {
        return parent;
    }

    public void setParent(tutoring.Node parent)
    {
        this.parent = parent;
    }
    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public boolean getVisited() {
        return isVisited;
    }
    public List<tutoring.Node> getChildren() {
        return children;
    }
    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
    public void addChild (tutoring.Node child) {
        this.children.add(child);
    }

    public int getX() {
        return (int)name.charAt(0) - 65;
    }

    public int getY() {
        return 16 * (Integer.parseInt(name.substring(1)) - 1);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.getName().equals(((tutoring.Node) obj).getName());
    }
}
