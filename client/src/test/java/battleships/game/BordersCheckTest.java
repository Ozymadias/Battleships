package battleships.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BordersCheckTest {

  @DataProvider
  public Object[][] testPollForTopBorder() {
    return new Object[][] {
        {0, true},
        {5, true},
        {9, true},
        {10, false},
        {55, false},
        {99, false},
        {-1, false},
        {-10, false},
        {120, false}
    };
  }

  @Test(dataProvider = "testPollForTopBorder")
  public void givenPosition_whenCheckingIfBelongsToTopBorder_resultShouldBeAsExpected(Integer position, boolean expectedResult) {
    assertThat(BordersCheck.isOnTopBorder(position)).isEqualTo(expectedResult);
  }

  @DataProvider
  public Object[][] testPollForBottomBorderBorder() {
    return new Object[][] {
        {90, true},
        {95, true},
        {99, true},
        {0, false},
        {10, false},
        {55, false},
        {-1, false},
        {-10, false},
        {120, false}
    };
  }

  @Test(dataProvider = "testPollForBottomBorderBorder")
  public void givenPosition_whenCheckingIfBelongsToBottomBorder_resultShouldBeAsExpected(Integer position, boolean expectedResult) {
    assertThat(BordersCheck.isOnBottomBorder(position)).isEqualTo(expectedResult);
  }

  @DataProvider
  public Object[][] testPollForLeftBorderBorder() {
    return new Object[][] {
        {0, true},
        {10, true},
        {90, true},
        {55, false},
        {-1, false},
        {-10, false},
        {120, false},
        {100, false},
    };
  }

  @Test(dataProvider = "testPollForLeftBorderBorder")
  public void givenPosition_whenCheckingIfBelongsToLeftBorder_resultShouldBeAsExpected(Integer position, boolean expectedResult) {
    assertThat(BordersCheck.isOnLeftBorder(position)).isEqualTo(expectedResult);
  }

  @DataProvider
  public Object[][] testPollForRightBorderBorder() {
    return new Object[][] {
        {9, true},
        {39, true},
        {99, true},
        {55, false},
        {-1, false},
        {-10, false},
        {120, false},
        {100, false},
        {-9, false},
        {109, false}
    };
  }

  @Test(dataProvider = "testPollForRightBorderBorder")
  public void givenPosition_whenCheckingIfBelongsToRightBorder_resultShouldBeAsExpected(Integer position, boolean expectedResult) {
    assertThat(BordersCheck.isOnRightBorder(position)).isEqualTo(expectedResult);
  }

}