package battleships.init.sequence;

class Field {

    private final Integer position;
    private FieldState state;

    Field(Integer position) {
        this.position = position;
        this.state = FieldState.EMPTY;
    }

    final Integer getPosition(){
        return position;
    }

    final FieldState getState(){
        return this.state;
    }

    void setState(FieldState elementState){
        this.state = elementState;
    }

    public String toString(){
       return "[" + position + " : " + state + "]";
    }

    public String positionToString() { return "[" + position + "]"; }
}
