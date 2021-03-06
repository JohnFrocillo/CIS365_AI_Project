import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
    JTextField friendlyCaptainAmericaLocation = new JTextField("--------", 3);
    JTextField friendlyCaptainAmericaClickNumber = new JTextField("--------", 3);
    JTextField friendlyCaptainAmericaActionTokens = new JTextField("--------", 3);
    JTextField enemyCaptainAmericaLocation = new JTextField("--------", 3);
    JTextField enemyCaptainAmericaClickNumber = new JTextField("--------", 3);
    JTextField enemyCaptainAmericaActionTokens = new JTextField("--------", 3);

    JTextField friendlyIronManLocation = new JTextField("--------", 3);
    JTextField friendlyIronManClickNumber = new JTextField("--------", 3);
    JTextField friendlyIronManActionTokens = new JTextField("--------", 3);
    JTextField enemyIronManLocation = new JTextField("--------", 3);
    JTextField enemyIronManClickNumber = new JTextField("--------", 3);
    JTextField enemyIronManActionTokens = new JTextField("--------", 3);

    JTextField friendlyThorLocation = new JTextField("--------", 3);
    JTextField friendlyThorClickNumber = new JTextField("--------", 3);
    JTextField friendlyThorActionTokens = new  JTextField("--------", 3);
    JTextField enemyThorLocation = new JTextField("--------", 3);
    JTextField enemyThorClickNumber = new JTextField("--------", 3);
    JTextField enemyThorActionTokens = new JTextField("--------", 3);

    // For pulling current stats
    JTextArea capStats = new JTextArea("Speed: ---\nAttack: ---\nDefense: ---\nRange: ---\nDamage Value: ---\nPower 1: ---\nPower 2: ---\nPower 3: ---\n");
    JTextArea ironManStats = new JTextArea("Speed: ---\nAttack: ---\nDefense: ---\nRange: ---\nDamage Value: ---\nPower 1: ---\nPower 2: ---\nPower 3: ---\n");
    JTextArea thorStats = new JTextArea("Speed: ---\nAttack: ---\nDefense: ---\nRange: ---\nDamage Value: ---\nPower 1: ---\nPower 2: ---\nPower 3: ---\n");

    // Additional information
    ArrayList<JLabel>     hObjectLocLabels = new ArrayList<>();
    ArrayList<JTextField> hObjLocs         = new ArrayList<>();

    /**
     * Initialize all Objects, Actions, and GUI for application.
     */
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

        // Add the stats to the panel
        c.gridx = 0;
        c.gridy = 8;
        panel.add(capStats,c);
        c.gridx = 2;
        c.gridy = 8;
        panel.add(ironManStats, c);
        c.gridx = 4;
        c.gridy = 8;
        panel.add(thorStats, c);

        // Add the object locations to the panel
        c.gridy = 9;
        for (int k = 0; k < 2; k++)
        {
            for (int i = 0; i < 3; i++)
            {
                c.gridx = i * 2;
                JLabel     l = new JLabel("Heavy Object " + (i + 1 + 3 * k) + " Location");
                JTextField t = new JTextField("", 3);
                hObjectLocLabels.add(l);
                hObjLocs.add(t);
                panel.add(l, c);
                c.gridx = i * 2 + 1;
                panel.add(t, c);
            }
            c.gridy++;
        }

        // Add the panel to the frame
        frame.add(panel);

        // Display the GUI
        frame.setVisible(true);
        fillTestData();

        // Decision tree for Captain America.
        captainAmericaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DFS dfs = new DFS();
                updateGameStateFromGUI();
                String result = "";
                System.out.println("Captain America Button Pressed");
                // Check action tokens and powers
                if(Arrays.asList(gs.friendlyCaptainAmerica.getActivePower()).contains("Willpower")
                    && gs.friendlyCaptainAmerica.actionTokens >= 1) {
                    // Can push without taking damage
                    result += "Use Willpower,\n";
                }
                else if (!Arrays.asList(gs.friendlyCaptainAmerica.getActivePower()).contains("Willpower")
                            && gs.friendlyCaptainAmerica.actionTokens >= 1) {
                    // Do not push and take damage
                    result += "Captain America should do nothing to clear his action tokens";
                    gs.friendlyCaptainAmerica.actionTokens = 0;
                    JOptionPane.showMessageDialog(null, result);
                    return;
                }

                // Use charge if possible
                if (Arrays.asList(gs.friendlyCaptainAmerica.getActivePower()).contains("Charge")) {
                    // see if charge can be used: half speed and free close attack
                    String start = gs.friendlyCaptainAmerica.location;

                    // Go for enemy Captain America first; he is the weakest
                    // See who is in range, and use minmax to compare who is the best target
                    String end = gs.enemyCaptainAmerica.location;
                    int newChargeSpeed = gs.friendlyCaptainAmerica.getSpeedValue()/2;
                    dfs = new DFS(); //clear the route/nodelist
                    dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                    if(dfs.route.size()-1 <= newChargeSpeed && !gs.enemyCaptainAmerica.isKOd()){
                        result += "Use Charge, move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2) + "\nand attack enemy Captain America";
                        gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    }
                    else {
                        dfs = new DFS();
                        end = gs.enemyIronMan.location;
                        dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                        if(dfs.route.size()-1 <= newChargeSpeed && !gs.enemyIronMan.isKOd()) {
                            result += "Use Charge, move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2) + "\nand attack enemy Iron Man";
                            gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                        else {
                            dfs = new DFS();
                            end = gs.enemyThor.location;
                            dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                            if(dfs.route.size()-1 <= newChargeSpeed && !gs.enemyThor.isKOd()) {
                                result += "Use Charge, move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2) + "\nand attack enemy Thor";
                                gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                                JOptionPane.showMessageDialog(null, result);
                                return;
                            }
                        }
                    }
                }

                // Check for close combat expert
                dfs = new DFS();
                if (Arrays.asList(gs.friendlyCaptainAmerica.getActivePower()).contains("Close Combat Expert")) {
                    // Check for close combat with enemy Cap
                    if (!gs.enemyCaptainAmerica.isKOd() &&
                        dfs.neighboringEnemyForCloseCombatExpert(gs.friendlyCaptainAmerica.location, gs.enemyCaptainAmerica.location)) {
                        if (gs.enemyCaptainAmerica.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() + 8);
                        else if (gs.enemyCaptainAmerica.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() &&
                            gs.enemyCaptainAmerica.getDefenseValue() <= gs.friendlyCaptainAmerica.getAttackValue() + 8)
                        {
                            result += "Use Close Combat Expert to add 2 to attack Captain America\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else if (gs.enemyCaptainAmerica.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() &&
                                    gs.enemyCaptainAmerica.getDefenseValue() <= gs.friendlyCaptainAmerica.getAttackValue() + 7)
                        {
                            result += "Use Close Combat Expert to add 1 to attack and 1 to damage Captain America\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else {
                            result += "Use Close Combat Expert to add 2 to damage Captain America\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }

                    }
                    // Check for close combat with enemy iron man
                    if (!gs.enemyIronMan.isKOd() &&
                        dfs.neighboringEnemyForCloseCombatExpert(gs.friendlyCaptainAmerica.location, gs.enemyIronMan.location)) {
                        if (gs.enemyIronMan.getDefenseValue() > gs.enemyIronMan.getAttackValue() + 8);
                        else if (gs.enemyIronMan.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() &&
                            gs.enemyIronMan.getDefenseValue() <= gs.friendlyCaptainAmerica.getAttackValue() + 8)
                        {
                            result += "Use Close Combat Expert to add 2 to attack Iron Man\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else if (gs.enemyIronMan.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() &&
                                    gs.enemyIronMan.getDefenseValue() <= gs.friendlyCaptainAmerica.getAttackValue() + 7)
                        {
                            result += "Use Close Combat Expert to add 1 to attack and 1 to damage Iron Man\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else {
                            result += "Use Close Combat Expert to add 2 to damage to Iron Man\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }

                    }
                    // Check for close combat with enemy thor
                    if (!gs.enemyThor.isKOd() &&
                        dfs.neighboringEnemyForCloseCombatExpert(gs.friendlyCaptainAmerica.location, gs.enemyThor.location)) {
                        if (gs.enemyThor.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() + 8);
                        else if (gs.enemyThor.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() &&
                            gs.enemyThor.getDefenseValue() <= gs.friendlyCaptainAmerica.getAttackValue() + 8)
                        {
                            result += "Use Close Combat Expert to add 2 to attack Thor\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else if (gs.enemyThor.getDefenseValue() > gs.friendlyCaptainAmerica.getAttackValue() &&
                                    gs.enemyThor.getDefenseValue() <= gs.friendlyCaptainAmerica.getAttackValue() + 7)
                        {
                            result += "Use Close Combat Expert to add 1 to attack and 1 to damage Thor\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else {
                            result += "Use Close Combat Expert to add 2 to damage Thor\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }

                    }
                }

                // See who, if anyone, is in range to attack
                dfs = new DFS();
                String start = gs.friendlyCaptainAmerica.location;
                String end = gs.enemyCaptainAmerica.location;

                int speed = gs.friendlyCaptainAmerica.getSpeedValue();
                dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                if (dfs.route.size()-1 <= speed && !gs.enemyCaptainAmerica.isKOd()) {
                    // enemy cap in range
                    // check for clear line of sight
                    int los = dfs.lineOfSight(start, end, gs.getBlockingLocations(true));
                    if (los > -1) {
                        if (los > 1)
                            result += "RANGE ";
                        else result += "CLOSE ";
                        result += "Attack enemy Captain America!\n";
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    }
                }
                else {
                    dfs = new DFS();
                    end = gs.enemyIronMan.location;
                    dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                    if (dfs.route.size()-1 <= speed && !gs.enemyIronMan.isKOd()) {
                        // enemy iron man in range
                        // check for clear line of sight
                        int los = dfs.lineOfSight(start, end, gs.getBlockingLocations(true));
                        if (los > -1) {
                            if (los > 1)
                                result += "RANGE ";
                            else result += "CLOSE ";
                            result += "\nAttack enemy Iron Man!";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                    }
                    else {
                        dfs = new DFS();
                        end = gs.enemyThor.location;
                        dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                        if (dfs.route.size()-1 <= speed && !gs.enemyThor.isKOd()) {
                            // enemy iron man in range
                            // check for clear line of sight
                            int los = dfs.lineOfSight(start, end, gs.getBlockingLocations(true));
                            if (los > -1) {
                                if (los > 1)
                                    result += "RANGE ";
                                else result += "CLOSE ";
                                result += "\nAttack enemy Thor!";
                                JOptionPane.showMessageDialog(null, result);
                                return;
                            }
                        }
                    }

                }

                // If not attacking (no one in range), use min max and A* to see who to move towards
                CaptainAmerica tempEnemyCap = new CaptainAmerica();
                tempEnemyCap.clickNumber = gs.enemyCaptainAmerica.clickNumber;
                IronMan tempEnemyIronMan = new IronMan();
                tempEnemyIronMan.clickNumber = gs.enemyIronMan.clickNumber;
                Thor tempEnemyThor = new Thor();
                tempEnemyThor.clickNumber = gs.enemyThor.clickNumber;

                // Make sure no one is ko'd, if they are pass a huge number for clickNum so it is not chosen
                if (tempEnemyCap.isKOd()) {
                    tempEnemyCap.clickNumber = -99;
                }
                if (tempEnemyIronMan.isKOd()) {
                    tempEnemyIronMan.clickNumber = -99;
                }
                if (tempEnemyThor.isKOd()) {
                    tempEnemyThor.clickNumber = -99;
                }
                MovementMinMax minMax = new MovementMinMax(tempEnemyCap.getDefenseValue(), tempEnemyCap.clickNumber, tempEnemyIronMan.getDefenseValue(), tempEnemyIronMan.clickNumber, tempEnemyThor.getDefenseValue(), tempEnemyThor.clickNumber);

                if (tempEnemyCap.isKOd() && tempEnemyIronMan.isKOd()) {
                    System.out.println("Thor");
                    dfs = new DFS();
                    end = gs.enemyThor.location;
                    dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                    if(dfs.route.size()-1 <= gs.friendlyCaptainAmerica.getSpeedValue() + 1) {
                        result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2);
                        gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    } // if they aren't fully in range of speed, move as far as possible
                    else {
                        result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(gs.friendlyCaptainAmerica.getSpeedValue() + 1);
                        gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    }

                }
                else if (tempEnemyCap.isKOd() && tempEnemyThor.isKOd()) {
                    System.out.println("Iron Man");
                    dfs = new DFS();
                    end = gs.enemyIronMan.location;
                    dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                    if(dfs.route.size()-1 <= gs.friendlyCaptainAmerica.getSpeedValue() + 1) {
                        result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2);
                        gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    }
                    // if they aren't fully in range of speed, move as far as possible
                    else {
                        result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(gs.friendlyCaptainAmerica.getSpeedValue() + 1);
                        gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    }
                }
                else if (tempEnemyIronMan.isKOd() && tempEnemyThor.isKOd()) {
                    System.out.println("Captain America");
                    dfs = new DFS();
                    end = gs.enemyCaptainAmerica.location;
                    dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                    if(dfs.route.size()-1 <= gs.friendlyCaptainAmerica.getSpeedValue() + 1) {
                        result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2);
                        gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    }
                    // if they aren't fully in range of speed, move as far as possible
                    else {
                        result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(gs.friendlyCaptainAmerica.getSpeedValue() + 1);
                        gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                        JOptionPane.showMessageDialog(null, result);
                        return;
                    }
                }
                else {
                    if (minMax.result == minMax.enemyCaptainAmericaValue) {
                        // Go for Cap
                        dfs = new DFS();
                        start = gs.friendlyCaptainAmerica.location;
                        end = gs.enemyCaptainAmerica.location;
                        dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                        if(dfs.route.size()-1 <= gs.friendlyCaptainAmerica.getSpeedValue() + 1) {
                            result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2);
                            gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                        else {
                            result += "Captain America move to: " + dfs.route.get(gs.friendlyCaptainAmerica.getSpeedValue() + 1);
                            gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                    }
                    else if (minMax.result == minMax.enemyIronManValue) {
                        // Go for Iron Man
                        dfs = new DFS();
                        start = gs.friendlyCaptainAmerica.location;
                        end = gs.enemyIronMan.location;
                        dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                        if(dfs.route.size()-1 <= gs.friendlyCaptainAmerica.getSpeedValue() + 1) {
                            result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2);
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                        else {
                            result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(gs.friendlyCaptainAmerica.getSpeedValue() + 1);
                            gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                    }
                    else {
                        // Go for Thor
                        dfs = new DFS();
                        start = gs.friendlyCaptainAmerica.location;
                        end = gs.enemyIronMan.location;
                        dfs.aStar(start, end, gs.getBlockingLocations(false), true);
                        if(dfs.route.size()-1 <= gs.friendlyCaptainAmerica.getSpeedValue() + 1) {
                            result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(dfs.route.size()-2);
                            gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                        else {
                            result += "Captain America move along route: " + dfs.routeToString() + " to: " + dfs.route.get(gs.friendlyCaptainAmerica.getSpeedValue() + 1);
                            gs.friendlyCaptainAmerica.location = ""+dfs.route.get(dfs.route.size()-2);
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                    }
                }
            }
        });

        // Decision tree for Ironman.
        ironManButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what iron man should do
                // use DFS and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                updateGameStateFromGUI();
                System.out.println("Iron Man Button Pressed");

                DFS dfs = new DFS();
                String result = "";
                String start = gs.friendlyIronMan.location;

                String target, targetLoc;

                int losCaptain = dfs.lineOfSight(start, gs.enemyCaptainAmerica.location, gs.getBlockingLocations(true));
                int losIronman = dfs.lineOfSight(start, gs.enemyIronMan.location, gs.getBlockingLocations(true));
                int losThor = dfs.lineOfSight(start, gs.enemyThor.location, gs.getBlockingLocations(true));

                CaptainAmerica tempEnemyCap = new CaptainAmerica();
                tempEnemyCap.clickNumber = gs.enemyCaptainAmerica.clickNumber;
                IronMan tempEnemyIronMan = new IronMan();
                tempEnemyIronMan.clickNumber = gs.enemyIronMan.clickNumber;
                Thor tempEnemyThor = new Thor();
                tempEnemyThor.clickNumber = gs.enemyThor.clickNumber;

                // Not enough tokens to attack or move
                if (gs.friendlyIronMan.actionTokens >= 1) {
                    JOptionPane.showMessageDialog(null, "Iron Man should do nothing to clear his action tokens");
                    return;
                }

                // Attack ---------------------------------------------------------------------------
                // Running Shot / Energy Explosion
                if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Running Shot")) {

                    // Check for running shot on cap
                    if (!tempEnemyCap.isKOd())
                    {
                        dfs.aStar(start, gs.enemyCaptainAmerica.location, gs.getBlockingLocations(false), false);
                        for (int i = 0;
                             i < gs.friendlyIronMan.getSpeedValue() / 2 - 1 && i < dfs.route.size();
                             i++)
                        {
                            int dist = dfs.lineOfSight(dfs.route.get(i).getName(), gs.enemyCaptainAmerica.location,
                                                       gs.getBlockingLocations(true));
                            if (dist > 0 && gs.friendlyIronMan.getRangeValue() >= dist)
                            {
                                target = "Captain America";
                                targetLoc = gs.enemyCaptainAmerica.location;
                                result += "Use Running Shot to move along route: " + dfs.routeToString() + " to " + dfs.route.get(i).getName() +
                                          " and attack Captain America\n";

                                // Energy Explosion on cap
                                if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Energy Explosion"))
                                {
                                    if (dfs.neighboringAllEnemy(targetLoc, gs.enemyIronMan.location,
                                                                gs.enemyThor.location))
                                    {
                                        result += "Use Energy Explosion to deal 2 damage to all enemies adjacent\n";
                                    }
                                }
                                
                                JOptionPane.showMessageDialog(null, result);
                                return;
                            }
                        }
                    }

                    // Check for running shot on ironman
                    if (!tempEnemyIronMan.isKOd())
                    {
                        dfs.aStar(start, gs.enemyIronMan.location, gs.getBlockingLocations(false), false);
                        for (int i = 0; i < gs.friendlyIronMan.getSpeedValue() / 2 -1; i++)
                        {
                            int dist = dfs.lineOfSight(dfs.route.get(i).getName(), gs.enemyIronMan.location,
                                                       gs.getBlockingLocations(true));
                            if (dist > 0 && gs.friendlyIronMan.getRangeValue() >= dist)
                            {
                                target = "Ironman";
                                targetLoc = gs.enemyIronMan.location;
                                result += "Use Running Shot to move along route: " + dfs.routeToString() + " to " + dfs.route.get(i).getName() +
                                          " and attack Ironman\n";

                                // Energy Explosion on iron man
                                if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Energy Explosion"))
                                {
                                    if (dfs.neighboringAllEnemy(targetLoc, gs.enemyCaptainAmerica.location,
                                                                gs.enemyThor.location))
                                    {
                                        result += "Use Energy Explosion to deal 2 damage to all enemies adjacent\n";
                                    }
                                }

                                JOptionPane.showMessageDialog(null, result);
                                return;
                            }
                        }
                    }

                    // Check for running shot on Thor
                    if (!tempEnemyThor.isKOd())
                    {
                        dfs.aStar(start, gs.enemyThor.location, gs.getBlockingLocations(false), false);
                        for (int i = 0; i < gs.friendlyIronMan.getSpeedValue() / 2 -1; i++)
                        {
                            int dist = dfs.lineOfSight(dfs.route.get(i).getName(), gs.enemyThor.location,
                                                       gs.getBlockingLocations(true));
                            if (dist > 0 && gs.friendlyIronMan.getRangeValue() >= dist)
                            {
                                target = "Thor";
                                targetLoc = gs.enemyThor.location;
                                result += "Use Running Shot to move along route: " + dfs.routeToString() + " to " + dfs.route.get(i).getName() +
                                          " and attack Thor\n";

                                // Energy Explosion on Thor
                                if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Energy Explosion"))
                                {
                                    if (dfs.neighboringAllEnemy(targetLoc, gs.enemyCaptainAmerica.location,
                                                                gs.enemyIronMan.location))
                                    {
                                        result += "Use Energy Explosion to deal 2 damage to all enemies adjacent\n";
                                    }
                                }

                                JOptionPane.showMessageDialog(null, result);
                                return;
                            }
                        }
                    }
                }

                // Ranged Combat Expert (Find target first)
                if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Ranged Combat Expert")) {
                    if (!tempEnemyCap.isKOd() && losCaptain == 1) {
                        if (tempEnemyCap.getDefenseValue() > gs.friendlyIronMan.getAttackValue() + 8);
                        else if (tempEnemyCap.getDefenseValue() > gs.friendlyIronMan.getAttackValue() &&
                            tempEnemyCap.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 8)
                        {
                            result += "Use Ranged Combat Expert to add 2 to attack Captain America\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else if (tempEnemyCap.getDefenseValue() > gs.friendlyIronMan.getAttackValue() &&
                                 tempEnemyCap.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 7)
                        {
                            result += "Use Ranged Combat Expert to add 1 to attack and 1 to damage Captain America\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else {
                            result += "Use Ranged Combat Expert to add 2 to damage Captain America\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                    }
                    if (!tempEnemyIronMan.isKOd() && losIronman == 1) {
                        if (tempEnemyIronMan.getDefenseValue() > gs.friendlyIronMan.getAttackValue() + 8);
                        else if (tempEnemyIronMan.getDefenseValue() > gs.friendlyIronMan.getAttackValue() &&
                                 tempEnemyIronMan.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 8)
                        {
                            result += "Use Ranged Combat Expert to add 2 to attack Ironman\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else if (tempEnemyIronMan.getDefenseValue() > gs.friendlyIronMan.getAttackValue() &&
                                 tempEnemyIronMan.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 7)
                        {
                            result += "Use Ranged Combat Expert to add 1 to attack and 1 to damage Ironman\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else {
                            result += "Use Ranged Combat Expert to add 2 to damage Ironman\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                    }
                    if (!tempEnemyThor.isKOd() && losThor == 1) {
                        if (tempEnemyThor.getDefenseValue() > gs.friendlyIronMan.getAttackValue() + 8);
                        else if (tempEnemyThor.getDefenseValue() > gs.friendlyIronMan.getAttackValue() &&
                                 tempEnemyThor.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 8)
                        {
                            result += "Use Ranged Combat Expert to add 2 to attack Thor\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else if (tempEnemyThor.getDefenseValue() > gs.friendlyIronMan.getAttackValue() &&
                                 tempEnemyThor.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 7)
                        {
                            result += "Use Ranged Combat Expert to add 1 to attack and 1 to damage Thor\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        } else
                        {
                            result += "Use Ranged Combat Expert to add 2 to damage Thor\n";
                            JOptionPane.showMessageDialog(null, result);
                            return;
                        }
                    }
                }

                // General Attack
                if (!tempEnemyCap.isKOd() &&
                    tempEnemyCap.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 6 &&
                    losCaptain > 0 && losCaptain <= gs.friendlyIronMan.getRangeValue()) {
                    // Energy Explosion on Cap
                    if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Energy Explosion"))
                    {
                        if (dfs.neighboringAllEnemy(gs.enemyCaptainAmerica.location, gs.enemyThor.location,
                                                    gs.enemyIronMan.location))
                        {
                            result += "Use Energy Explosion to deal 2 damage to all enemies adjacent\n";
                        }
                    } else
                    {
                        if (losCaptain > 1)
                            result += "RANGE ";
                        else result += "CLOSE ";
                        result += "Attack Captain America\n";
                    }
                    JOptionPane.showMessageDialog(null, result);
                    return;
                } else if (!tempEnemyIronMan.isKOd() &&
                           tempEnemyIronMan.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 6 &&
                           losIronman > 0 && losIronman <= gs.friendlyIronMan.getRangeValue()) {
                    // Energy Explosion on Ironman
                    if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Energy Explosion"))
                    {
                        if (dfs.neighboringAllEnemy(gs.enemyIronMan.location, gs.enemyThor.location,
                                                    gs.enemyCaptainAmerica.location))
                        {
                            result += "Use Energy Explosion to deal 2 damage to all enemies adjacent\n";
                        }
                    } else
                    {
                        if (losIronman > 1)
                            result += "RANGE ";
                        else result += "CLOSE ";
                        result += "Attack Ironman\n";
                    }
                    JOptionPane.showMessageDialog(null, result);
                    return;
                } else if (!tempEnemyThor.isKOd() &&
                           tempEnemyThor.getDefenseValue() <= gs.friendlyIronMan.getAttackValue() + 6 &&
                           losThor > 0 && losThor <= gs.friendlyIronMan.getRangeValue()) {
                    // Energy Explosion on Thor
                    if (Arrays.asList(gs.friendlyIronMan.getActivePower()).contains("Energy Explosion"))
                    {
                        if (dfs.neighboringAllEnemy(gs.enemyThor.location, gs.enemyCaptainAmerica.location,
                                                    gs.enemyIronMan.location))
                        {
                            result += "Use Energy Explosion to deal 2 damage to all enemies adjacent\n";
                        }
                    } else
                    {
                        if (losThor > 1)
                            result += "RANGE ";
                        else result += "CLOSE ";
                        result += "Attack Captain America\n";
                    }
                    JOptionPane.showMessageDialog(null, result);
                    return;
                }

                // TODO: Sidestep (idk if it matters too much)

                // Note from Prof:
                // Invulnerability and Toughness don't need to be in the AI, just roll for them
                // Move to enemy

                // Make sure no one is ko'd, if they are pass a huge number for clickNum so it is not chosen
                if (tempEnemyCap.isKOd()) {
                    tempEnemyCap.clickNumber = -99;
                }
                if (tempEnemyIronMan.isKOd()) {
                    tempEnemyIronMan.clickNumber = -99;
                }
                if (tempEnemyThor.isKOd()) {
                    tempEnemyThor.clickNumber = -99;
                }

                MovementMinMax minMax = new MovementMinMax(
                        tempEnemyCap.getDefenseValue(),
                        tempEnemyCap.clickNumber,
                        tempEnemyIronMan.getDefenseValue(),
                        tempEnemyIronMan.clickNumber,
                        tempEnemyThor.getDefenseValue(),
                        tempEnemyThor.clickNumber);

                // If out of range, get as close as possible using A*
                if (minMax.result == minMax.enemyCaptainAmericaValue)
                    dfs.aStar(start, gs.enemyCaptainAmerica.location, gs.getBlockingLocations(false), false);
                else if (minMax.result == minMax.enemyIronManValue)
                    dfs.aStar(start, gs.enemyIronMan.location, gs.getBlockingLocations(false), false);
                else
                    dfs.aStar(start, gs.enemyThor.location, gs.getBlockingLocations(false), false);

                String finalLoc = dfs.route.get(
                        gs.friendlyIronMan.getSpeedValue() + 1 > dfs.route.size() ? dfs.route.size() - 2 : gs.friendlyIronMan.getSpeedValue() + 1)
                        .getName();
                JOptionPane.showMessageDialog(
                        null,
                        "Enemy out of range, move Iron man along route: " + dfs.routeToString() + " to: " + finalLoc);
            }
        });

        // Decision tree for Thor.
        thorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what thor should do
                // use DFS and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                updateGameStateFromGUI();
                System.out.println("Thor Button Pressed");

                DFS dfs = new DFS();
                String result = "";
                String start = gs.friendlyThor.location;

                String target, targetLoc;

                int losCaptain = dfs.lineOfSight(start, gs.enemyCaptainAmerica.location, gs.getBlockingLocations(true));
                int losIronman = dfs.lineOfSight(start, gs.enemyIronMan.location, gs.getBlockingLocations(true));
                int losThor = dfs.lineOfSight(start, gs.enemyThor.location, gs.getBlockingLocations(true));

                CaptainAmerica tempEnemyCap = new CaptainAmerica();
                tempEnemyCap.clickNumber = gs.enemyCaptainAmerica.clickNumber;
                IronMan tempEnemyIronMan = new IronMan();
                tempEnemyIronMan.clickNumber = gs.enemyIronMan.clickNumber;
                Thor tempEnemyThor = new Thor();
                tempEnemyThor.clickNumber = gs.enemyThor.clickNumber;

                // If they have 2 action tokens, do nothing and rest. Don't 'push' and take damage
                if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Willpower") &&
                    gs.friendlyThor.actionTokens >= 1) {
                    result += "Use Willpower\n";
                } else if (!Arrays.asList(gs.friendlyThor.getActivePower()).contains("Willpower") &&
                           gs.friendlyThor.actionTokens >= 1)
                {
                    JOptionPane.showMessageDialog(null, "Thor should do nothing to clear his action tokens");
                    return;
                }

                // If in range of an enemy, attack!
                if (!tempEnemyCap.isKOd() && losCaptain > -1 && losCaptain < gs.friendlyThor.getRangeValue()) {

                    // Charge
                    if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Charge"))
                    {
                        dfs.aStar(gs.friendlyThor.location, gs.enemyCaptainAmerica.location,
                                  gs.getBlockingLocations(true), false);
                        if (dfs.route.size() - 1 < gs.friendlyThor.getSpeedValue() / 2 &&
                            dfs.route.size() > 2)
                        {
                            JOptionPane.showMessageDialog(null,
                                                          "Using Charge move along route: " + dfs.routeToString() + " to " + dfs.route.get(dfs.route.size() - 2) + " and CLOSE attack Captain America\n");
                            return;
                        }
                    }

                    // Running Shot
                    if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Running Shot")) {
                        dfs.aStar(gs.friendlyThor.location, gs.enemyCaptainAmerica.location,
                                  gs.getBlockingLocations(true), false);
                        if (dfs.route.size() > 2)
                        {
                            for (int i = 1; i < gs.friendlyThor.getSpeedValue() / 2; i++) {
                                if (dfs.lineOfSight(
                                        dfs.route.get(i),
                                        dfs.route.get(dfs.route.size() - 1),
                                        gs.getBlockingLocations(true)) <= gs.friendlyThor.getRangeValue()) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Using Running Shot move along route: " + dfs.routeToString() + " to " + dfs.route.get(i) + " and RANGE attack Captain America\n");
                                    return;
                                }
                            }

                        }
                    }

                    // Normal Range
                    if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Energy Explosion"))
                    {
                        if (dfs.neighboringAllEnemy(gs.enemyCaptainAmerica.location, gs.enemyThor.location,
                                                    gs.enemyIronMan.location))
                        {
                            result += "Use Energy Explosion to deal 2 damage to all enemies adjacent\n";
                        }
                    } else
                    {
                        if (losCaptain > 2)
                            result += "RANGE ";
                        else result += "CLOSE ";

                        result += "Attack Captain America\n";
                    }
                    JOptionPane.showMessageDialog(null, result);
                    return;
                }
                if (!tempEnemyIronMan.isKOd() && losIronman > -1 && losIronman < gs.friendlyThor.getRangeValue()) {
                    // Charge
                    if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Charge"))
                    {
                        dfs.aStar(gs.friendlyThor.location, gs.enemyIronMan.location,
                                  gs.getBlockingLocations(true), false);
                        if (dfs.route.size() - 1 < gs.friendlyThor.getSpeedValue() / 2 &&
                            dfs.route.size() > 2)
                        {
                            JOptionPane.showMessageDialog(null,
                                                          "Using Charge move along route: " + dfs.routeToString() + " to " + dfs.route.get(dfs.route.size() - 2) + " and CLOSE attack Ironman\n");
                            return;
                        }
                    }

                    // Running Shot
                    else if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Running Shot")) {
                        dfs.aStar(gs.friendlyThor.location, gs.enemyIronMan.location,
                                  gs.getBlockingLocations(true), false);
                        if (dfs.route.size() > 2)
                        {
                            for (int i = 1; i < gs.friendlyThor.getSpeedValue() / 2; i++) {
                                if (dfs.lineOfSight(
                                        dfs.route.get(i),
                                        dfs.route.get(dfs.route.size() - 1),
                                        gs.getBlockingLocations(true)) <= gs.friendlyThor.getRangeValue()) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Using Running Shot move along route: " + dfs.routeToString() + " to " + dfs.route.get(i) + " and RANGE attack Ironman\n");
                                    return;
                                }
                            }

                        }
                    }

                    // Normal Range
                    if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Energy Explosion"))
                    {
                        if (dfs.neighboringAllEnemy(gs.enemyIronMan.location, gs.enemyThor.location,
                                                    gs.enemyCaptainAmerica.location))
                        {
                            result += "Use Energy Explosion to deal 2 damage to all enemies adjacent to Ironman\n";
                        }
                    } else
                    {
                        if (losIronman > 2)
                            result += "RANGE ";
                        else result += "CLOSE ";

                        result += "Attack Ironman\n";
                    }
                    JOptionPane.showMessageDialog(null, result);
                    return;
                }
                if (!tempEnemyThor.isKOd() && losThor > -1 && losThor < gs.friendlyThor.getRangeValue()) {
                    // Charge
                    if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Charge"))
                    {
                        dfs.aStar(gs.friendlyThor.location, gs.enemyThor.location,
                                  gs.getBlockingLocations(true), false);
                        if (dfs.route.size() - 1 < gs.friendlyThor.getSpeedValue() / 2 &&
                            dfs.route.size() > 2)
                        {
                            JOptionPane.showMessageDialog(null,
                                                          "Using Charge move along route: " + dfs.routeToString() + " to " + dfs.route.get(dfs.route.size() - 2) + " and CLOSE attack Thor\n");
                            return;
                        }
                    }

                    // Running Shot
                    else if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Running Shot")) {
                        dfs.aStar(gs.friendlyThor.location, gs.enemyThor.location,
                                  gs.getBlockingLocations(true), false);
                        if (dfs.route.size() > 2)
                        {
                            for (int i = 1; i < gs.friendlyThor.getSpeedValue() / 2; i++) {
                                if (dfs.lineOfSight(
                                        dfs.route.get(i),
                                        dfs.route.get(dfs.route.size() - 1),
                                        gs.getBlockingLocations(true)) <= gs.friendlyThor.getRangeValue()) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Using Running Shot move along route: " + dfs.routeToString() + " to " + dfs.route.get(i) + " and RANGE attack Thor\n");
                                    return;
                                }
                            }

                        }
                    }

                    // Normal Range
                    if (Arrays.asList(gs.friendlyThor.getActivePower()).contains("Energy Explosion"))
                    {
                        if (dfs.neighboringAllEnemy(gs.enemyThor.location, gs.enemyIronMan.location,
                                                    gs.enemyCaptainAmerica.location))
                        {
                            result += "Use Energy Explosion to deal 2 damage to all enemies adjacent to Thor\n";
                        }
                    } else
                    {
                        if (losThor > 2)
                            result += "RANGE ";
                        else result += "CLOSE ";

                        result += "Attack Thor\n";
                    }
                    JOptionPane.showMessageDialog(null, result);
                    return;
                }

                // TODO: Sidestep

                // If out of range, get as close as possible using A*
                // Make sure no one is ko'd, if they are pass a huge number for clickNum so it is not chosen
                if (tempEnemyCap.isKOd()) {
                    tempEnemyCap.clickNumber = -99;
                }
                if (tempEnemyIronMan.isKOd()) {
                    tempEnemyIronMan.clickNumber = -99;
                }
                if (tempEnemyThor.isKOd()) {
                    tempEnemyThor.clickNumber = -99;
                }

                MovementMinMax minMax = new MovementMinMax(
                        tempEnemyCap.getDefenseValue(),
                        tempEnemyCap.clickNumber,
                        tempEnemyIronMan.getDefenseValue(),
                        tempEnemyIronMan.clickNumber,
                        tempEnemyThor.getDefenseValue(),
                        tempEnemyThor.clickNumber);

                // If out of range, get as close as possible using A*
                if (minMax.result == minMax.enemyCaptainAmericaValue)
                    dfs.aStar(start, gs.enemyCaptainAmerica.location, gs.getBlockingLocations(false), false);
                else if (minMax.result == minMax.enemyIronManValue)
                    dfs.aStar(start, gs.enemyIronMan.location, gs.getBlockingLocations(false), false);
                else
                    dfs.aStar(start, gs.enemyThor.location, gs.getBlockingLocations(false), false);

                String finalLoc = dfs.route.get(
                        gs.friendlyThor.getSpeedValue() + 1 >= dfs.route.size() ? dfs.route.size() - 2 : gs.friendlyThor.getSpeedValue() + 1)
                                           .getName();
                JOptionPane.showMessageDialog(
                        null,
                        "Enemy out of range, move Thor along path: " + dfs.routeToString() + " to: " + finalLoc);
            }
        });
    }

    /**
     * Parse all the inputs on the GUI to update the game state.
     */
    private void updateGameStateFromGUI() {
        gs = new GameState();
        // Captain America information
        gs.enemyCaptainAmerica.location = enemyCaptainAmericaLocation.getText();
        gs.enemyCaptainAmerica.clickNumber = Integer.parseInt(enemyCaptainAmericaClickNumber.getText());
        gs.friendlyCaptainAmerica.location = friendlyCaptainAmericaLocation.getText();
        gs.friendlyCaptainAmerica.clickNumber = Integer.parseInt(friendlyCaptainAmericaClickNumber.getText());
        gs.friendlyCaptainAmerica.actionTokens = Integer.parseInt(friendlyCaptainAmericaActionTokens.getText());
        // Iron Man information
        gs.enemyIronMan.location = enemyIronManLocation.getText();
        gs.enemyIronMan.clickNumber = Integer.parseInt(enemyIronManClickNumber.getText());
        gs.friendlyIronMan.location = friendlyIronManLocation.getText();
        gs.friendlyIronMan.clickNumber = Integer.parseInt(friendlyIronManClickNumber.getText());
        gs.friendlyIronMan.actionTokens = Integer.parseInt(friendlyIronManActionTokens.getText());
        // Thor information
        gs.enemyThor.location = enemyThorLocation.getText();
        gs.enemyThor.clickNumber = Integer.parseInt(enemyThorClickNumber.getText());
        gs.friendlyThor.location = friendlyThorLocation.getText();
        gs.friendlyThor.clickNumber = Integer.parseInt(friendlyThorClickNumber.getText());
        gs.friendlyThor.actionTokens = Integer.parseInt(friendlyThorActionTokens.getText());
        // object
        int i = 0;
        for (JTextField loc : hObjLocs) {
            gs.heavyObjectLocations[i] = loc.getText();
            i++;
        }
        updateStatsField();
    }

    public static void main(String[] args) {
        AI ai = new AI();
    }

    /**
     * Fill the GUI fields with test data.
     */
    private void fillTestData() {
        enemyCaptainAmericaLocation.setText("A5");
        enemyCaptainAmericaClickNumber.setText("1");
        enemyCaptainAmericaActionTokens.setText("0");
        friendlyCaptainAmericaLocation.setText("A1");
        friendlyCaptainAmericaClickNumber.setText("1");
        friendlyCaptainAmericaActionTokens.setText("0");

        enemyIronManLocation.setText("P5");
        enemyIronManClickNumber.setText("1");
        enemyIronManActionTokens.setText("0");
        friendlyIronManLocation.setText("P1");
        friendlyIronManClickNumber.setText("1");
        friendlyIronManActionTokens.setText("0");

        enemyThorLocation.setText("D5");
        enemyThorClickNumber.setText("1");
        enemyThorActionTokens.setText("0");
        friendlyThorLocation.setText("D1");
        friendlyThorClickNumber.setText("1");
        friendlyThorActionTokens.setText("0");

        for (JTextField text : hObjLocs) {
            text.setText("");
        }

        updateGameStateFromGUI();
    }

    /**
     * Compile all the stats for each character to display based on click count
     */
    private void updateStatsField() {
        if (!gs.friendlyCaptainAmerica.isKOd())
        {
            String capStr = "Speed: " + gs.friendlyCaptainAmerica.getSpeedValue() + "\n" +
                            "Attack: " + gs.friendlyCaptainAmerica.getAttackValue() + "\n" +
                            "Defense: " + gs.friendlyCaptainAmerica.getDefenseValue() + "\n" +
                            "Range: " + gs.friendlyCaptainAmerica.getRangeValue() + "\n" +
                            "Damage Value: " + gs.friendlyCaptainAmerica.getDamageValue() + "\n" +
                            "Power 1: " + gs.friendlyCaptainAmerica.getActivePower()[0] + "\n" +
                            "Power 2: " + gs.friendlyCaptainAmerica.getActivePower()[1] + "\n" +
                            "Power 3: " + gs.friendlyCaptainAmerica.getActivePower()[2] + "\n";
            capStats.setText(capStr);
        } else capStats.setText("Captain America is KOed");

        if (!gs.friendlyIronMan.isKOd())
        {
            String ironManStr = "Speed: " + gs.friendlyIronMan.getSpeedValue() + "\n" +
                                "Attack: " + gs.friendlyIronMan.getAttackValue() + "\n" +
                                "Defense: " + gs.friendlyIronMan.getDefenseValue() + "\n" +
                                "Range: " + gs.friendlyIronMan.getRangeValue() + "\n" +
                                "Damage Value: " + gs.friendlyIronMan.getDamageValue() + "\n" +
                                "Power 1: " + gs.friendlyIronMan.getActivePower()[0] + "\n" +
                                "Power 2: " + gs.friendlyIronMan.getActivePower()[1] + "\n" +
                                "Power 3: " + gs.friendlyIronMan.getActivePower()[2] + "\n";
            ironManStats.setText(ironManStr);
        } else capStats.setText("Ironman is KOed");

        if (!gs.friendlyThor.isKOd())
        {
            String thorStr = "Speed: " + gs.friendlyThor.getSpeedValue() + "\n" +
                             "Attack: " + gs.friendlyThor.getAttackValue() + "\n" +
                             "Defense: " + gs.friendlyThor.getDefenseValue() + "\n" +
                             "Range: " + gs.friendlyThor.getRangeValue() + "\n" +
                             "Damage Value: " + gs.friendlyThor.getDamageValue() + "\n" +
                             "Power 1: " + gs.friendlyThor.getActivePower()[0] + "\n" +
                             "Power 2: " + gs.friendlyThor.getActivePower()[1] + "\n" +
                             "Power 3: " + gs.friendlyThor.getActivePower()[2] + "\n";

            thorStats.setText(thorStr);
        } else capStats.setText("Thor is KOed");
    }
}
