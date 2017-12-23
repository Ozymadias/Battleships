package battleships.game;

class BordersCheck {

    private static final int TOP_LEFT_CORNER_POSITION = 0;
    private static final int TOP_RIGHT_CORNER_POSITION = 9;
    private static final int BOTTOM_LEFT_CORNER_POSITION = 90;
    private static final int BOTTOM_RIGHT_CORNER_POSITION = 99;
    private static final int SEQUENCE_LENGTH = 10;

    static Boolean isOnTopBorder(Integer number) {
        return number >= TOP_LEFT_CORNER_POSITION && number <= TOP_RIGHT_CORNER_POSITION;
    }

    static Boolean isOnBottomBorder(Integer number) {
        return number >= BOTTOM_LEFT_CORNER_POSITION && number <= BOTTOM_RIGHT_CORNER_POSITION;
    }

    static Boolean isOnLeftBorder(Integer number){
        return (number >= TOP_LEFT_CORNER_POSITION && number <= BOTTOM_LEFT_CORNER_POSITION)
            && (new Integer(number % SEQUENCE_LENGTH)).equals(0);
    }

    static Boolean isOnRightBorder(Integer number){
        return (number >= TOP_RIGHT_CORNER_POSITION && number <= BOTTOM_RIGHT_CORNER_POSITION)
               && (new Integer(number % SEQUENCE_LENGTH)).equals(SEQUENCE_LENGTH - 1);
    }


}
