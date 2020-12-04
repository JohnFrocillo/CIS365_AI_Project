public class CaptainAmerica {
    public int pointValue = 50;
    public String location;
    public int clickNumber;
    public int actionTokens;

    // empty constructor. Change or overload if needed
    public CaptainAmerica(){}

    /**
     * @return True if Captain America is KOed. Otherwise false.
     */
    public boolean isKOd() {
        if (clickNumber > 6 || clickNumber < 0)
            return true;
        else
            return false;
    }

    // Based on the card in Project Appendix

    /**
     * @return Speed value based on Cards for Captain America based on click.
     */
    public int getSpeedValue() {

        // can only clear tokens this turn. Not possible to get a third token.
        if (actionTokens >= 2) {
            return 0;
        }

        switch (clickNumber) {
            case 1:
                return 8;
            case 2:
            case 3:
                return 7;
            case 4:
            case 5:
                return 6;
            case 6:
                return 5;
            default:
                // return 0 to imply they are KO'd or can't move yet
                return 0;
        }
    }

    /**
     * @return Attack value based on Cards for Captain America based on click.
     */
    public int getAttackValue() {
        switch (clickNumber) {
            case 1:
                return 11;
            case 2:
                return 10;
            case 3:
                return 10;
            case 4:
                return 9;
            case 5:
                return 9;
            case 6:
                return 9;
            default:
                // return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Defense value based on Cards for Captain America based on click.
     */
    public int getDefenseValue() {
        switch (clickNumber) {
            case 1:
            case 2:
            case 3:
            case 6:
                return 17;
            case 4:
            case 5:
                return 16;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Range value based on Cards for Captain America.
     */
    public int getRangeValue() {
        return 5;
    }

    /**
     * @return Damage value based on Cards for Captain America based on click.
     */
    public int getDamageValue() {
        switch (clickNumber) {
            case 1:
            case 2:
            case 3:
                return 3;
            case 4:
            case 5:
            case 6:
                return 2;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    /**
     * @return Powers based on Cards for Captain America based on click.
     */
    public String[]  getActivePower() {
        switch (clickNumber) {
            case 1:
                return new String[]{"Charge", "Combat Reflexes", "Leadership"};
            case 2:
                return new String[]{"Charge", "Combat Reflexes", "Leadership"};
            case 3:
                return new String[]{"Charge", "Combat Reflexes", "Leadership"};
            case 4:
                return new String[]{"Sidestep", "Willpower", "Close Combat Expert"};
            case 5:
                return new String[]{"Sidestep", "Willpower", "Close Combat Expert"};
            case 6:
                return new String[]{"Sidestep", "Willpower", "Close Combat Expert"};
            default:
                //return empty to imply they are KO'd
                return new String[]{};
        }
    }
}
