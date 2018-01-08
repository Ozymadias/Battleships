package battleships.gameplay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EndingGameTest {
  private EndingGame endingGame;

  @BeforeMethod
  public void setUp() {
    endingGame = new EndingGame();
  }

  @Test
  public void whenInEndOfGameState_expectThatOnlyEndOfGameStateWillBeReturned() {
    assertThat(endingGame.process()).isExactlyInstanceOf(EndingGame.class);

  }

  @Test
  public void whenInEndOfGameState_expectedIsEndOfGameToBeTrue() {
    assertThat(endingGame.isEndOfTheGame()).isTrue();
  }
}