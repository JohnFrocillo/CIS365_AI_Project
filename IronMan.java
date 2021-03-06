public class IronMan {
    public int pointValue = 100;
    public String location;
    public int clickNumber;
    public int actionTokens;

    // empty constructor. Change or overload if needed
    public IronMan(){}

    /**
     * @return True if Ironman is KOed. Otherwise false.
     */
    public boolean isKOd() {
        if (clickNumber > 7 || clickNumber < 0)
            return true;
        else
            return false;
    }

    /**
     * @return Speed value based on Cards for Ironman based on click.
     */
    public int getSpeedValue() {

        // can only clear tokens this turn. Not possible to get a third token.
        if (actionTokens >= 2) {
            return 0;
        }

        switch (clickNumber) {
            case 1:
            case 2:
            case 3:
                return 10;
            case 4:
            case 5:
                return 9;
            case 6:
            case 7:
                return 8;
            default:
                // return 0 to imply they are KO'd or can't move yet
                return 0;
        }
    }

    /**
     * @return Attack value based on Cards for Ironman based on click.
     */
    public int getAttackValue() {
        switch (clickNumber) {
            case 1:
            case 2:
            case 3:
                return 10;
            case 4:
            case 5:
            case 6:
            case 7:
                return 9;
            default:
                // return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Defense value based on Cards for Ironman based on click.
     */
    public int getDefenseValue() {
        switch (clickNumber) {
            case 1:
                return 18;
            case 2:
                return 17;
            case 3:
                return 17;
            case 4:
                return 17;
            case 5:
                return 17;
            case 6:
                return 16;
            case 7:
                return 16;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Range value based on Cards for Ironman based on click.
     */
    public int getRangeValue() {
        return 7;
    }

    /**
     * @return Damage value based on Cards for Ironman based on click.
     */
    public int getDamageValue() {
        switch (clickNumber) {
            case 1:
                return 4;
            case 2:
            case 3:
                return 3;
            case 4:
            case 5:
            case 6:
            case 7:
                return 2;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Powers based on Cards for Ironman based on click.
     */
    public String[]  getActivePower() {
        switch (clickNumber) {
            case 1:
            case 2:
            case 3:
                return new String[]{"Running Shot", "Energy Explosion", "Invulnerability"};
            case 4:
            case 5:
            case 6:
            case 7:
                return new String[]{"Sidestep", "Toughness", "Ranged Combat Expert"};
            default:
                //return empty to imply they are KO'd
                return new String[]{};
        }
    }
}
