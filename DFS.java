import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class DFS {

    static ArrayList<Node> nodesList = new ArrayList<>();
    public static String goal;
    // instantiate matrix
    private int adjacency_matrix[][] = new int[256][256];
    private boolean water_tiles[] = new boolean[256];

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
        HashMap<String, String> trace = new HashMap<>();
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
                    trace.put(n.getName(), element.getName());
                }
            }
        }

        String current = goal;
        while (current != null) {
            System.out.println(current);
            current = trace.get(current);
        }
    }

    /**
     * Astar pathfinding implementation
     * assisted by: https://github.com/psikoi/AStar-Pathfinding
     * @param adjacency_matrix adjacency matrix for the graph to path-find
     * @param start Node to begin path-finding on
     * @param considerWater water tiles have higher cost than normal
     * @return Back-traced path ArrayList from end to start
     */
    public ArrayList<Node> AStar(int adjacency_matrix[][], Node start, boolean considerWater) {
        if (start.getName() == goal) {
            return new ArrayList<>();
        }

        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();

        open.add(start);

        while (!open.isEmpty()) {
            Node cur = getLowestCost(open);

            if (cur.getName().equals(goal)) {
                return retrace(cur);
            }

            open.remove(cur);
            closed.add(cur);

            for (Node n : findNeighbours(adjacency_matrix, cur)) {
                if (closed.contains(n)) continue;

                int score = cur.getCost() + distance(cur, n);
                if (considerWater && isWater(n)) {
                    score += 5;
                }

                if (open.contains(n)) {
                    if (score < n.getCost()) {
                        n.setCost(score);
                        n.setParent(cur);
                    }
                } else {
                    n.setCost(score);
                    open.add(n);
                    n.setParent(cur);
                }
            }
        }

        return new ArrayList<>();
    }

    private int distance(Node a, Node b) {
        return (int)Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) +
                              (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    private ArrayList<Node> retrace(Node n) {
        Node temp = n;
        ArrayList<Node> trace = new ArrayList<>();
        trace.add(n);

        while (temp.getParent() != null) {
            trace.add(temp.getParent());
            temp = temp.getParent();
        }

        return trace;
    }

    private Node getLowestCost(ArrayList<Node> nodes) {
        Node min = nodes.get(0);
        for (Node n : nodes)
            if (n.getCost() < min.getCost())
                min = n;
        return min;
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

    /**
     * Checks if a node has water.
     * @param n node to check
     * @return true if node has water on it.
     */
    public boolean isWater(Node n) {
        return water_tiles[n.getY() * 16 + n.getX()];
    }

    /**
     * Checks if a node has water.
     * @param i tile number
     * @return true if node has water on it.
     */
    public boolean isWater(int i) {
        return water_tiles[i];
    }

    /**
     * Wrapper for a Line of Sight calculator
     * @param a Name of starting square on map
     * @param b Name of ending square on map
     * @see DFS#lineOfSight(Node, Node)
     * @return the distance to the end from the start.  -1 if there is no LoS.
     */
    public int lineOfSight(String a, String b) {
        return lineOfSight(new Node(a), new Node(b));
    }

    /**
     * A Line of Sight calculator using Bresenham's Line Generation Algorithm
     * Sources: https://www.geeksforgeeks.org/bresenhams-line-generation-algorithm/
     * https://csustan.csustan.edu/~tom/Lecture-Notes/Graphics/Bresenham-Line/Bresenham-Line.pdf
     * @param a starting node
     * @param b ending node
     * @return the distance to the end from the start.  -1 if there is no LoS.
     */
    public int lineOfSight(Node a, Node b) {
        Node cur = a;
        int distance = 0;

        int dx, dy;
        int incX, incY;

        dx = b.getX() - a.getX();
        dy = b.getY() - a.getY();

        if (dy < 0) {
            dy = -dy;
            incY = -1;
        } else {
            incY = 1;
        }

        if (dx < 0) {
            dx = -dx;
            incX = -1;
        } else {
            incX = 1;
        }

        dx *= 2;
        dy *= 2;

        if (dx > dy) {
            int error = dy - (dx / 2);
            int x = a.getX(), y = a.getY();
            while (x != b.getX()) {
                x += incX;
                if (error >= 0) {
                    y += incY;
                    error -= dx;
                }
                error += dy;
                distance++;

                // Check if next tile in line is a neighbor of the current node.
                boolean         found     = false;
                ArrayList<Node> neighbors = findNeighbours(adjacency_matrix, cur);

                for (Node n : neighbors)
                {
                    String name = "" + (char) (x + 65) + (y + 1);
                    System.out.println(name);
                    if (n.getName().equals(name))
                    {
                        found = true;
                        cur = n;
                        break;
                    }
                }
                if (!found) return -1;
            }
        } else {
            int error = dx - (dy / 2);
            int x = a.getX(), y = a.getY();
            while (y != b.getY()) {
                if (error >= 0) {
                    x += incX;
                    error -= dy;
                }
                y += incY;
                error += dx;
                distance++;

                // Check if next tile in line is a neighbor of the current node.
                boolean         found     = false;
                ArrayList<Node> neighbors = findNeighbours(adjacency_matrix, cur);

                for (Node n : neighbors)
                {
                    String name = "" + (char) (x + 65) + (y + 1);
                    System.out.println(name);
                    if (n.getName().equals(name))
                    {
                        found = true;
                        cur = n;
                        break;
                    }
                }
                if (!found) return -1;
            }
        }

        return distance;
    }

    public DFS() {
        nodesList = new ArrayList<>();

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
                //System.out.println(temp.getName());
            }
        }

        // Add in the spots that should be '1' in adjacency matrix
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
        //015 should connect to P16
        adjacency_matrix[238][255] = 1;

        // manually add walls

        // Top half of board
        adjacency_matrix[7][8] = 0;
        adjacency_matrix[8][7] = 0;
        adjacency_matrix[7][24] = 0;
        adjacency_matrix[19][36] = 0;
        adjacency_matrix[24][7] = 0;
        adjacency_matrix[23][24] = 0;
        adjacency_matrix[24][23] = 0;
        adjacency_matrix[23][40] = 0;
        adjacency_matrix[40][23] = 0;
        adjacency_matrix[39][40] = 0;
        adjacency_matrix[40][39] = 0;
        adjacency_matrix[23][8] = 0;
        adjacency_matrix[8][23] = 0;
        adjacency_matrix[39][24] = 0;
        adjacency_matrix[24][39] = 0;

        adjacency_matrix[20][36] = 0;
        adjacency_matrix[20][37] = 0;
        adjacency_matrix[21][36] = 0;
        adjacency_matrix[21][37] = 0;
        adjacency_matrix[21][22] = 0;
        adjacency_matrix[21][38] = 0;
        adjacency_matrix[25][26] = 0;
        adjacency_matrix[26][25] = 0;
        adjacency_matrix[26][41] = 0;
        adjacency_matrix[26][42] = 0;
        adjacency_matrix[26][43] = 0;
        adjacency_matrix[27][42] = 0;
        adjacency_matrix[27][43] = 0;
        adjacency_matrix[28][43] = 0;

        adjacency_matrix[35][36] = 0;
        adjacency_matrix[35][52] = 0;
        adjacency_matrix[36][19] = 0;
        adjacency_matrix[36][20] = 0;
        adjacency_matrix[36][21] = 0;
        adjacency_matrix[36][35] = 0;
        adjacency_matrix[36][51] = 0;
        adjacency_matrix[37][20] = 0;
        adjacency_matrix[37][21] = 0;
        adjacency_matrix[41][26] = 0;
        adjacency_matrix[42][26] = 0;
        adjacency_matrix[42][27] = 0;
        adjacency_matrix[43][26] = 0;
        adjacency_matrix[43][27] = 0;
        adjacency_matrix[43][28] = 0;
        adjacency_matrix[43][44] = 0;
        adjacency_matrix[43][60] = 0;
        adjacency_matrix[44][43] = 0;
        adjacency_matrix[44][59] = 0;
        adjacency_matrix[49][66] = 0;

        adjacency_matrix[50][66] = 0;
        adjacency_matrix[50][67] = 0;
        adjacency_matrix[51][66] = 0;
        adjacency_matrix[51][67] = 0;
        adjacency_matrix[51][68] = 0;
        adjacency_matrix[51][52] = 0;
        adjacency_matrix[51][36] = 0;
        adjacency_matrix[52][51] = 0;
        adjacency_matrix[52][35] = 0;
        adjacency_matrix[52][67] = 0;
        adjacency_matrix[53][70] = 0;
        adjacency_matrix[54][70] = 0;
        adjacency_matrix[59][44] = 0;
        adjacency_matrix[59][60] = 0;
        adjacency_matrix[59][74] = 0;
        adjacency_matrix[59][75] = 0;
        adjacency_matrix[59][76] = 0;
        adjacency_matrix[59][44] = 0;
        adjacency_matrix[60][43] = 0;
        adjacency_matrix[60][59] = 0;
        adjacency_matrix[60][75] = 0;
        adjacency_matrix[60][76] = 0;
        adjacency_matrix[60][77] = 0;
        adjacency_matrix[61][76] = 0;
        adjacency_matrix[61][77] = 0;
        adjacency_matrix[62][77] = 0;

        adjacency_matrix[65][66] = 0;
        adjacency_matrix[65][82] = 0;
        adjacency_matrix[66][49] = 0;
        adjacency_matrix[66][50] = 0;
        adjacency_matrix[66][51] = 0;
        adjacency_matrix[66][65] = 0;
        adjacency_matrix[66][81] = 0;
        adjacency_matrix[67][50] = 0;
        adjacency_matrix[67][51] = 0;
        adjacency_matrix[67][52] = 0;
        adjacency_matrix[67][68] = 0;
        adjacency_matrix[68][51] = 0;
        adjacency_matrix[68][67] = 0;
        adjacency_matrix[69][70] = 0;
        adjacency_matrix[69][86] = 0;
        adjacency_matrix[70][53] = 0;
        adjacency_matrix[70][54] = 0;
        adjacency_matrix[70][69] = 0;
        adjacency_matrix[70][85] = 0;
        adjacency_matrix[74][58] = 0;
        adjacency_matrix[74][59] = 0;
        adjacency_matrix[75][58] = 0;
        adjacency_matrix[75][59] = 0;
        adjacency_matrix[75][60] = 0;
        adjacency_matrix[76][59] = 0;
        adjacency_matrix[76][60] = 0;
        adjacency_matrix[76][61] = 0;
        adjacency_matrix[77][60] = 0;
        adjacency_matrix[77][61] = 0;
        adjacency_matrix[77][62] = 0;
        adjacency_matrix[77][78] = 0;
        adjacency_matrix[77][94] = 0;
        adjacency_matrix[78][77] = 0;
        adjacency_matrix[78][93] = 0;
        adjacency_matrix[81][66] = 0;
        adjacency_matrix[81][82] = 0;
        adjacency_matrix[81][98] = 0;
        adjacency_matrix[82][65] = 0;
        adjacency_matrix[82][81] = 0;
        adjacency_matrix[82][97] = 0;
        adjacency_matrix[85][70] = 0;
        adjacency_matrix[85][86] = 0;
        adjacency_matrix[85][102] = 0;
        adjacency_matrix[86][69] = 0;
        adjacency_matrix[86][85] = 0;
        adjacency_matrix[86][101] = 0;
        adjacency_matrix[87][103] = 0;
        adjacency_matrix[87][104] = 0;
        adjacency_matrix[88][103] = 0;
        adjacency_matrix[88][104] = 0;
        adjacency_matrix[88][105] = 0;
        adjacency_matrix[89][104] = 0;
        adjacency_matrix[89][105] = 0;
        adjacency_matrix[89][106] = 0;
        adjacency_matrix[90][105] = 0;
        adjacency_matrix[90][106] = 0;
        adjacency_matrix[90][107] = 0;
        adjacency_matrix[91][106] = 0;
        adjacency_matrix[91][107] = 0;
        adjacency_matrix[93][78] = 0;
        adjacency_matrix[93][94] = 0;
        adjacency_matrix[94][77] = 0;
        adjacency_matrix[94][93] = 0;
        adjacency_matrix[94][109] = 0;
        adjacency_matrix[94][110] = 0;

        adjacency_matrix[97][82] = 0;
        adjacency_matrix[97][98] = 0;
        adjacency_matrix[97][114] = 0;
        adjacency_matrix[98][81] = 0;
        adjacency_matrix[98][97] = 0;
        adjacency_matrix[98][113] = 0;
        adjacency_matrix[98][114] = 0;
        adjacency_matrix[98][115] = 0;
        adjacency_matrix[99][114] = 0;
        adjacency_matrix[99][115] = 0;
        adjacency_matrix[101][86] = 0;
        adjacency_matrix[101][102] = 0;
        adjacency_matrix[101][118] = 0;
        adjacency_matrix[102][85] = 0;
        adjacency_matrix[102][101] = 0;
        adjacency_matrix[102][117] = 0;
        adjacency_matrix[102][119] = 0;
        adjacency_matrix[103][87] = 0;
        adjacency_matrix[103][88] = 0;
        adjacency_matrix[103][119] = 0;
        adjacency_matrix[103][120] = 0;
        adjacency_matrix[104][87] = 0;
        adjacency_matrix[104][88] = 0;
        adjacency_matrix[104][89] = 0;
        adjacency_matrix[104][119] = 0;
        adjacency_matrix[104][120] = 0;
        adjacency_matrix[105][88] = 0;
        adjacency_matrix[105][89] = 0;
        adjacency_matrix[105][90] = 0;
        adjacency_matrix[105][106] = 0;
        adjacency_matrix[105][120] = 0;
        adjacency_matrix[106][89] = 0;
        adjacency_matrix[106][90] = 0;
        adjacency_matrix[106][91] = 0;
        adjacency_matrix[106][105] = 0;
        adjacency_matrix[106][121] = 0;
        adjacency_matrix[107][90] = 0;
        adjacency_matrix[107][91] = 0;
        adjacency_matrix[109][94] = 0;
        adjacency_matrix[110][94] = 0;

        adjacency_matrix[113][98] = 0;
        adjacency_matrix[113][114] = 0;
        adjacency_matrix[114][97] = 0;
        adjacency_matrix[114][98] = 0;
        adjacency_matrix[114][99] = 0;
        adjacency_matrix[114][113] = 0;
        adjacency_matrix[115][98] = 0;
        adjacency_matrix[115][99] = 0;
        adjacency_matrix[117][102] = 0;
        adjacency_matrix[117][118] = 0;
        adjacency_matrix[118][101] = 0;
        adjacency_matrix[118][117] = 0;
        adjacency_matrix[118][119] = 0;
        adjacency_matrix[118][135] = 0;
        adjacency_matrix[121][106] = 0;
        adjacency_matrix[121][120] = 0;
        adjacency_matrix[121][122] = 0;
        adjacency_matrix[121][136] = 0;
        adjacency_matrix[121][138] = 0;
        adjacency_matrix[122][105] = 0;
        adjacency_matrix[122][121] = 0;
        adjacency_matrix[122][137] = 0;
        adjacency_matrix[124][140] = 0;
        adjacency_matrix[124][141] = 0;
        adjacency_matrix[125][140] = 0;
        adjacency_matrix[125][141] = 0;
        adjacency_matrix[125][142] = 0;
        adjacency_matrix[126][141] = 0;
        adjacency_matrix[126][142] = 0;
        adjacency_matrix[126][143] = 0;
        adjacency_matrix[127][142] = 0;
        adjacency_matrix[127][143] = 0;

        // Bottom half of board
        adjacency_matrix[128][144] = 0;
        adjacency_matrix[128][145] = 0;
        adjacency_matrix[129][144] = 0;
        adjacency_matrix[129][145] = 0;
        adjacency_matrix[129][146] = 0;
        adjacency_matrix[130][145] = 0;
        adjacency_matrix[130][146] = 0;
        adjacency_matrix[130][147] = 0;
        adjacency_matrix[131][146] = 0;
        adjacency_matrix[131][147] = 0;
        adjacency_matrix[134][119] = 0;
        adjacency_matrix[134][135] = 0;
        adjacency_matrix[137][120] = 0;
        adjacency_matrix[137][122] = 0;
        adjacency_matrix[137][136] = 0;
        adjacency_matrix[137][138] = 0;
        adjacency_matrix[138][121] = 0;
        adjacency_matrix[138][137] = 0;
        adjacency_matrix[140][124] = 0;
        adjacency_matrix[140][125] = 0;
        adjacency_matrix[141][124] = 0;
        adjacency_matrix[141][125] = 0;
        adjacency_matrix[141][126] = 0;
        adjacency_matrix[142][125] = 0;
        adjacency_matrix[142][126] = 0;
        adjacency_matrix[142][127] = 0;
        adjacency_matrix[143][126] = 0;
        adjacency_matrix[143][127] = 0;

        adjacency_matrix[144][128] = 0;
        adjacency_matrix[144][129] = 0;
        adjacency_matrix[145][128] = 0;
        adjacency_matrix[145][129] = 0;
        adjacency_matrix[145][130] =0;
        adjacency_matrix[145][146] = 0;
        adjacency_matrix[145][162] = 0;
        adjacency_matrix[146][129] = 0;
        adjacency_matrix[146][130] = 0;
        adjacency_matrix[146][131] = 0;
        adjacency_matrix[146][145] = 0;
        adjacency_matrix[146][161] = 0;
        adjacency_matrix[147][130] = 0;
        adjacency_matrix[147][131] = 0;
        adjacency_matrix[149][150] = 0;
        adjacency_matrix[149][166] = 0;
        adjacency_matrix[150][149] = 0;
        adjacency_matrix[150][165] = 0;
        adjacency_matrix[150][166] = 0;
        adjacency_matrix[150][167] = 0;
        adjacency_matrix[150][135] = 0;
        adjacency_matrix[151][166] = 0;
        adjacency_matrix[151][167] = 0;
        adjacency_matrix[151][135] = 0;
        adjacency_matrix[151][136] = 0;
        adjacency_matrix[152][135] = 0;
        adjacency_matrix[152][136] = 0;
        adjacency_matrix[153][136] = 0;
        adjacency_matrix[153][169] = 0;
        adjacency_matrix[153][170] = 0;
        adjacency_matrix[154][169] = 0;
        adjacency_matrix[154][170] = 0;
        adjacency_matrix[154][171] = 0;
        adjacency_matrix[155][170] = 0;
        adjacency_matrix[155][171] = 0;
        adjacency_matrix[157][174] = 0;
        adjacency_matrix[158][174] = 0;

        adjacency_matrix[161][146] = 0;
        adjacency_matrix[161][162] = 0;
        adjacency_matrix[161][178] = 0;
        adjacency_matrix[162][145] = 0;
        adjacency_matrix[162][161] = 0;
        adjacency_matrix[162][177] = 0;
        adjacency_matrix[165][150] = 0;
        adjacency_matrix[165][166] = 0;
        adjacency_matrix[166][149] = 0;
        adjacency_matrix[166][150] = 0;
        adjacency_matrix[166][151] = 0;
        adjacency_matrix[166][165] = 0;
        adjacency_matrix[167][150] = 0;
        adjacency_matrix[167][151] = 0;
        adjacency_matrix[169][153] = 0;
        adjacency_matrix[169][154] = 0;
        adjacency_matrix[170][153] = 0;
        adjacency_matrix[170][154] = 0;
        adjacency_matrix[170][155] = 0;
        adjacency_matrix[171][154] = 0;
        adjacency_matrix[171][155] = 0;
        adjacency_matrix[173][174] = 0;
        adjacency_matrix[173][190] = 0;
        adjacency_matrix[174][157] = 0;
        adjacency_matrix[174][158] = 0;
        adjacency_matrix[174][173] = 0;
        adjacency_matrix[174][189] = 0;

        adjacency_matrix[177][162] = 0;
        adjacency_matrix[177][178] = 0;
        adjacency_matrix[178][161] = 0;
        adjacency_matrix[178][177] = 0;
        adjacency_matrix[178][193] = 0;
        adjacency_matrix[178][194] = 0;
        adjacency_matrix[179][180] = 0;
        adjacency_matrix[179][196] = 0;
        adjacency_matrix[180][179] = 0;
        adjacency_matrix[180][195] = 0;
        adjacency_matrix[186][202] = 0;
        adjacency_matrix[186][203] = 0;
        adjacency_matrix[187][202] = 0;
        adjacency_matrix[187][203] = 0;
        adjacency_matrix[187][204] = 0;
        adjacency_matrix[188][203] = 0;
        adjacency_matrix[188][204] = 0;
        adjacency_matrix[188][205] = 0;
        adjacency_matrix[189][174] = 0;
        adjacency_matrix[189][190] = 0;
        adjacency_matrix[189][204] = 0;
        adjacency_matrix[189][205] = 0;
        adjacency_matrix[189][206] = 0;
        adjacency_matrix[190][173] = 0;
        adjacency_matrix[190][189] = 0;

        adjacency_matrix[193][178] = 0;
        adjacency_matrix[194][178] = 0;
        adjacency_matrix[195][180] = 0;
        adjacency_matrix[195][196] = 0;
        adjacency_matrix[195][212] = 0;
        adjacency_matrix[196][179] = 0;
        adjacency_matrix[196][195] = 0;
        adjacency_matrix[196][211] = 0;
        adjacency_matrix[197][198] = 0;
        adjacency_matrix[197][214] = 0;
        adjacency_matrix[198][197] = 0;
        adjacency_matrix[198][213] = 0;
        adjacency_matrix[199][200] = 0;
        adjacency_matrix[199][216] = 0;
        adjacency_matrix[200][199] = 0;
        adjacency_matrix[200][215] = 0;
        adjacency_matrix[202][186] = 0;
        adjacency_matrix[202][187] = 0;
        adjacency_matrix[202][203] = 0;
        adjacency_matrix[203][186] = 0;
        adjacency_matrix[203][187] = 0;
        adjacency_matrix[203][188] = 0;
        adjacency_matrix[203][202] = 0;
        adjacency_matrix[204][187] = 0;
        adjacency_matrix[204][188] = 0;
        adjacency_matrix[204][189] = 0;
        adjacency_matrix[205][188] = 0;
        adjacency_matrix[205][189] = 0;
        adjacency_matrix[206][189] = 0;

        adjacency_matrix[211][196] = 0;
        adjacency_matrix[211][212] = 0;
        adjacency_matrix[211][228] = 0;
        adjacency_matrix[212][195] = 0;
        adjacency_matrix[212][211] = 0;
        adjacency_matrix[212][227] = 0;
        adjacency_matrix[212][228] = 0;
        adjacency_matrix[212][229] = 0;
        adjacency_matrix[213][198] = 0;
        adjacency_matrix[213][214] = 0;
        adjacency_matrix[213][228] = 0;
        adjacency_matrix[213][229] = 0;
        adjacency_matrix[213][230] = 0;
        adjacency_matrix[214][197] = 0;
        adjacency_matrix[214][213] = 0;
        adjacency_matrix[215][200] = 0;
        adjacency_matrix[215][216] = 0;
        adjacency_matrix[215][232] = 0;
        adjacency_matrix[216][199] = 0;
        adjacency_matrix[216][215] = 0;
        adjacency_matrix[216][231] = 0;
        adjacency_matrix[216][232] = 0;

        adjacency_matrix[227][212] = 0;
        adjacency_matrix[227][228] = 0;
        adjacency_matrix[228][211] = 0;
        adjacency_matrix[228][212] = 0;
        adjacency_matrix[228][213] = 0;
        adjacency_matrix[228][227] = 0;
        adjacency_matrix[229][212] = 0;
        adjacency_matrix[229][213] = 0;
        adjacency_matrix[230][213] = 0;
        adjacency_matrix[231][216] = 0;
        adjacency_matrix[231][232] = 0;
        adjacency_matrix[231][248] = 0;
        adjacency_matrix[232][215] = 0;
        adjacency_matrix[232][216] = 0;
        adjacency_matrix[232][231] = 0;
        adjacency_matrix[232][247] = 0;
        adjacency_matrix[234][235] = 0;
        adjacency_matrix[234][251] = 0;
        adjacency_matrix[235][234] = 0;
        adjacency_matrix[235][250] = 0;

        adjacency_matrix[247][232] = 0;
        adjacency_matrix[247][248] = 0;
        adjacency_matrix[248][231] = 0;
        adjacency_matrix[248][247] = 0;
        adjacency_matrix[250][235] = 0;
        adjacency_matrix[250][251] = 0;
        adjacency_matrix[251][234] = 0;
        adjacency_matrix[251][250] = 0;

        // Water (all tiles default to false)
        water_tiles[4] = true;
        water_tiles[5] = true;
        water_tiles[6] = true;
        water_tiles[9] = true;
        water_tiles[10] = true;
        water_tiles[11] = true;

        water_tiles[22] = true;
        water_tiles[25] = true;

        water_tiles[38] = true;
        water_tiles[39] = true;
        water_tiles[40] = true;
        water_tiles[41] = true;

        water_tiles[55] = true;
        water_tiles[56] = true;

        water_tiles[71] = true;
        water_tiles[72] = true;

        water_tiles[87] = true;

        water_tiles[102] = true;
        water_tiles[103] = true;
        water_tiles[104] = true;

        water_tiles[116] = true;
        water_tiles[118] = true;
        water_tiles[121] = true;

        water_tiles[132] = true;
        water_tiles[133] = true;
        water_tiles[134] = true;
        water_tiles[137] = true;

        water_tiles[147] = true;
        water_tiles[148] = true;
        water_tiles[151] = true;
        water_tiles[152] = true;
        water_tiles[153] = true;

        water_tiles[163] = true;
        water_tiles[168] = true;

        water_tiles[168] = true;
        water_tiles[168] = true;

        water_tiles[179] = true;
        water_tiles[185] = true;

        water_tiles[201] = true;

        water_tiles[217] = true;
        water_tiles[218] = true;
        water_tiles[219] = true;

        water_tiles[235] = true;

        //printMatrix(adjacency_matrix);

        // Get starting point from the user
        // Scanner scan = new Scanner(System.in);
        // System.out.println("What is the starting point? ");
        // String start = scan.next();

        // // Get the goal from the user
        // System.out.println("What is the destination? ");
        // String end = scan.next();
        // scan.close();

        // System.out.println("You have selected to start at " + start + " and end at " + end + ".");

        // goal = end;
        // DFS dfs = new DFS();

        
    }

    ArrayList<Node> route;
    
    public void aStar(String start, String end, boolean water) {
        goal = end;
        // Find the node specified by the user to start the Algorithm at
        nodesList.forEach((node) -> {
            if (node.getName().equals(start)) {
                //System.out.println(node.getName() + " will be the start node.\n\nHere is the result:");

                /*ArrayList<Node> list = dfs.findNeighbours(adjacency_matrix, node);
                for (Node n : list)
                    System.out.print(n.getName() + " ");*/

                //dfs.dfsUsingStack(adjacency_matrix, node);
                route = this.AStar(this.adjacency_matrix, node, water);
                // Reverse to get traverse path
                Collections.reverse(route);

                for (Node n : route) {
                    System.out.print(n.getName() + " ");
                }
            }
        });
    }
    
}