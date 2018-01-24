package battleships.gameplay.util;

import battleships.Observers;
import battleships.communication.messages.SalvoResult;
import battleships.game.GameResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

public class TripletDP {
  
  private final static int PLAYER_TWO = 1;
  
  @DataProvider
  public static Object[][] fleetAndSalvosData() {
    return Triplet.provideTestDataForSinkingShips();
  }
  
  @DataProvider
  public static Object[] fleetOfTheSecondPlayerSunks() {
    return new Object[] {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
        Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(1, 2, 3))),
            new Fleet(Arrays.asList(Ship.createShip(4, 5, 6)))),
        Arrays.asList
                   (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(1, 2), GameResult.NONE)
                       , new SalvoResult(Arrays.asList(1, 2), Arrays.asList(4, 5, 6), GameResult.NONE))
    )};
  }
  
}
