package battleships.game;

class BordersCheck {

    static Boolean isOnTopBorder(Integer number) { return number >= 0 && number <=9; }

    static Boolean isOnBottomBorder(Integer number) { return number >= 90 && number <=99; }

    static Boolean isOnLeftBorder(Integer number){
        return new Integer(number % 10).equals(0);
    }

    static Boolean isOnRightBorder(Integer number){
        return new Integer(number % 10).equals(9);
    }


}
