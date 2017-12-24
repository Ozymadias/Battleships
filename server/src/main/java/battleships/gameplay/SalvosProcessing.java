package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.Salvo;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.util.List;

class SalvosProcessing implements GameState {


    private final BattleshipLog log = BattleshipLog.provideLogger(SalvosProcessing.class);
    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;
    private final List<Salvo> salvos;

    SalvosProcessing(List<BattleObserver> observers, List<Fleet> playersFleets, List<Salvo> salvos) {
        this.observers = observers;
        this.playersFleets = playersFleets;
        this.salvos = salvos;
    }

    @Override
    public GameState process() {
        log.info("processing salvos");
        return new SinkingShips(observers, playersFleets, new SalvoProcessor().process(salvos, playersFleets));
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
