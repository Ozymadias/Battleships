package battleships.ships;

import java.util.List;

public class Fleet {
    private final List<Ship> ships;

    public Fleet(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Ship> getShips() {
        return ships;
    }
}