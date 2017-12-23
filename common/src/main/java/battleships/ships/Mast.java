package battleships.ships;

import battleships.communication.Messagable;

public class Mast implements Messagable {
    private boolean isAlive = true;
    private Integer position;

    public Mast() {
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

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

    public String toString(){
        return position.toString();
    }
}
