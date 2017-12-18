package battleships.init.sequence;

public enum  FieldState {
    EMPTY("e"),
    OCCUPIED("o"),
    BUFFER("b");

    private String stateMark;

    FieldState(String stateMark) {
        this.stateMark = stateMark;
    }

    public String getStateMark() {
        return stateMark;
    }
}
