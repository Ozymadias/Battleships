package battleships.game;

public enum SeqCount {
  DEFAULT(10);

  private final int count;

  SeqCount(int count) {
    this.count = count;
  }

  public int getValue() {
    return count;
  }
}
