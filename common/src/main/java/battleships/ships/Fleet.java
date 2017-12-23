package battleships.ships;

import battleships.communication.Messagable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fleet implements Messagable {
    private List<Ship> ships;

    public Fleet() {
        ships = new ArrayList<>();
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public Fleet(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Integer> getAllPositions() {
        return ships
                .stream()
                .flatMap(ship1 -> ship1
                        .getMasts()
                        .stream())
                .map(Mast::getPosition)
                .collect(Collectors.toList());
    }
}