package battleships.init.sequence;

public enum FieldStateForRandom {
    EMPTY("e"),
    OCCUPIED("o"),
    BUFFER("b"),
    BORDER("x");

    private String stateMark;

    FieldStateForRandom(String stateMark) {
        this.stateMark = stateMark;
    }

    public String getStateMark() {
        return stateMark;
    }
}
