package battleships.game;

class Field {

    //TODO: field provides Rectagle with color based on its state
    //maybe it's go ide to provide field interface
    //with implementations which differs it's state
    //and provides different rectangles

    private final Integer position;
    private FieldState state;

    Field(Integer position){
        this.position = position;
        this.state = FieldState.EMPTY;
    }

    final Integer getPosition() {
        return position;
    }

    final FieldState getState() {
        return state;
    }

    void setShipPartOn(){
        this.state = FieldState.UNBROKEN_SHIP_PART;
    }

    public String toString(){
        return "[" + position + " : " + state + "]";
    }
}
