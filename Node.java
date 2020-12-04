import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private boolean isVisited;
    private List<Node> children;

    // This is for A*
    private int cost;
    private Node parent = null;

    /**
     * Creates a new node from its name
     * @param name name of new node
     */
    public Node (String name) {
        this.name = name;
        this.children = new ArrayList<Node>();
        this.cost = 0;
    }

    /**
     * This will only return the parent of a node after A* is run on it.
     * @return parent of node in route.
     */
    public Node getParent()
    {
        return parent;
    }

    /**
     * Sets the parent of a node in a route for A*
     * @param parent parent node
     */
    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    /**
     * The cost to travers a node in A* (For A* only)
     * @return the cost to traverse
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * Sets the traversal cost of the node
     * @param cost cost of traversal
     */
    public void setCost(int cost)
    {
        this.cost = cost;
    }

    /**
     * @return Name of the node.
     */
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

    /**
     * @return The node's integer x coordinate on the map.
     */
    public int getX() {
        return (int)name.charAt(0) - 65;
    }

    /**
     * @return the node's integer y coordinate on the map.
     */
    public int getY() {
        return (Integer.parseInt(name.substring(1)) - 1);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.getName().equals(((Node) obj).getName());
    }
}
