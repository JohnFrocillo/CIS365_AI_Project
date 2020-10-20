public class CaptainAmerica {
    public int pointValue = 50;
    public String location;
    public int clickNumber;
    public int actionTokens;

    // empty constructor. Change or overload if needed
    public CaptainAmerica(){}

    // Based on the card in Project Appendix
    public int getSpeedValue() {

        // can only clear tokens this turn. Not possible to get a third token.
        if (actionTokens >= 2) {
            return 0;
        }

        switch (clickNumber) {
            case 1:
                return 8;
            case 2:
                return 7;
            case 3:
                return 7;
            case 4:
                return 6;
            case 5:
                return 6;
            case 6:
                return 5;
            default:
                // return 0 to imply they are KO'd or can't move yet
                return 0;
        }
    }

    // Based on the card in Project Appendix
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

    // Based on the card in Project Appendix
    public int getDefenseValue() {
        switch (clickNumber) {
            case 1:
                return 17;
            case 2:
                return 17;
            case 3:
                return 17;
            case 4:
                return 16;
            case 5:
                return 16;
            case 6:
                return 17;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    // Based on the card in Project Appendix
    public int getRangeValue() {
        return 5;
    }

    public int getDamageValue() {
        switch (clickNumber) {
            case 1:
                return 3;
            case 2:
                return 3;
            case 3:
                return 3;
            case 4:
                return 2;
            case 5:
                return 2;
            case 6:
                return 2;
            default:
                //return 0 to imply they are KO'd
                return 0;
        }
    }

    // Based on the card in Project Appendix
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
