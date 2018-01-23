package battleships.gameplay;

import battleships.game.GameResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class CalculatingGameResultTest {

  @Test(dataProvider = "calculatingResultProvider")
  public void whenGivenDataIsProcessedByShipSinkingAndResultCalculating_expectResultToMatchExpectedResult(Triplet triplet, int playerIndex, GameResult expectedResult) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet.salvoResults);
    //when
    GameState calculatingGameResult = sinkingShips.process();
    calculatingGameResult.process();
    //then
    assertThat(triplet.salvoResults.get(playerIndex).getGameResult()).isEqualTo(expectedResult);
  }

  @Test
  public void whenGameStateIsCalculatingGameResult_expectIsEndOfGameToBeFalse() {
    //given
    SinkingShips sinkingShips = new SinkingShips(mock(List.class), mock(List.class), mock(List.class));
    //then
    assertThat(sinkingShips.isEndOfTheGame()).isFalse();
  }

  @DataProvider(name = "calculatingResultProvider")
  public static Object[][] calculatingResultProvider() {

    return Triplet.provideTestDataForCalculatingResults();
  }


}