package battleships.ships;

import battleships.communication.Messagable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fleet implements Messagable {
    private final List<Ship> ships;

    @JsonCreator
    public Fleet(
            @JsonProperty("ships") List<Ship> ships) {
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