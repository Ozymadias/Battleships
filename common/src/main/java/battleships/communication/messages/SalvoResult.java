package battleships.communication.messages;

import battleships.communication.Messagable;
import battleships.game.GameResult;

import java.util.List;

public class SalvoResult implements Messagable {

    private List<Integer> resultList;
    private List<Integer> salvoPositions;
    private GameResult gameResult;

    public SalvoResult() {
    }

    public SalvoResult(List<Integer> resultList, List<Integer> salvoPositions, GameResult gameResult) {

        this.resultList = resultList;
        this.salvoPositions = salvoPositions;
        this.gameResult = gameResult;
    }

    public void setResultList(List<Integer> resultList) {
        this.resultList = resultList;
    }

    public void setSalvoPositions(List<Integer> salvoPositions) {
        this.salvoPositions = salvoPositions;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public List<Integer> getResultList() {
        return resultList;
    }

    public List<Integer> getSalvoPositions() {
        return salvoPositions;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
