import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AI {
    // Create the game state to track everything
    GameState gs = new GameState();
    // The buttons used to call the AI
    JButton captainAmericaButton = new JButton("What should Captain America Do?");
    JButton ironManButton = new JButton("What should Iron Man do?");
    JButton thorButton = new JButton("What should Thor do?");

    // Labels for the columns
    JLabel captainAmericaLabel = new JLabel("<HTML><U>Captain America</U></HTML>");
    JLabel ironManLabel = new JLabel("<HTML><U>Iron Man</U></HTML>");
    JLabel thorLabel = new JLabel("<HTML><U>Thor</U></HTML>");

    // Labels for the text fields
    JLabel friendlyCaptainAmericaLocationLabel = new JLabel("Enter Friendly Captain America Location:");
    JLabel friendlyCaptainAmericaClickNumberLabel = new JLabel("Enter Friendly Captain America Click #:");
    JLabel friendlyCaptainAmericaActionTokensLabel = new JLabel("Enter Friendly Captain America # Action Tokens:");
    JLabel enemyCaptainAmericaLocationLabel = new JLabel("Enter Enemy Captain America Location:");
    JLabel enemyCaptainAmericaClickNumberLabel = new JLabel("Enter Enemy Captain America Click #:");
    JLabel enemyCaptainAmericaActionTokensLabel = new JLabel("Enter Enemy Captain America # Action Tokens:");

    JLabel friendlyIronManLocationLabel = new JLabel("Enter Friendly Iron Man Location:");
    JLabel friendlyIronManClickNumberLabel = new JLabel("Enter Friendly Iron Man Click #:");
    JLabel friendlyIronManActionTokensLabel = new JLabel("Enter Friendly Iron Man # Action Tokens:");
    JLabel enemyIronManLocationLabel = new JLabel("Enter Enemy Iron Man Location:");
    JLabel enemyIronManClickNumberLabel = new JLabel("Enter Enemy Iron Man Click #:");
    JLabel enemyIronManActionTokensLabel = new JLabel("Enter Enemy Iron Man # Action Tokens:");

    JLabel friendlyThorLocationLabel = new JLabel("Enter Friendly Thor Location:");
    JLabel friendlyThorClickNumberLabel = new JLabel("Enter Friendly Thor Click #:");
    JLabel friendlyThorActionTokensLabel = new JLabel("Enter Friendly Thor # Action Tokens:");
    JLabel enemyThorLocationLabel = new JLabel("Enter Enemy Thor Location:");
    JLabel enemyThorClickNumberLabel = new JLabel("Enter Enemy Thor Click #:");
    JLabel enemyThorActionTokensLabel = new JLabel("Enter Enemy Thor # Action Tokens:");
    
    // The fields that need to be filled out for the AI to make a decision
    JTextField friendlyCaptainAmericaLocation = new JTextField("--------");
    JTextField friendlyCaptainAmericaClickNumber = new JTextField("--------");
    JTextField friendlyCaptainAmericaActionTokens = new JTextField("--------");
    JTextField enemyCaptainAmericaLocation = new JTextField("--------");
    JTextField enemyCaptainAmericaClickNumber = new JTextField("--------");
    JTextField enemyCaptainAmericaActionTokens = new JTextField("--------");

    JTextField friendlyIronManLocation = new JTextField("--------");
    JTextField friendlyIronManClickNumber = new JTextField("--------");
    JTextField friendlyIronManActionTokens = new JTextField("--------");
    JTextField enemyIronManLocation = new JTextField("--------");
    JTextField enemyIronManClickNumber = new JTextField("--------");
    JTextField enemyIronManActionTokens = new JTextField("--------");

    JTextField friendlyThorLocation = new JTextField("--------");
    JTextField friendlyThorClickNumber = new JTextField("--------");
    JTextField friendlyThorActionTokens = new  JTextField("--------");
    JTextField enemyThorLocation = new JTextField("--------");
    JTextField enemyThorClickNumber = new JTextField("--------");
    JTextField enemyThorActionTokens = new JTextField("--------");

    public AI() {
        // create the frame for the GUI
        JFrame frame = new JFrame("Heroclix AI");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the panel to store the GUI items
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // create constraints for the panel
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 2, 4, 2);

        // Add the labels to the panel
        c.gridx = 0;
        c.gridy = 0;
        panel.add(captainAmericaLabel, c);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(ironManLabel, c);
        c.gridx = 4;
        c.gridy = 0;
        panel.add(thorLabel, c);

        // Add the fields to the panel
        c.gridx = 0;
        c.gridy = 1;
        panel.add(enemyCaptainAmericaLocationLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(enemyCaptainAmericaLocation, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(enemyCaptainAmericaClickNumberLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(enemyCaptainAmericaClickNumber, c);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(enemyCaptainAmericaActionTokensLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(enemyCaptainAmericaActionTokens, c);
        c.gridx = 0;
        c.gridy = 4;
        panel.add(friendlyCaptainAmericaLocationLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(friendlyCaptainAmericaLocation, c);
        c.gridx = 0;
        c.gridy = 5;
        panel.add(friendlyCaptainAmericaClickNumberLabel, c);
        c.gridx = 1;
        c.gridy = 5;
        panel.add(friendlyCaptainAmericaClickNumber, c);
        c.gridx = 0;
        c.gridy = 6;
        panel.add(friendlyCaptainAmericaActionTokensLabel, c);
        c.gridx = 1;
        c.gridy = 6;
        panel.add(friendlyCaptainAmericaActionTokens, c);

        c.gridx = 2;
        c.gridy = 1;
        panel.add(enemyIronManLocationLabel, c);
        c.gridx = 3;
        c.gridy = 1;
        panel.add(enemyIronManLocation, c);
        c.gridx = 2;
        c.gridy = 2;
        panel.add(enemyIronManClickNumberLabel, c);
        c.gridx = 3;
        c.gridy = 2;
        panel.add(enemyIronManClickNumber, c);
        c.gridx = 2;
        c.gridy = 3;
        panel.add(enemyIronManActionTokensLabel, c);
        c.gridx = 3;
        c.gridy = 3;
        panel.add(enemyIronManActionTokens, c);
        c.gridx = 2;
        c.gridy = 4;
        panel.add(friendlyIronManLocationLabel, c);
        c.gridx = 3;
        c.gridy = 4;
        panel.add(friendlyIronManLocation, c);
        c.gridx = 2;
        c.gridy = 5;
        panel.add(friendlyIronManClickNumberLabel, c);
        c.gridx = 3;
        c.gridy = 5;
        panel.add(friendlyIronManClickNumber, c);
        c.gridx = 2;
        c.gridy = 6;
        panel.add(friendlyIronManActionTokensLabel, c);
        c.gridx = 3;
        c.gridy = 6;
        panel.add(friendlyIronManActionTokens, c);

        c.gridx = 4;
        c.gridy = 1;
        panel.add(enemyThorLocationLabel, c);
        c.gridx = 5;
        c.gridy = 1;
        panel.add(enemyThorLocation, c);
        c.gridx = 4;
        c.gridy = 2;
        panel.add(enemyThorClickNumberLabel, c);
        c.gridx = 5;
        c.gridy = 2;
        panel.add(enemyThorClickNumber, c);
        c.gridx = 4;
        c.gridy = 3;
        panel.add(enemyThorActionTokensLabel, c);
        c.gridx = 5;
        c.gridy = 3;
        panel.add(enemyThorActionTokens, c);
        c.gridx = 4;
        c.gridy = 4;
        panel.add(friendlyThorLocationLabel, c);
        c.gridx = 5;
        c.gridy = 4;
        panel.add(friendlyThorLocation, c);
        c.gridx = 4;
        c.gridy = 5;
        panel.add(friendlyThorClickNumberLabel, c);
        c.gridx = 5;
        c.gridy = 5;
        panel.add(friendlyThorClickNumber, c);
        c.gridx = 4;
        c.gridy = 6;
        panel.add(friendlyThorActionTokensLabel, c);
        c.gridx = 5;
        c.gridy = 6;
        panel.add(friendlyThorActionTokens, c);

        // Add the buttons to the panel
        c.gridx = 0;
        c.gridy = 7;
        panel.add(captainAmericaButton, c);
        c.gridx = 2;
        c.gridy = 7;
        panel.add(ironManButton, c);
        c.gridx = 4;
        c.gridy = 7;
        panel.add(thorButton, c);

        // Add the panel to the frame
        frame.add(panel);

        // Display the GUI
        frame.setVisible(true);

        captainAmericaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what cap should do
                // use A* and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                updateGameStateFromGUI();
                System.out.println("Captain America Button Pressed");

                // If they have 2 action tokens, do nothing and rest. Don't 'push' and take damage
                if (gs.friendlyCaptainAmerica.actionTokens >= 2) {
                    JOptionPane.showMessageDialog(null, "Captain America should do nothing to clear his action tokens.");
                    return;
                }

                // If in range of an enemy, attack!
                DFS dfs = new DFS();
                String start = gs.friendlyCaptainAmerica.location;
                // BEFORE MOVING, MAKE SURE THE SPOT IS NOT OCCUPIED
                // Go for enemy Captain America first; he is the weakest
                String end = gs.enemyCaptainAmericaLocation;
                dfs.aStar(start, end);
                // If out of range, get as close as possible using A*
                if (dfs.route.size() > gs.friendlyCaptainAmerica.getRangeValue()) {
                    if (dfs.route.size() >= gs.friendlyCaptainAmerica.getSpeedValue()) {
                        JOptionPane.showMessageDialog(null, "Enemy out of range, move Captain America to: " + dfs.route.get(gs.friendlyCaptainAmerica.getSpeedValue()));
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Enemy out of range, move Captain America to: " + dfs.route.get(dfs.route.size()-2));
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "If clear line of sight, attack enemy Captain America!\nElse move to: " + dfs.route.get(dfs.route.size()-2));
                }
                return;

                

            }
        });

        ironManButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what iron man should do
                // use DFS and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                updateGameStateFromGUI();
                System.out.println("Iron Man Button Pressed");
                
                // If they have 2 action tokens, do nothing and rest. Don't 'push' and take damage
                if (gs.friendlyIronMan.actionTokens >= 2) {
                    JOptionPane.showMessageDialog(null, "Iron Man should do nothing to clear his action tokens");
                    return;
                }
                // If in range of an enemy, attack!

                // If out of range, get as close as possible using A*
            }
        });

        thorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what thor should do
                // use DFS and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                updateGameStateFromGUI();
                System.out.println("Thor Button Pressed");
                
                // If they have 2 action tokens, do nothing and rest. Don't 'push' and take damage
                if (gs.friendlyThor.actionTokens >= 2) {
                    JOptionPane.showMessageDialog(null, "Thor should do nothing to clear his action tokens");
                    return;
                }
                // If in range of an enemy, attack!

                // If out of range, get as close as possible using A*
            }
        });
    }

    private void updateGameStateFromGUI() {
        gs = new GameState();
        // Captain America information
        gs.enemyCaptainAmericaLocation = enemyCaptainAmericaLocation.getText();
        gs.enemyCaptainAmericaClickNumber = Integer.parseInt(enemyCaptainAmericaClickNumber.getText());
        gs.friendlyCaptainAmerica.location = friendlyCaptainAmericaLocation.getText();
        gs.friendlyCaptainAmerica.clickNumber = Integer.parseInt(friendlyCaptainAmericaClickNumber.getText());
        gs.friendlyCaptainAmerica.actionTokens = Integer.parseInt(friendlyCaptainAmericaActionTokens.getText());
        // Iron Man information
        gs.enemyIronManLocation = enemyIronManLocation.getText();
        gs.enemyIronManClickNumber = Integer.parseInt(enemyIronManClickNumber.getText());
        gs.friendlyIronMan.location = friendlyIronManLocation.getText();
        gs.friendlyIronMan.clickNumber = Integer.parseInt(friendlyIronManClickNumber.getText());
        gs.friendlyIronMan.actionTokens = Integer.parseInt(friendlyIronManActionTokens.getText());
        // Thor information
        gs.enemyThorLocation = enemyThorLocation.getText();
        gs.enemyThorClickNumber = Integer.parseInt(enemyThorClickNumber.getText());
        gs.friendlyThor.location = friendlyThorLocation.getText();
        gs.friendlyThor.clickNumber = Integer.parseInt(friendlyThorClickNumber.getText());
        gs.friendlyThor.actionTokens = Integer.parseInt(friendlyThorActionTokens.getText());

        // Just for testing to see if it works
        //JOptionPane.showMessageDialog(null, "Here is the current GameState:\n" + gs.toString());
    }

    public static void main(String[] args) {
        AI ai = new AI();
    }

}
