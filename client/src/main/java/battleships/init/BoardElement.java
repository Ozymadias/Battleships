package battleships.init;

class BoardElement {

    private final Integer position;
    private final ShipsRandomize.ElementState state;

    BoardElement(Integer position, ShipsRandomize.ElementState state) {
        this.position = position;
        this.state = state;
    }

    Integer getPosition(){
        return position;
    }

}
