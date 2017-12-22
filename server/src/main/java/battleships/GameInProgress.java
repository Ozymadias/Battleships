package battleships;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameInProgress implements GameState {
    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;
    private final BattleshipLog log = BattleshipLog.provideLogger(GameInProgress.class);

    GameInProgress(List<BattleObserver> observers, List<Fleet> playersFleets) {
        this.observers = observers;
        this.playersFleets = playersFleets;
    }

    @Override
    public GameState process() {
        log.info("Received fleets!");
        List<Salvo> salvos = observers.stream().map(p -> (Salvo) p.receiveMessage())
                .collect(Collectors.toList());
        processSalvo(salvos, playersFleets);
        if (areAllShipsSunk())
            return new EndOfTheGame(observers, playersFleets);
        else return this;
    }

    private void processSalvo(List<Salvo> salvo, List<Fleet> fleet) {
        Collections.reverse(salvo);
        IntStream.range(0, fleet.size())
                .forEach(i1 -> fleet.get(i1)
                        .getAllPositions()
                        .removeAll(salvo
                                .get(i1)
                                .getSalvoPositions()));

        IntStream.range(0, observers.size())
                .forEach(i -> observers.get(i).sendMessage(new SalvoResult(fleet.get(i).getAllPositions())));
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }

    boolean areAllShipsSunk() {
        return playersFleets.stream().anyMatch(this::allMyFriendsAreDead);
    }

    private boolean allMyFriendsAreDead(Fleet fleet) {
        return fleet.getShips().stream().allMatch(Ship::isSunk);
    }
}
