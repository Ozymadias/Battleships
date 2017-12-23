package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class ProcessingSalvos implements GameState {
    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;
    private final List<Salvo> salvos;
    private final BattleshipLog log = BattleshipLog.provideLogger(ProcessingSalvos.class);

    ProcessingSalvos(List<BattleObserver> observers, List<Fleet> playersFleets, List<Salvo> salvos) {

        this.observers = observers;
        this.playersFleets = playersFleets;
        this.salvos = salvos;
    }

    @Override
    public GameState process() {
        Collections.reverse(salvos);
        salvos.forEach(p -> log.info("Salvo was received "));
        IntStream.range(0, playersFleets.size())
                .forEach(i1 -> playersFleets.get(i1)
                        .getAllPositions()
                        .removeAll(salvos
                                .get(i1)
                                .getSalvoPositions()));
        //log.info(new SalvoResult(playersFleets.get(1).getAllPositions()).toString());
//        IntStream.range(0, observers.size())
//                .forEach(i -> observers.get(i).sendMessage(new SalvoResult(playersFleets.get(i).getAllPositions())));
        log.info("Results were send");
        return new ProcessingSalvos(observers,playersFleets,salvos);
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
