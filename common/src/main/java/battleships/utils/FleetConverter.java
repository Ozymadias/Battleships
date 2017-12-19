package battleships.utils;

import battleships.ships.Fleet;
import battleships.ships.Mast;
import battleships.ships.Ship;

import java.util.HashMap;
import java.util.Map;

public class FleetConverter {

    public Map<Integer, Ship> convert(Fleet ships) {
        Map<Integer, Ship> shipMap = new HashMap<>();

        for (Ship ship : ships.getShips()) {
            for (Mast m : ship.getMasts()) {
                shipMap.put(m.getPosition(), ship);
            }
        }

        return shipMap;
    }
}
