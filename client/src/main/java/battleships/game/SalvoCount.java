package battleships.game;

import battleships.communication.Messageable;

class SalvoCount implements Messageable {
  private final Integer count;

  SalvoCount(Integer count) {
    this.count = count;
  }

  Integer getCount() {
    return count;
  }
}
