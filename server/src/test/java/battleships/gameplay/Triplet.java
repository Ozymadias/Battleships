package battleships.gameplay;

import battleships.Observers;
import battleships.communication.messages.SalvoResult;
import battleships.game.GameResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

class Triplet {
  private final static int PLAYER_ONE = 0;
  private final static int PLAYER_TWO = 1;
  List<Observers> observers;
  List<Fleet> fleets;
  List<SalvoResult> salvoResults;

  private Triplet(List<Observers> observers, List<Fleet> fleets, List<SalvoResult> salvoResults) {
    this.observers = observers;
    this.fleets = fleets;
    this.salvoResults = salvoResults;
  }

  static Object[][] provideTestDataForSinkingShips() {
    return new Object[][] {

        //player two fleet should be sunk
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(1, 2, 3))),
                new Fleet(Arrays.asList(Ship.createShip(4, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(1, 2), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(1, 2), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_TWO, true},

        //player one fleet should be sunk
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32))),
                new Fleet(Arrays.asList(Ship.createShip(4, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_ONE, true},

        //player one fleet should not be sunk
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32), Ship.createShip(54, 55, 56))),
                new Fleet(Arrays.asList(Ship.createShip(10, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_ONE, false},

        //player two fleet should be sunk even when it contains multiple ships
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32), Ship.createShip(54, 55, 56))),
                new Fleet(Arrays.asList(Ship.createShip(1, 2, 3, 4), Ship.createShip(10, 20, 30)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(10, 20, 1, 30, 2, 3, 4), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(10, 20, 1, 30, 2, 3, 4), GameResult.NONE))
        ), PLAYER_TWO, true},

        //player two fleet should not be sunk even when it contains multiple ships
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32), Ship.createShip(54, 55, 56))),
                new Fleet(Arrays.asList(Ship.createShip(1, 2, 3, 4), Ship.createShip(10, 20, 30)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(10, 20, 1, 3, 4), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(10, 20, 1, 3, 4), GameResult.NONE))
        ), PLAYER_TWO, false}
    };
  }

  static Object[][] provideTestDataForCalculatingResults() {
    return new Object[][] {

        //player one should win
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(1, 2, 3))),
                new Fleet(Arrays.asList(Ship.createShip(4, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(1, 2), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(1, 2), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_ONE, GameResult.WIN},

        //draw should happen for player two
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32))),
                new Fleet(Arrays.asList(Ship.createShip(4, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_TWO, GameResult.DRAW},

        //draw should happen for player one
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32))),
                new Fleet(Arrays.asList(Ship.createShip(4, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_ONE, GameResult.DRAW},

        //player one gamestate should be NONE as game continues
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32), Ship.createShip(54, 55, 56))),
                new Fleet(Arrays.asList(Ship.createShip(10, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_ONE, GameResult.NONE},

        //player two gamestate should be NONE as game continues
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32), Ship.createShip(54, 55, 56))),
                new Fleet(Arrays.asList(Ship.createShip(10, 5, 6)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(4, 5, 6), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(4, 5, 6), GameResult.NONE))
        ), PLAYER_TWO, GameResult.NONE},

        //player One should win even when enemy fleet contains multiple ships
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32), Ship.createShip(1, 2, 3, 4))),
                new Fleet(Arrays.asList(Ship.createShip(1, 2, 3, 4), Ship.createShip(34, 33, 32)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(33, 32, 1, 34, 2, 3, 4), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(33, 32, 1, 34, 2, 3, 4), GameResult.NONE))
        ), PLAYER_ONE, GameResult.WIN},

        //player Two should lose even when player fleet contains multiple ships
        {new Triplet(Arrays.asList(mock(Observers.class), mock(Observers.class)),
            Arrays.asList(new Fleet(Arrays.asList(Ship.createShip(34, 33, 32), Ship.createShip(1, 2, 3, 4))),
                new Fleet(Arrays.asList(Ship.createShip(1, 2, 3, 4), Ship.createShip(34, 33, 32)))),
            Arrays.asList
                (new SalvoResult(Arrays.asList(33, 32, 1, 34, 2, 3, 4), Arrays.asList(34, 33, 32), GameResult.NONE)
                    , new SalvoResult(Arrays.asList(34, 33, 32), Arrays.asList(33, 32, 1, 34, 2, 3, 4), GameResult.NONE))
        ), PLAYER_TWO, GameResult.LOOSE},
    };
  }
}