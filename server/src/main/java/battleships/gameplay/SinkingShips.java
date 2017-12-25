package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.List;
import java.util.stream.IntStream;

class SinkingShips implements GameState {
    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;
    private final List<SalvoResult> results;

    SinkingShips(List<BattleObserver> observers, List<Fleet> playersFleets, List<SalvoResult> results) {

        this.observers = observers;
        this.playersFleets = playersFleets;
        this.results = results;
    }

    @Override
    public GameState process() {
        IntStream.range(0, playersFleets.size())
                .forEach(p -> killShips(playersFleets.get(p).getShips(), results.get(p)));
        return new SendingSalvoResults(observers,playersFleets,results);
    }

    private void killShips(List<Ship> toBeKilled, SalvoResult toKill) {
        toBeKilled.forEach(p -> toKill.getResultList().forEach(p::killMast));
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
