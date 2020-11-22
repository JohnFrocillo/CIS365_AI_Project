public class GameState {

    // empty constructor
    public GameState() {}

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
    CaptainAmerica friendlyCaptainAmerica = new CaptainAmerica();
    // set or get whatever is needed for Cap here
    Thor friendlyThor = new Thor();
    IronMan friendlyIronMan = new  IronMan();
    
    // Total points KO'd for each side
    public int friendlyPoints;
    public int enemyPoints;

    // Keep track of walls that are broken
        //Ex: "A1-A2" Wall between A1 and A2 is gone
    public String[] brokenWalls;

    // Approximate time left on game clock
    public double timeLeftInGame;

    // Action tokens for each figure is tracked in the character's class

    // Was Captain America carried?
        // Again this might be best in the figure's own class
    public boolean captainAmericaWasCarried;
}
