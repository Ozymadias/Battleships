package battleships.game;

import javafx.scene.paint.Color;

enum FieldState {
    EMPTY(Color.AQUA),
    UNBROKEN_SHIP_PART(Color.GREEN),
    BROKEN_SHIP_PART(Color.YELLOW),
    SUNK_SHIP_PART(Color.RED);

    public Color getColor() {
        return color;
    }

    private final Color color;

    FieldState(Color color) {
        this.color = color;
    }
}
