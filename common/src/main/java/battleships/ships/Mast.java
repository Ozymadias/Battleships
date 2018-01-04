package battleships.ships;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents mast that is send from a client to a server in a Fleet message.
 */
class Mast implements Messageable {
  private boolean isAlive;
  private final Integer position;

  @JsonCreator
  Mast(
      @JsonProperty("position") Integer position) {
    this.position = position;
    this.isAlive = true;
  }

  boolean isAlive() {
    return isAlive;
  }

  Integer getPosition() {
    return position;
  }

  void kill() {
    isAlive = false;
  }
}
