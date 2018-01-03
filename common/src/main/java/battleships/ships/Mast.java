package battleships.ships;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Mast implements Messageable {
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

  public Integer getPosition() {
    return position;
  }

  void kill() {
    isAlive = false;
  }
}
