package battleships.communication.messages;

import battleships.communication.Messagable;
import battleships.game.FieldState;

import java.util.HashMap;
import java.util.Map;

public class SalvoResult implements Messagable {
    Map<Integer,FieldState> positionAndState;

    public SalvoResult () {
        positionAndState = new HashMap<>();
    }

    public void setPositionAndState(Integer position, FieldState state) {
        positionAndState.put(position,state);
    }

}
