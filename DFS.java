import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
 
public class DFS { 
 
    static ArrayList<Node> nodesList = new ArrayList<>();
    private static String goal;

    // find neighbors of node using adjacency matrix
    // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
    public ArrayList<Node> findNeighbours(int adjacency_matrix[][], Node x) {
        int nodeIndex = -1;

        ArrayList<Node> neighbours = new ArrayList<>();
        for (int i = 0; i < nodesList.size(); i++) {
            if (nodesList.get(i).equals(x)) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
                if (adjacency_matrix[nodeIndex][j] == 1) {
                    neighbours.add(nodesList.get(j));
                }
            }
        }
        return neighbours;
    }

    // Recursive DFS
    public void dfs(int adjacency_matrix[][], Node node) {

        System.out.print(node.getName() + " ");
        ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, node);
        node.setVisited(true);
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.getVisited()) {
                dfs(adjacency_matrix, n);
            }
        }
    }

    // Iterative DFS using stack
    public void dfsUsingStack(int adjacency_matrix[][], Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);

        while (!stack.isEmpty()) {
            Node element = stack.pop();
            if (!element.getVisited()) {
                System.out.print(element.getName() + " ");
                element.setVisited(true);
                if (element.getName().equals(goal)) {
                    System.out.println(" Goal Reached!");
                    return;
                }
            }

            ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, element);
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.getVisited()) {
                    stack.add(n);
                }
            }
        }
    }

    private static void printMatrix(int matrix[][]) {
        System.out.print("   ");
        for (int i = 0; i < 256; i++)
        {
            System.out.print(String.format("%3d ", i));
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(String.format("%-3d", i));
            for (int j : matrix[i]) {
                System.out.print(String.format("%3d ", j));
            }
            System.out.println();
        }
    }

    public static void main(String arg[]) {

        // instantiate matrix
        int adjacency_matrix[][] = new int[256][256];

        for (int i = 0; i <= 255; i++) {
            for (int j = 0; j <= 255; j++) {
                adjacency_matrix[i][j] = 0;
            }
        }

        // start with an all '0' adjacency matrix
        for (int i = 1; i <= 16; i++) {
            for (int j = 'A'; j <= 'P'; j++) {
                String nodeName = String.format("%C%d", j, i);
                Node temp = new Node(nodeName);
                nodesList.add(temp);
                System.out.println(temp.getName());
            }
        }

        // FIXME: manually add in the spots that should be '1' in adjacency matrix
        // The stupid complicated for loop parameters are just keeping the index in bounds on the edges
        for (int i = 0; i < adjacency_matrix.length; i++) {
            if (i > 15)
                for (int up = (i - 1) % 16 == 15 ? i - 16 : i - 17;
                     up < (i - 14 > 255 ? 255 : i - 14);
                     up++)
                    adjacency_matrix[i][up] = 1;


            for (int mid = (i + 15) % 16 == 15 ? i : i - 1;
                 mid < (i + 2 > 255 ? 255 : i + 2);
                 mid++)
                adjacency_matrix[i][mid] = 1;

            if (i < 240)
                for (int bot = (i + 15) % 16 == 15 ? i + 16 : i + 15;
                     bot < (i + 18 > 255 ? 255 : i + 18);
                     bot++)
                    adjacency_matrix[i][bot] = 1;
        }

        // Display the matrix for testing.
        printMatrix(adjacency_matrix);

        // Get starting point from the user
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the starting point? ");
        String start = scan.next();

        // Get the goal from the user
        System.out.println("What is the destination? ");
        String end = scan.next();
        scan.close();

        System.out.println("You have selected to start at " + start + " and end at " + end + ".");

        goal = end;
        DFS dfs = new DFS();

        // Find the node specified by the user to start the Algorithm at
        nodesList.forEach((node) -> {
            if (node.getName().equals(start)) {
                System.out.println(node.getName() + " will be the start node.\n\nHere is the result:");

                dfs.dfsUsingStack(adjacency_matrix, node);
            }   
        });

		// int adjacency_matrix[][] = {
		// 		{0,1,1,0,0,0,0},  // Node 1: 40
		// 		{0,0,0,1,0,0,0},  // Node 2 :10
		// 		{0,1,0,1,1,1,0},  // Node 3: 20
		// 		{0,0,0,0,1,0,0},  // Node 4: 30
		// 		{0,0,0,0,0,0,1},  // Node 5: 60
		// 		{0,0,0,0,0,0,1},  // Node 6: 50
		// 		{0,0,0,0,0,0,0},  // Node 7: 70
		// };
 
		// DFS dfs = new DFS();

		// System.out.println("The DFS traversal of the graph using stack ");
		// dfs.dfsUsingStack(adjacency_matrix, A1);
 
		// System.out.println();
 
		// clearVisitedFlags();
 
		// System.out.println("The DFS traversal of the graph using recursion ");
		// dfs.dfs(adjacency_matrix, A1);
 
        
	}
 
	public static void clearVisitedFlags()
	{
		for (int i = 0; i < nodesList.size(); i++) {
			nodesList.get(i).setVisited(false);
		}
	}
}