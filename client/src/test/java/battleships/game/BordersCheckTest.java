package battleships.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BordersCheckTest {

  @DataProvider
  public Object[][] testPollForTopBorder() {
    return new Object[][] {
        {0, true},
        {1, true},
        {2, true},
        {3, true},
        {4, true},
        {5, true},
        {6, true},
        {7, true},
        {8, true},
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
  public void whenCheckingIfPositionBelongsToTopBorder_expectItBelongsOrNot(Integer position, boolean expectedResult) {
    //when
    boolean isPositionOnTopBorder = BordersCheck.isOnTopBorder(position);
    //then
    assertThat(isPositionOnTopBorder).isEqualTo(expectedResult);
  }

  @DataProvider
  public Object[][] testPollForBottomBorder() {
    return new Object[][] {
        {90, true},
        {91, true},
        {92, true},
        {93, true},
        {94, true},
        {95, true},
        {96, true},
        {97, true},
        {98, true},
        {99, true},
        {89, false},
        {100, false},
        {55, false},
        {-1, false},
        {-10, false},
        {120, false}
    };
  }

  @Test(dataProvider = "testPollForBottomBorder")
  public void whenCheckingIfPositionBelongsToBottomBorder_expectItBelongsOrNot(Integer position, boolean expectedResult) {
    //when
    boolean isPositionOnBottomBorder = BordersCheck.isOnBottomBorder(position);
    //then
    assertThat(isPositionOnBottomBorder).isEqualTo(expectedResult);
  }

  @DataProvider
  public Object[][] testPollForLeftBorder() {
    return new Object[][] {
        {0, true},
        {10, true},
        {20, true},
        {30, true},
        {40, true},
        {50, true},
        {60, true},
        {70, true},
        {80, true},
        {90, true},
        {55, false},
        {-1, false},
        {-10, false},
        {120, false},
        {100, false},
    };
  }

  @Test(dataProvider = "testPollForLeftBorder")
  public void whenCheckingIfPositionBelongsToLeftBorder_expectItBelongsOrNot(Integer position, boolean expectedResult) {
    //when
    boolean isPositionOnLeftBorder = BordersCheck.isOnLeftBorder(position);
    //then
    assertThat(isPositionOnLeftBorder).isEqualTo(expectedResult);
  }

  @DataProvider
  public Object[][] testPollForRightBorder() {
    return new Object[][] {
        {9, true},
        {19, true},
        {29, true},
        {39, true},
        {49, true},
        {59, true},
        {69, true},
        {79, true},
        {89, true},
        {99, true},
        {109, false},
        {-1, false},
        {-10, false},
        {120, false},
        {100, false},
        {-9, false},
    };
  }

  @Test(dataProvider = "testPollForRightBorder")
  public void whenCheckingIfPositionBelongsToRightBorder_expectItBelongsOrNot(Integer position, boolean expectedResult) {
    //when
    boolean isPositionOnRightBorder = BordersCheck.isOnRightBorder(position);
    //then
    assertThat(isPositionOnRightBorder).isEqualTo(expectedResult);
  }

}