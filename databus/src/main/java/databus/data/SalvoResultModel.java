package databus.data;

import battleships.communication.Messageable;
import battleships.game.GameResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import databus.DataType;
import databus.DataTypeMember;

import java.util.List;

/**
 * This class represents SalvoResultModel message that can be send from a server to clients.
 * It is a response for a SalvoModel message send lately to the server.
 */
public class SalvoResultModel implements DataType {

  private final List<Integer> resultList;
  private final List<Integer> salvoPositions;
  private GameResult gameResult;

  /**
   * @param resultList list of positions where enemy ships were hit
   * @param salvoPositions positions of salvo from enemy ships
   * @param gameResult it indicates current game result caused by last salvo shot.
   */
  @JsonCreator
  public SalvoResultModel(
      @JsonProperty("resultList") List<Integer> resultList,
      @JsonProperty("salvoPositions") List<Integer> salvoPositions,
      @JsonProperty("gameResult") GameResult gameResult) {

    this.resultList = resultList;
    this.salvoPositions = salvoPositions;
    this.gameResult = gameResult;
  }

  /**
   * Setter for setting GameResult used by CalculatingGameResult class.
   *
   * @param gameResult current result of a game.
   */
  public void setGameResult(GameResult gameResult) {
    this.gameResult = gameResult;
  }

  /**
   * It returns list of positions where enemy ships were hit.
   */
  public List<Integer> getResultList() {
    return resultList;
  }

  /**
   * It returns positions of salvo from enemy ships.
   */
  public List<Integer> getSalvoPositions() {
    return salvoPositions;
  }

  /**
   * It returns current game result caused by last salvo shot.
   */
  public GameResult getGameResult() {
    return gameResult;
  }

  @Override
  public void acceptMemeber(DataTypeMember dataTypeMember) {
    dataTypeMember.visit(this);
  }
}
