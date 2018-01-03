package battleships.communication.messages;

import battleships.communication.Messageable;
import battleships.game.GameResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SalvoResult implements Messageable {

  private final List<Integer> resultList;
  private final List<Integer> salvoPositions;
  private GameResult gameResult;

  @JsonCreator
  public SalvoResult(
      @JsonProperty("resultList") List<Integer> resultList,
      @JsonProperty("salvoPositions") List<Integer> salvoPositions,
      @JsonProperty("gameResult") GameResult gameResult) {

    this.resultList = resultList;
    this.salvoPositions = salvoPositions;
    this.gameResult = gameResult;
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
