package battleships.ships;

import battleships.communication.Messagable;

import java.util.List;

public class Fleet implements Messagable {
    private final List<Ship> ships;

    public Fleet(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Ship> getShips() {
        return ships;
    }
}