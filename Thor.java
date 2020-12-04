public class Thor {
    public int pointValue = 150;
    public String location;
    public int clickNumber;
    public int actionTokens;

    // empty constructor. Change or overload if needed
    public Thor(){}

    /**
     * @return True if Thor is KOed. Otherwise false.
     */
    public boolean isKOd() {
        if (clickNumber > 9 || clickNumber < 0)
            return true;
        else
            return false;
    }

    /**
     * @return Speed value based on Cards for Thor based on click.
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
            case 4:
            case 5:
            case 6:
                return 10;
            case 7:
            case 8:
            case 9:
                return 9;
            default:
                // return 0 to imply they are KO'd or can't move yet
                return 0;
        }
    }

    /**
     * @return Attack value based on Cards for Thor based on click.
     */
    public int getAttackValue() {
        switch (clickNumber) {
            case 1:
            case 2:
            case 3:
                return 11;
            case 4:
            case 5:
            case 6:
                return 10;
            case 7:
            case 8:
            case 9:
                return 9;
            default:
                // return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Defense value based on Cards for Thor based on click.
     */
    public int getDefenseValue() {
        switch (clickNumber) {
            case 1:
                return 18;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return 17;
            case 9:
                return 16;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Range value based on Cards for Thor based on click.
     */
    public int getRangeValue() {
        return 6;
    }

    /**
     * @return Damage value based on Cards for Thor based on click.
     */
    public int getDamageValue() {
        switch (clickNumber) {
            case 1:
            case 2:
                return 4;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return 3;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Powers based on Cards for Thor based on click.
     */
    public String[]  getActivePower() {
        switch (clickNumber) {
            case 1:
            case 2:
            case 3:
                return new String[]{"Charge", "Super Strength", "Impervious"};
            case 4:
            case 5:
            case 6:
                return new String[]{"Running Shot", "Energy Explosion", "Invulnerability"};
            case 7:
            case 8:
            case 9:
                return new String[]{"Sidestep", "Lightning Smash", "Willpower"};
            default:
                //return empty to imply they are KO'd
                return new String[]{};
        }
    }
}
