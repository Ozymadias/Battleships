package battleships.game;

class Field {

    private final Integer position;
    private FieldState state;
    private Boolean isShot;
    private BorderType borderType;

    Field(Integer position){
        this.position = position;
        this.state = FieldState.EMPTY;
        this.isShot = false;
        this.borderType = BorderType.NONE;
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

    void setTopBorder() { this.borderType = BorderType.TOP; }

    void setBottomBorder() { this.borderType = BorderType.BOTTOM; }

    void setLeftBorder() { this.borderType = BorderType.LEFT; }

    void setRightBorder() { this.borderType = BorderType.RIGHT; }

    boolean isBorder() { return borderType == BorderType.NONE; }

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

    public boolean isOnLeftBorder() {
        return borderType == BorderType.LEFT;
    }

    public boolean isOnRightBorder() {
        return borderType == BorderType.RIGHT;
    }
}
