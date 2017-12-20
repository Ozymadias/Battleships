package battleships;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameInProgress implements GameState {
    private final List<HandlerWrapper> observers;
    private final List<Fleet> playersFleets;

    GameInProgress(List<HandlerWrapper> observers, List<Fleet> playersFleets) {

        this.observers = observers;
        this.playersFleets = playersFleets;
    }

    @Override
    public GameState process() {
        List<Salvo> salvos = observers.stream().map(p -> (Salvo) p.raport())
                .collect(Collectors.toList());

        processSalvo(salvos, playersFleets);

        if (areAllShipsSunk(playersFleets))
            return new EndOfTheGame(observers, playersFleets);
        else return this;
    }

    private void processSalvo(List<Salvo> salvo, List<Fleet> fleet) {
        Fleet fleet1 = fleet.get(0);
        List<Integer> resultList = new ArrayList<>(fleet1.getAllPositions());
        resultList.removeAll(salvo.get(1).getSalvoPositions());
        SalvoResult salvoResult = new SalvoResult(resultList);
        Fleet fleet2 = fleet.get(1);
        List<Integer> resultList2 = new ArrayList<>(fleet2.getAllPositions());
        resultList2.removeAll(salvo.get(0).getSalvoPositions());
        SalvoResult salvoResult2 = new SalvoResult(resultList);
        sunkMeBaby(fleet1, resultList);
        sunkMeBaby(fleet2, resultList2);
        observers.get(0).getNotified(salvoResult);
        observers.get(1).getNotified(salvoResult2);
    }

    private void sunkMeBaby(Fleet fleet1, List<Integer> resultList) {
        fleet1.getShips().forEach(ship -> resultList.forEach(ship::killMast));
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }

    private boolean areAllShipsSunk(List<Fleet> fleet) {
        return fleet.stream().noneMatch(this::allMyFriendsAreDead);
    }

    private boolean allMyFriendsAreDead(Fleet fleet) {
        return fleet.getShips().stream().allMatch(Ship::isSunk);
    }
}
