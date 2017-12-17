package battleships.ships;

public class Mast {
    private boolean isAlive = true;
    private final Integer position;

    Mast(Integer position) {
        this.position = position;
    }

    boolean isAlive() {
        return isAlive;
    }

    public Integer getPosition() {
        return position;
    }

    void kill() {
        isAlive = false;
    }
}
