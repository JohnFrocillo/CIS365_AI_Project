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
        adjacency_matrix[0][1] = 1;
        adjacency_matrix[0][16] = 1;
        adjacency_matrix[0][17] = 1;
        adjacency_matrix[1][0] = 1;
        adjacency_matrix[1][2] = 1;
        adjacency_matrix[1][16] = 1;
        adjacency_matrix[1][17] = 1;
        adjacency_matrix[1][18] = 1;
        adjacency_matrix[2][1] = 1;
        adjacency_matrix[2][3] = 1;
        adjacency_matrix[2][17] = 1;
        adjacency_matrix[2][18] = 1;
        adjacency_matrix[2][19] = 1;
        adjacency_matrix[3][2] = 1;
        adjacency_matrix[3][4] = 1;
        adjacency_matrix[3][18] = 1;
        adjacency_matrix[3][19] = 1;
        adjacency_matrix[3][20] = 1;
        adjacency_matrix[4][3] = 1;
        adjacency_matrix[4][5] = 1;
        adjacency_matrix[4][19] = 1;
        adjacency_matrix[4][20] = 1;
        adjacency_matrix[4][21] = 1;
        adjacency_matrix[5][4] = 1;
        adjacency_matrix[5][6] = 1;
        adjacency_matrix[5][20] = 1;
        adjacency_matrix[5][21] = 1;
        adjacency_matrix[5][22] = 1;
        adjacency_matrix[6][5] = 1;
        adjacency_matrix[6][7] = 1;
        adjacency_matrix[6][21] = 1;
        adjacency_matrix[6][22] = 1;
        adjacency_matrix[6][23] = 1;
        adjacency_matrix[7][6] = 1;
        adjacency_matrix[7][22] = 1;
        adjacency_matrix[7][23] = 1;
        adjacency_matrix[8][9] = 1;
        adjacency_matrix[8][24] = 1;
        adjacency_matrix[8][25] = 1;
        adjacency_matrix[9][8] = 1;
        adjacency_matrix[9][10] = 1;
        adjacency_matrix[9][24] = 1;
        adjacency_matrix[9][25] = 1;
        adjacency_matrix[9][26] = 1;
        adjacency_matrix[10][9] = 1;
        adjacency_matrix[10][11] = 1;
        adjacency_matrix[10][25] = 1;
        adjacency_matrix[10][26] = 1;
        adjacency_matrix[10][27] = 1;
        adjacency_matrix[11][10] = 1;
        adjacency_matrix[11][12] = 1;
        adjacency_matrix[11][26] = 1;
        adjacency_matrix[11][27] = 1;
        adjacency_matrix[11][28] = 1;
        adjacency_matrix[12][11] = 1;
        adjacency_matrix[12][13] = 1;
        adjacency_matrix[12][27] = 1;
        adjacency_matrix[12][28] = 1;
        adjacency_matrix[12][29] = 1;
        adjacency_matrix[13][12] = 1;
        adjacency_matrix[13][14] = 1;
        adjacency_matrix[13][28] = 1;
        adjacency_matrix[13][29] = 1;
        adjacency_matrix[13][30] = 1;
        adjacency_matrix[14][13] = 1;
        adjacency_matrix[14][15] = 1;
        adjacency_matrix[14][29] = 1;
        adjacency_matrix[14][30] = 1;
        adjacency_matrix[14][31] = 1;
        adjacency_matrix[15][14] = 1;
        adjacency_matrix[15][30] = 1;
        adjacency_matrix[15][31] = 1;
        adjacency_matrix[16][0] = 1;
        adjacency_matrix[16][1] = 1;
        adjacency_matrix[16][17] = 1;
        adjacency_matrix[16][32] = 1;
        adjacency_matrix[16][33] = 1;
        adjacency_matrix[17][16] = 1;
        adjacency_matrix[17][0] = 1;
        adjacency_matrix[17][1] = 1;
        adjacency_matrix[17][2] = 1;
        adjacency_matrix[17][18] = 1;
        adjacency_matrix[17][32] = 1;
        adjacency_matrix[17][33] = 1;
        adjacency_matrix[17][34] = 1;
        adjacency_matrix[18][17] = 1;
        adjacency_matrix[18][19] = 1;
        adjacency_matrix[18][1] = 1;
        adjacency_matrix[18][2] = 1;
        adjacency_matrix[18][3] = 1;
        adjacency_matrix[18][33] = 1;
        adjacency_matrix[18][34] = 1;
        adjacency_matrix[18][35] = 1;
        adjacency_matrix[19][18] = 1;
        adjacency_matrix[19][20] = 1;
        adjacency_matrix[19][2] = 1;
        adjacency_matrix[19][3] = 1;
        adjacency_matrix[19][4] = 1;
        adjacency_matrix[19][34] = 1;
        adjacency_matrix[19][35] = 1;
        adjacency_matrix[20][19] = 1;
        adjacency_matrix[20][21] = 1;
        adjacency_matrix[20][3] = 1;
        adjacency_matrix[20][4] = 1;
        adjacency_matrix[20][5] = 1;
        adjacency_matrix[20][35] = 1;
        adjacency_matrix[21][20] = 1;
        adjacency_matrix[21][4] = 1;
        adjacency_matrix[21][5] = 1;
        adjacency_matrix[21][6] = 1;
        adjacency_matrix[22][5] = 1;
        adjacency_matrix[22][6] = 1;
        adjacency_matrix[22][7] = 1;
        adjacency_matrix[22][23] = 1;
        adjacency_matrix[22][38] = 1;
        adjacency_matrix[22][39] = 1;
        adjacency_matrix[23][22] = 1;
        adjacency_matrix[23][6] = 1;
        adjacency_matrix[23][7] = 1;
        adjacency_matrix[23][38] = 1;
        adjacency_matrix[23][39] = 1;
        adjacency_matrix[24][8] = 1;
        adjacency_matrix[24][9] = 1;
        adjacency_matrix[24][25] = 1;
        adjacency_matrix[24][40] = 1;
        adjacency_matrix[24][41] = 1;
        adjacency_matrix[25][24] = 1;
        adjacency_matrix[25][8] = 1;
        adjacency_matrix[25][9] = 1;
        adjacency_matrix[25][40] = 1;
        adjacency_matrix[25][41] = 1;
        adjacency_matrix[25][10] = 1;
        adjacency_matrix[25][42] = 1;
        adjacency_matrix[26][9] = 1;
        adjacency_matrix[26][10] = 1;
        adjacency_matrix[26][11] = 1;
        adjacency_matrix[26][27] = 1;
        adjacency_matrix[27][10] = 1;
        adjacency_matrix[27][11] = 1;
        adjacency_matrix[27][12] = 1;
        adjacency_matrix[27][28] = 1;
        adjacency_matrix[27][44] = 1;
        adjacency_matrix[28][11] = 1;
        adjacency_matrix[28][12] = 1;
        adjacency_matrix[28][13] = 1;
        adjacency_matrix[28][29] = 1;
        adjacency_matrix[28][44] = 1;
        adjacency_matrix[28][45] = 1;
        adjacency_matrix[29][12] = 1;
        adjacency_matrix[29][13] = 1;
        adjacency_matrix[29][14] = 1;
        adjacency_matrix[29][30] = 1;
        adjacency_matrix[29][44] = 1;
        adjacency_matrix[29][45] = 1;
        adjacency_matrix[29][46] = 1;
        adjacency_matrix[30][13] = 1;
        adjacency_matrix[30][14] = 1;
        adjacency_matrix[30][15] = 1;
        adjacency_matrix[30][31] = 1;
        adjacency_matrix[30][45] = 1;
        adjacency_matrix[30][46] = 1;
        adjacency_matrix[30][47] = 1;
        adjacency_matrix[31][14] = 1;
        adjacency_matrix[31][15] = 1;
        adjacency_matrix[31][46] = 1;
        adjacency_matrix[31][47] = 1;
        adjacency_matrix[32][16] = 1;
        adjacency_matrix[32][17] = 1;
        adjacency_matrix[32][33] = 1;
        adjacency_matrix[32][48] = 1;
        adjacency_matrix[32][49] = 1;
        adjacency_matrix[33][16] = 1;
        adjacency_matrix[33][17] = 1;
        adjacency_matrix[33][18] = 1;
        adjacency_matrix[33][32] = 1;
        adjacency_matrix[33][34] = 1;
        adjacency_matrix[33][48] = 1;
        adjacency_matrix[33][49] = 1;
        adjacency_matrix[33][50] = 1;
        adjacency_matrix[34][17] = 1;
        adjacency_matrix[34][18] = 1;
        adjacency_matrix[34][19] = 1;
        adjacency_matrix[34][33] = 1;
        adjacency_matrix[34][35] = 1;
        adjacency_matrix[34][49] = 1;
        adjacency_matrix[34][50] = 1;
        adjacency_matrix[34][51] = 1;
        adjacency_matrix[35][18] = 1;
        adjacency_matrix[35][19] = 1;
        adjacency_matrix[35][20] = 1;
        adjacency_matrix[35][34] = 1;
        adjacency_matrix[35][50] = 1;
        adjacency_matrix[35][51] = 1;
        adjacency_matrix[36][37] = 1;
        adjacency_matrix[36][52] = 1;
        adjacency_matrix[36][53] = 1;
        adjacency_matrix[37][36] = 1;
        adjacency_matrix[37][38] = 1;
        adjacency_matrix[37][22] = 1;
        adjacency_matrix[37][52] = 1;
        adjacency_matrix[37][53] = 1;
        adjacency_matrix[37][54] = 1;
        adjacency_matrix[38][37] = 1;
        adjacency_matrix[38][39] = 1;
        adjacency_matrix[38][22] = 1;
        adjacency_matrix[38][23] = 1;
        adjacency_matrix[38][53] = 1;
        adjacency_matrix[38][54] = 1;
        adjacency_matrix[38][55] = 1;
        adjacency_matrix[39][22] = 1;
        adjacency_matrix[39][23] = 1;
        adjacency_matrix[39][38] = 1;
        adjacency_matrix[39][54] = 1;
        adjacency_matrix[39][55] = 1;
        adjacency_matrix[39][56] = 1;
        adjacency_matrix[40][24] = 1;
        adjacency_matrix[40][25] = 1;
        adjacency_matrix[40][41] = 1;
        adjacency_matrix[40][55] = 1;
        adjacency_matrix[40][56] = 1;
        adjacency_matrix[40][57] = 1;
        adjacency_matrix[41][40] = 1;
        adjacency_matrix[41][24] = 1;
        adjacency_matrix[41][25] = 1;
        adjacency_matrix[41][42] = 1;
        adjacency_matrix[41][56] = 1;
        adjacency_matrix[41][57] = 1;
        adjacency_matrix[41][58] = 1;
        adjacency_matrix[42][41] = 1;
        adjacency_matrix[42][25] = 1;
        adjacency_matrix[42][42] = 1;
        adjacency_matrix[42][57] = 1;
        adjacency_matrix[42][58] = 1;
        adjacency_matrix[42][59] = 1;
        adjacency_matrix[43][42] = 1;
        adjacency_matrix[43][58] = 1;
        adjacency_matrix[43][59] = 1;
        adjacency_matrix[44][27] = 1;
        adjacency_matrix[44][28] = 1;
        adjacency_matrix[44][29] = 1;
        adjacency_matrix[44][45] = 1;
        adjacency_matrix[44][60] = 1;
        adjacency_matrix[44][61] = 1;
        adjacency_matrix[45][28] = 1;
        adjacency_matrix[45][29] = 1;
        adjacency_matrix[45][30] = 1;
        adjacency_matrix[45][44] = 1;
        adjacency_matrix[45][46] = 1;
        adjacency_matrix[45][60] = 1;
        adjacency_matrix[45][61] = 1;
        adjacency_matrix[45][62] = 1;
        adjacency_matrix[46][29] = 1;
        adjacency_matrix[46][30] = 1;
        adjacency_matrix[46][31] = 1;
        adjacency_matrix[46][45] = 1;
        adjacency_matrix[46][47] = 1;
        adjacency_matrix[46][61] = 1;
        adjacency_matrix[46][62] = 1;
        adjacency_matrix[46][63] = 1;
        adjacency_matrix[47][30] = 1;
        adjacency_matrix[47][31] = 1;
        adjacency_matrix[47][46] = 1;
        adjacency_matrix[47][62] = 1;
        adjacency_matrix[47][63] = 1;
        adjacency_matrix[48][32] = 1;
        adjacency_matrix[48][33] = 1;
        adjacency_matrix[48][49] = 1;
        adjacency_matrix[48][64] = 1;
        adjacency_matrix[48][65] = 1;
        adjacency_matrix[49][32] = 1;
        adjacency_matrix[49][33] = 1;
        adjacency_matrix[49][34] = 1;
        adjacency_matrix[49][48] = 1;
        adjacency_matrix[49][50] = 1;
        adjacency_matrix[49][64] = 1;
        adjacency_matrix[49][65] = 1;
        adjacency_matrix[50][33] = 1;
        adjacency_matrix[50][34] = 1;
        adjacency_matrix[50][35] = 1;
        adjacency_matrix[50][49] = 1;
        adjacency_matrix[50][51] = 1;
        adjacency_matrix[50][65] = 1;
        adjacency_matrix[51][34] = 1;
        adjacency_matrix[51][35] = 1;
        adjacency_matrix[51][50] = 1;
        adjacency_matrix[52][36] = 1;
        adjacency_matrix[52][37] = 1;
        adjacency_matrix[52][53] = 1;
        adjacency_matrix[52][68] = 1;
        adjacency_matrix[52][69] = 1;
        adjacency_matrix[53][36] = 1;
        adjacency_matrix[53][37] = 1;
        adjacency_matrix[53][38] = 1;
        adjacency_matrix[53][52] = 1;
        adjacency_matrix[53][54] = 1;
        adjacency_matrix[53][68] = 1;
        adjacency_matrix[53][69] = 1;
        


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