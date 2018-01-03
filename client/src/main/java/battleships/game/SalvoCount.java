package battleships.game;

import battleships.communication.Messageable;

public class SalvoCount implements Messageable {
  private final Integer count;

  public SalvoCount(Integer count) {
    this.count = count;
  }

  public Integer getCount() {
    return count;
  }
}
