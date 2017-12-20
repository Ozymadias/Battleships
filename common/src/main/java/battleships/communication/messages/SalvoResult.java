package battleships.communication.messages;

import battleships.communication.Messagable;
import battleships.game.FieldState;
import battleships.game.GameResult;

import java.util.HashMap;
import java.util.Map;

public class SalvoResult implements Messagable {
    Map<Integer,FieldState> positionAndState;
    GameResult gameResult;

    public SalvoResult () {
        positionAndState = new HashMap<>();
        gameResult = GameResult.NONE;
    }

    public void setPositionAndState(Integer position, FieldState state) {
        positionAndState.put(position,state);
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

}
