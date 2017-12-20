package battleships.communication.messages;

import battleships.communication.Messagable;
import battleships.game.FieldState;
import battleships.game.GameResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalvoResult implements Messagable {

    private List<Integer> resultList;

    public SalvoResult(List<Integer> resultList) {


        this.resultList = resultList;
    }

    public List<Integer> getResultList() {
        return resultList;
    }
}
