package battleships.init.sequence;

class FieldForRandom {

    private final Integer position;
    private FieldStateForRandom state;

    FieldForRandom(Integer position) {
        this.position = position;
        this.state = FieldStateForRandom.EMPTY;
    }

    final Integer getPosition(){
        return position;
    }

    final FieldStateForRandom getState(){
        return this.state;
    }

    void setState(FieldStateForRandom elementState){
        this.state = elementState;
    }

    boolean isOccupied(){ return this.state.equals(FieldStateForRandom.OCCUPIED); }

    public String toString(){
       return "[" + position + " : " + state + "]";
    }

    public String positionToString() { return "[" + position + "]"; }

    public String stateMarkToString() { return state.getStateMark(); }
}
