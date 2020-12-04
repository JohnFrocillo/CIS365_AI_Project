import java.util.ArrayList;

public class GameState {

    // empty constructor
    public GameState() {}

    // Stats and powers for each figure at different click numbers
    CaptainAmerica friendlyCaptainAmerica = new CaptainAmerica();
    Thor friendlyThor = new Thor();
    IronMan friendlyIronMan = new  IronMan();
    // Enemies
    CaptainAmerica enemyCaptainAmerica = new CaptainAmerica();
    Thor enemyThor = new Thor();
    IronMan enemyIronMan = new IronMan();
    
    // Total points KO'd for each side
    public int friendlyPoints;
    public int enemyPoints;

    public String[] heavyObjectLocations = new String[6];

    // Keep track of walls that are broken
        //Ex: "A1-A2" Wall between A1 and A2 is gone
    public String[] brokenWalls;

    // Approximate time left on game clock
    public double timeLeftInGame;

    // Action tokens for each figure is tracked in the character's class

    // Was Captain America carried?
        // Again this might be best in the figure's own class
    public boolean captainAmericaWasCarried;

    /**
     * @return All LoS and movement blocking object locations
     */
    public ArrayList<String> getBlockingLocations(boolean considerFriendly) {
        ArrayList<String> list = new ArrayList<>();
        if (considerFriendly)
        {
            list.add(friendlyCaptainAmerica.location);
            list.add(friendlyIronMan.location);
            list.add(friendlyThor.location);
        }

        list.add(enemyCaptainAmerica.location);
        list.add(enemyIronMan.location);
        list.add(enemyThor.location);
        for (String loc : heavyObjectLocations) {
            if (!loc.equals("")) list.add(loc);
        }
        return list;
    }

    @Override
    public String toString() {
        String returnValue = "";
        returnValue += "Friendly Captain America: Click Number, Location:  " + friendlyCaptainAmerica.clickNumber + " " + friendlyCaptainAmerica.location + "\n";
        returnValue += "Friendly Iron Man: Click Number, Location:  " + friendlyIronMan.clickNumber + " " + friendlyIronMan.location + "\n";
        returnValue += "Friendly Thor: Click number, Location:  " + friendlyThor.clickNumber + " " + friendlyThor.location + "\n";
        returnValue += "Enemy Captain America: Click Number, Location:  " + enemyCaptainAmerica.clickNumber + " " + enemyCaptainAmerica.location + "\n";
        returnValue += "Enemy Iron Man: Click Number, Location:  " + enemyIronMan.clickNumber + " " + enemyIronMan.location + "\n";
        returnValue += "Enemy Thor: Click Number, Location:  " + enemyThor.clickNumber + " " + enemyThor.location + "\n";
    
        return returnValue;
    }
}
