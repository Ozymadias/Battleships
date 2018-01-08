package battleships.ships;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents Fleet message that is send from a client to a server.
 */
public class Fleet implements Messageable {
  private final List<Ship> ships;

  /**
   * This is a constructor for Fleet. It is used by Json as a property based creator.
   *
   * @param ships list of ships belonging to this fleet.
   */
  @JsonCreator
  public Fleet(
      @JsonProperty("ships") List<Ship> ships) {
    this.ships = ships;
  }

  /**
   * It returns a list of Ships in a Fleet.
   */
  public List<Ship> getShips() {
    return ships;
  }

  /**
   * Creates list of positions of fleet, both dead and living ships are counted.
   *
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