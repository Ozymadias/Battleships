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
        Salvo salvo1 = (Salvo) clientHandlerMap.get(Players.PLAYER1).receiveMessage();
        Salvo salvo2 = (Salvo) clientHandlerMap.get(Players.PLAYER2).receiveMessage();

        SalvoResult salvoResult1 = processSalvo(salvo1,fleet2);
        SalvoResult salvoResult2 = processSalvo(salvo2,fleet1);

        boolean player1Wins = areAllShipsSunk(fleet2);
        boolean player2Wins = areAllShipsSunk(fleet1);

        if (player1Wins && player2Wins) {
            salvoResult1.setGameResult(GameResult.DRAW);
            salvoResult2.setGameResult(GameResult.DRAW);
        } else if (player1Wins) {
            salvoResult1.setGameResult(GameResult.WIN);
            salvoResult2.setGameResult(GameResult.LOOSE);
        } else if (player2Wins) {
            salvoResult1.setGameResult(GameResult.LOOSE);
            salvoResult2.setGameResult(GameResult.WIN);
        }

        clientHandlerMap.get(Players.PLAYER1).sendMessage(salvoResult1);
        clientHandlerMap.get(Players.PLAYER1).sendMessage(salvoResult2);
        clientHandlerMap.get(Players.PLAYER2).sendMessage(salvoResult2);
        clientHandlerMap.get(Players.PLAYER2).sendMessage(salvoResult1);

        if (player1Wins || player2Wins) {
            return new EndOfTheGame();
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

    private boolean areAllShipsSunk(Fleet fleet) {
        return fleet.getShips().stream().filter(p->!p.isSunk()).count() == 0;
    }
}
