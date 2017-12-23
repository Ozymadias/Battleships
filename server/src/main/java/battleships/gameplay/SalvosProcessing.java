package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;

import java.util.List;

class SalvosProcessing implements GameState {


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
        return new ShipsSinking(observers, playersFleets, new SalvoProcessor().process(salvos, playersFleets));
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
