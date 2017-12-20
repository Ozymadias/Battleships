package battleships.game;

class Field {

    private final Integer position;
    private FieldState state;
    private Boolean isShot;

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

}
