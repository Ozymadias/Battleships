package battleships.ships;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Fleet implements Messageable {
  private final List<Ship> ships;

  @JsonCreator
  public Fleet(
      @JsonProperty("ships") List<Ship> ships) {
    this.ships = ships;
  }

  public List<Ship> getShips() {
    return ships;
  }

  /**
   * Creates list of positions of fleet, both dead and living ships are counted.
   * @return returns list of Integers of every ships position in current fleet.
   */

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