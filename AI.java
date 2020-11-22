import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AI {
    public static void main(String[] args) {
        // Create the game state to track everything
        GameState gs = new GameState();

        // create the frame for the GUI
        JFrame frame = new JFrame("Heroclix AI");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the panel to store the GUI items
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // create constraints for the panel
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 2, 4, 2);

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
        JLabel enemyCaptainAmericaLocationLabel = new JLabel("Enter Enemy Captain America Location:");
        JLabel enemyCaptainAmericaClickNumberLabel = new JLabel("Enter Enemy Captain America Click #:");

        JLabel friendlyIronManLocationLabel = new JLabel("Enter Friendly Iron Man Location:");
        JLabel friendlyIronManClickNumberLabel = new JLabel("Enter Friendly Iron Man Click #:");
        JLabel enemyIronManLocationLabel = new JLabel("Enter Enemy Iron Man Location:");
        JLabel enemyIronManClickNumberLabel = new JLabel("Enter Enemy Iron Man Click Number:");

        JLabel friendlyThorLocationLabel = new JLabel("Enter Friendly Thor Location:");
        JLabel friendlyThorClickNumberLabel = new JLabel("Enter Friendly Thor Click Number:");
        JLabel enemyThorLocationLabel = new JLabel("Enter Enemy THor Location:");
        JLabel enemyThorClickNumberLabel = new JLabel("Enter Enemy Thor Click Number:");

        // The fields that need to be filled out for the AI to make a decision
        JTextField friendlyCaptainAmericaLocation = new JTextField("--------");
        JTextField friendlyCaptainAmericaClickNumber = new JTextField("--------");
        JTextField enemyCaptainAmericaLocation = new JTextField("--------");
        JTextField enemyCaptainAmericaClickNumber = new JTextField("--------");

        JTextField friendlyIronManLocation = new JTextField("--------");
        JTextField friendlyIronManClickNumber = new JTextField("--------");
        JTextField enemyIronManLocation = new JTextField("--------");
        JTextField enemyIronManClickNumber = new JTextField("--------");

        JTextField friendlyThorLocation = new JTextField("--------");
        JTextField friendlyThorClickNumber = new JTextField("--------");
        JTextField enemyThorLocation = new JTextField("--------");
        JTextField enemyThorClickNumber = new JTextField("--------");

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
        panel.add(friendlyCaptainAmericaLocationLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(friendlyCaptainAmericaLocation, c);
        c.gridx = 0;
        c.gridy = 4;
        panel.add(friendlyCaptainAmericaClickNumberLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(friendlyCaptainAmericaClickNumber, c);

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
        panel.add(friendlyIronManLocationLabel, c);
        c.gridx = 3;
        c.gridy = 3;
        panel.add(friendlyIronManLocation, c);
        c.gridx = 2;
        c.gridy = 4;
        panel.add(friendlyIronManClickNumberLabel, c);
        c.gridx = 3;
        c.gridy = 4;
        panel.add(friendlyIronManClickNumber, c);

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
        panel.add(friendlyThorLocationLabel, c);
        c.gridx = 5;
        c.gridy = 3;
        panel.add(friendlyThorLocation, c);
        c.gridx = 4;
        c.gridy = 4;
        panel.add(friendlyThorClickNumberLabel, c);
        c.gridx = 5;
        c.gridy = 4;
        panel.add(friendlyThorClickNumber, c);

        // Add the buttons to the panel
        c.gridx = 0;
        c.gridy = 5;
        panel.add(captainAmericaButton, c);
        c.gridx = 2;
        c.gridy = 5;
        panel.add(ironManButton, c);
        c.gridx = 4;
        c.gridy = 5;
        panel.add(thorButton, c);

        // Add the panel to the frame
        frame.add(panel);
        // Display the GUI
        frame.setVisible(true);

        captainAmericaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what cap should do
                // use DFS and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                System.out.println("Captain America Button Pressed");
            }
        });

        ironManButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what iron man should do
                // use DFS and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                System.out.println("Iron Man Button Pressed");
            }
        });

        thorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Use the fields filled in to determine what thor should do
                // use DFS and should they move? Attack? Make decesion
                // Use GameState object gs to access and update data
                System.out.println("Thor Button Pressed");
            }
        });
    }
}
