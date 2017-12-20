package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.game.FieldState;
import battleships.game.GameResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import battleships.utils.FleetConverter;

import java.util.Map;

public class GameInProgress implements GameState {
    private Map<Players, ClientHandler> clientHandlerMap;
    private Fleet fleet1;
    private Fleet fleet2;

    public GameInProgress(Map<Players, ClientHandler> clientHandlerMap, Fleet fleet1, Fleet fleet2) {

        this.clientHandlerMap = clientHandlerMap;
        this.fleet1 = fleet1;
        this.fleet2 = fleet2;
    }

    @Override
    public GameState process() {
        Salvo salvo2 = (Salvo) clientHandlerMap.get(Players.PLAYER2).receiveMessage();
        Salvo salvo1 = (Salvo) clientHandlerMap.get(Players.PLAYER1).receiveMessage();
        SalvoResult salvoResult1 = processSalvo(salvo1,fleet2);
        SalvoResult salvoResult2 = processSalvo(salvo2,fleet1);

        clientHandlerMap.get(Players.PLAYER1).sendMessage(salvoResult1);
        clientHandlerMap.get(Players.PLAYER1).sendMessage(salvoResult2);
        clientHandlerMap.get(Players.PLAYER2).sendMessage(salvoResult2);
        clientHandlerMap.get(Players.PLAYER2).sendMessage(salvoResult1);

        boolean player1Wins = areAllShipsSunked(fleet2);
        boolean player2Wins = areAllShipsSunked(fleet1);
        if (player1Wins && player2Wins) {
            return new EndOfTheGame(GameResult.DRAW);
        } else if (player1Wins) {
            return new EndOfTheGame(GameResult.WIN,Players.PLAYER1);
        } else if (player2Wins) {
            return new EndOfTheGame(GameResult.WIN, Players.PLAYER2);
        }
        return this;
    }

    private SalvoResult processSalvo(Salvo salvo, Fleet fleet) {
        SalvoResult salvoResult = new SalvoResult();
        Map<Integer,Ship> positionsToShips = new FleetConverter().convert(fleet);
        for (Integer position : salvo.getSalvoPositions()) {
            Ship shipOnPosition = positionsToShips.get(position);
            if (shipOnPosition == null) {
                salvoResult.setPositionAndState(position, FieldState.EMPTY);
            } else {
                salvoResult.setPositionAndState(position, FieldState.BROKEN_SHIP_PART);
                shipOnPosition.killMast(position);
            }
        }
        return salvoResult;

    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }

    private boolean areAllShipsSunked(Fleet fleet) {

        return fleet.getShips().stream().filter(p->!p.isSunk()).count() == 0;
    }
}
