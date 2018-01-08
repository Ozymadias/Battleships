package battleships.game;

import javafx.scene.paint.Color;

enum FieldState {
  EMPTY(Color.AQUA, "e"),
  UNBROKEN_SHIP_PART(Color.GREEN, "o"),
  BROKEN_SHIP_PART(Color.YELLOW, "o"),
  BUFFER(Color.AQUA, "b");

  Color getColor() {
    return color;
  }

  String getStateMark() {
    return stateMark;
  }

  private final Color color;
  private final String stateMark;

  FieldState(Color color, String stateMark) {
    this.color = color;
    this.stateMark = stateMark;
  }
}
