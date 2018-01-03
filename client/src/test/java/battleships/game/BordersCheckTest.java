package battleships.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import static battleships.utils.BattleshipUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BordersCheckTest {

  private class PositionExpectedResultPair {
    int position;
    boolean expectedResult;
    PositionExpectedResultPair(int position, IntPredicate intPredicate) {
      this.position = position;
      this.expectedResult = intPredicate.test(position);
    }
  }

  private boolean isWithinRange(int position, int min , int max) {
    return position>=min && position <=max;
  }

  @DataProvider
  public Object[] testPollForTopBorder() {
    return IntStream.generate(() -> provideRandomNumber(-10, 110))
        .limit(20)
        .mapToObj(i -> new PositionExpectedResultPair(i, j-> isWithinRange(j,0,9) ))
        .toArray();
  }

  @Test(dataProvider = "testPollForTopBorder")
  public void whenCheckingIfPositionBelongsToTopBorder_expectItBelongsOrNot(PositionExpectedResultPair positionExpectedResultPair) {
    assertThat(BordersCheck.isOnTopBorder(positionExpectedResultPair.position))
        .isEqualTo(positionExpectedResultPair.expectedResult);
  }

  @DataProvider
  public Object[] testPollForBottomBorder() {
    return IntStream.generate(() -> provideRandomNumber(-10, 110))
        .limit(20)
        .mapToObj(i -> new PositionExpectedResultPair(i, j-> isWithinRange(j, 90, 99)))
        .toArray();
  }

  @Test(dataProvider = "testPollForBottomBorder")
  public void whenCheckingIfPositionBelongsToBottomBorder_expectItBelongsOrNot(PositionExpectedResultPair positionExpectedResultPair) {
    assertThat(BordersCheck.isOnBottomBorder(positionExpectedResultPair.position))
        .isEqualTo(positionExpectedResultPair.expectedResult);
  }

  @DataProvider
  public Object[] testPollForLeftBorder() {
    return IntStream.generate(() -> provideRandomNumber(-10, 110))
        .limit(20)
        .mapToObj(i -> new PositionExpectedResultPair(i, j-> j%10 == 0 && isWithinRange(j,0, 99)))
        .toArray();
  }

  @Test(dataProvider = "testPollForLeftBorder")
  public void whenCheckingIfPositionBelongsToLeftBorder_expectItBelongsOrNot(PositionExpectedResultPair positionExpectedResultPair) {
    assertThat(BordersCheck.isOnLeftBorder(positionExpectedResultPair.position))
        .isEqualTo(positionExpectedResultPair.expectedResult);
  }

  @DataProvider
  public Object[] testPollForRightBorder() {
    return IntStream.generate(() -> provideRandomNumber(-10, 110))
        .limit(20)
        .mapToObj(i -> new PositionExpectedResultPair(i, j -> j % 10 == 9 && isWithinRange(j, 0, 99)))
        .toArray();
  }

  @Test(dataProvider = "testPollForRightBorder")
  public void whenCheckingIfPositionBelongsToRightBorder_expectItBelongsOrNot(PositionExpectedResultPair positionExpectedResultPair) {
    assertThat(BordersCheck.isOnRightBorder(positionExpectedResultPair.position))
        .isEqualTo(positionExpectedResultPair.expectedResult);
  }

}