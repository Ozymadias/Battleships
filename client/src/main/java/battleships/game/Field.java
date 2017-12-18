package battleships.game;

class Field {

    private final Integer position;
    private FieldState state;
    private Boolean isShoted;

    Field(Integer position){
        this.position = position;
        this.state = FieldState.EMPTY;
        this.isShoted = false;
    }

    final Integer getPosition() {
        return position;
    }

    final FieldState getState() {
        return state;
    }

    final Boolean isShoted(){ return isShoted; }

    void setShipPartOn(){
        this.state = FieldState.UNBROKEN_SHIP_PART;
    }

    public String toString(){
        return "[" + position + " : " + state + "]";
    }

}
