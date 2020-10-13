public class GameState {

    // Location of each of the pieces Ex: "A1"
    public String friendlyCaptainAmericaLocation;
    public String friendlyIronManLocation;
    public String friendlyThorLocation;
    public String enemyCaptainAmericaLocation;
    public String enemyIronManLocation;
    public String enemyThorLocation;
    public String heavyObjectLocation;

    // Click number of all the characters
    public int friendlyCaptainAmericaClickNumber;
    public int friendlyIronManClickNumber;
    public int friendlyThorClickNumber;
    public int enemyCaptainAmericaClickNumber;
    public int enemyIronManClickNumber;
    public int enemyThorClickNumber;

    // Stats and powers for each figure at different click numbers
        // Should each piece have its own class to hold all their info?

    // How many points each figure is worth
        // Again this might be best in the figure's own class?
    public int captainAmericaPointValue;
    public int ironManPointValue;
    public int thorPointValue;
    
    // Total points KO'd for each side
    public int friendlyPoints;
    public int enemyPoints;

    // Keep track of walls that are broken
        //Ex: "A1-A2" Wall between A1 and A2 is gone
    public String[] brokenWalls;

    // Approximate time left on game clock
    public double timeLeftInGame;

    // Action tokens for each figure (how many each figure currently has)
        // Again this might be best in the figure's own class?
    public int friendlyCaptainAmericaActionTokens;
    public int friendlyIronManActionTokens;
    public int friendlyThorActionTokens;
    public int enemyCaptainAmericaActionTokens;
    public int enemyIronManActionTokens;
    public int enemyThorActionTokens;

    // Was Captain America carried?
        // Again this might be best in the figure's own class
    public boolean captainAmericaWasCarried;
}
