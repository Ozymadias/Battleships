package battleships.game;

class Field {

    private final Integer position;
    private FieldState state;
    private Boolean isShot;
    //todo if field is border field shoul be addintional property

    Field(Integer position){
        this.position = position;
        this.state = FieldState.EMPTY;
        this.isShot = false;
    }

    final Integer getPosition() {
        return position;
    }

    final FieldState getState() {
        return state;
    }

    final Boolean isShot(){ return isShot; }

    void setShipPartOn(){
        this.state = FieldState.UNBROKEN_SHIP_PART;
    }

    void shoot(){
        isShot = true;
    }

    void setBorder() {this.state = FieldState.BORDER; }

    void setState(FieldState elementState){
        this.state = elementState;
    }

    boolean isOccupied(){ return this.state.equals(FieldState.SUNK_SHIP_PART)
            || this.state.equals(FieldState.UNBROKEN_SHIP_PART)
            || this.state.equals(FieldState.BROKEN_SHIP_PART); }

    boolean isEmpty(){ return this.state.equals(FieldState.EMPTY); }

    public String toString(){
        return "[" + position + " : " + state + "]";
    }

    public String positionToString() { return "[" + position + "]"; }

    public String stateMarkToString() { return state.getStateMark(); }

}
