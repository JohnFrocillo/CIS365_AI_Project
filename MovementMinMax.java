import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovementMinMax {

    String strResult = "";
    int enemyCaptainAmericaValue;
    int enemyIronManValue;
    int enemyThorValue;
    int result;

    public MovementMinMax() {
        // Node value derived from:
        // value = (highest possible defense) - (enemy current defense) + (click number)
        // For example:
        // value = 18 - 16 + 4
        //       = 6
        // we want the maximized number for this. That means they have a low defense and/or a high click number
    }

    public MovementMinMax(int enemyCaptainAmericaDefense, int enemyCaptainAmericaClickNumber,
                            int enemyIronManDefense, int enemyIronManClickNumber,
                            int enemyThorDefense, int enemyThorClickNumber) {
        enemyCaptainAmericaValue = 18 - enemyCaptainAmericaDefense + enemyCaptainAmericaClickNumber;
        enemyIronManValue = 18 - enemyIronManDefense + enemyIronManClickNumber;
        enemyThorValue = 18 - enemyThorDefense + enemyThorClickNumber;
        
        MovementNode A = new MovementNode();
        MovementNode B = new MovementNode();
        MovementNode C = new MovementNode();
        MovementNode D = new MovementNode(enemyThorValue);
        MovementNode E = new MovementNode(enemyIronManValue);
        MovementNode F = new MovementNode(enemyCaptainAmericaValue);

        A.addChild(new ArrayList<MovementNode>(Arrays.asList(B,C)));
        B.addChild(new ArrayList<MovementNode>(Arrays.asList(D,E)));
        C.addChild(F);

        MinMax m = new MinMax();
        result = m.minMax(A, 2, true);
        System.out.println("The optimal output is: " + result);

        if (result == enemyCaptainAmericaValue) {
            strResult = "Captain America";
        }
        else if (result == enemyIronManValue) {
            strResult = "Iron Man";
        }
        else if (result == enemyThorValue) {
            strResult = "Thor";
        }
        else {
            strResult = "Captain America";
        }
    }

}

class MinMax {
    public int minMax (MovementNode position, int depth, boolean maximizingPlayer) {
        if (depth == 0) {
            return position.getValue();
        }
        if (maximizingPlayer) {
            int maxEval = -999;
            for (MovementNode x : position.getChildren()) {
                int eval = minMax(x, depth-1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        }
        else {
            int minEval = 999;
            for (MovementNode x : position.getChildren()) {
                int eval = minMax(x, depth-1, true);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }
}

class MovementNode {
    private int value;
    private List<MovementNode> children;
    public MovementNode() {
        this.children = new ArrayList<MovementNode>();
    }
    public MovementNode (int value) {
        this.value = value;
        this.children = new ArrayList<MovementNode>();
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public List<MovementNode> getChildren() {
        return children;
    }
    public void addChild (MovementNode child) {
        this.children.add(child);
    }
    public void addChild (List<MovementNode> children) {
        this.children.addAll(children);
    }
    @Override
    public String toString() {
        return ("" + value);
    }
}
