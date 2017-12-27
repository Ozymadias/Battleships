package battleships.game;

import javafx.scene.paint.Color;

class Field {

  private final Integer position;
  private FieldState state;
  private Boolean isShot;

  Field(Integer position) {
    this.position = position;
    this.state = FieldState.EMPTY;
    this.isShot = false;
  }

  final Integer getPosition() {
    return position;
  }

  final Boolean isShot() {
    return isShot;
  }

  void setUnbrokenShipPartOn() {
    this.state = FieldState.UNBROKEN_SHIP_PART;
  }

  void setBrokenShipPartOn() {
    this.state = FieldState.BROKEN_SHIP_PART;
  }

  void shoot() {
    isShot = true;
    if (this.state == FieldState.UNBROKEN_SHIP_PART) {
      this.state = FieldState.BROKEN_SHIP_PART;
    }
  }

  boolean isEmpty() {
    return this.state.equals(FieldState.EMPTY);
  }

  public String stateMarkToString() {
    return state.getStateMark();
  }

  Color getColorBasedOnState() {
    return state.getColor();
  }

  void setBuffer() {
    this.state = FieldState.BUFFER;
  }

  Boolean isBuffered() {
    return this.state == FieldState.BUFFER;
  }

  public boolean isShipOn() {
    return (this.state == FieldState.UNBROKEN_SHIP_PART
        || this.state == FieldState.BROKEN_SHIP_PART);
  }

  public boolean isUnbrokenShipOn() {
    return this.state == FieldState.UNBROKEN_SHIP_PART;
  }
}
