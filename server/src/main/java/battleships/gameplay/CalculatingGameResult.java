package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.SalvoResult;
import battleships.game.GameResult;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.List;
import java.util.stream.Collectors;

public class CalculatingGameResult implements GameState {
    private final BattleshipLog log = BattleshipLog.provideLogger(CalculatingGameResult.class);
    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;
    private final List<SalvoResult> results;

    public CalculatingGameResult(List<BattleObserver> observers, List<Fleet> playersFleets, List<SalvoResult> results) {
        this.observers = observers;
        this.playersFleets = playersFleets;
        this.results = results;
    }

    @Override
    public GameState process() {
        log.info("processing game result");
        boolean isEndOfTheGame = calculateGameResult(playersFleets,results);
        return new SendingSalvoResults(observers,playersFleets, results, isEndOfTheGame);
    }

    private boolean calculateGameResult(List<Fleet> playersFleets, List<SalvoResult> results) {

        List<Fleet> sunkenFleets = playersFleets.stream().filter(this::isFleetSunk).collect(Collectors.toList());

        if (sunkenFleets.size() == 2 ) {
            results.forEach(p -> p.setGameResult(GameResult.DRAW));
            log.info("We have a draw");
            return true;
        } else if (sunkenFleets.size() == 1) {
            if (playersFleets.get(0).equals(sunkenFleets.get(0))) {
                log.info("Player 1 loose the game");
                log.info("Player 2 win the game");
                results.get(0).setGameResult(GameResult.LOOSE);
                results.get(1).setGameResult(GameResult.WIN);
                return true;
            } else {
                log.info("Player 1 win the game");
                log.info("Player 2 loose the game");
                results.get(0).setGameResult(GameResult.WIN);
                results.get(1).setGameResult(GameResult.LOOSE);
                return true;
            }
        }
        log.info("Game continue");
        return false;
    }

    private boolean isFleetSunk(Fleet fleet) {
        return fleet.getShips().stream().allMatch(Ship::isSunk);
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
