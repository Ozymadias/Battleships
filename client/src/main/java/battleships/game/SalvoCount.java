package battleships.game;

import battleships.communication.Messagable;

public class SalvoCount implements Messagable {
    private final Integer count;

    public SalvoCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }
}
