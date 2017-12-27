package battleships.ships;

import battleships.communication.Messagable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Mast implements Messagable {
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
