package battleships.game;

import javafx.scene.paint.Color;

enum FieldState {
    EMPTY(Color.AQUA, "e"),
    UNBROKEN_SHIP_PART(Color.GREEN, "o"),
    BROKEN_SHIP_PART(Color.YELLOW, "o"),
    SUNK_SHIP_PART(Color.RED, "o"),
    BUFFER(Color.GRAY, "b");

    public Color getColor() {
        return color;
    }

    public String getStateMark() {
        return stateMark;
    }

    private final Color color;
    private final String stateMark;

    FieldState(Color color, String stateMark) {
        this.color = color;
        this.stateMark = stateMark;
    }
}
