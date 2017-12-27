package battleships.gameplay;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.game.GameResult;
import battleships.ships.Fleet;

import java.util.ArrayList;
import java.util.List;

class SalvoProcessor {
    List<SalvoResult> process(List<Salvo> salvos, List<Fleet> fleets) {

        List<SalvoResult> salvoResults = new ArrayList<>();

        salvoResults.add(calculate(salvos.get(0), fleets.get(1), salvos.get(1)));
        salvoResults.add(calculate(salvos.get(1), fleets.get(0), salvos.get(0)));

        return salvoResults;
    }

    private SalvoResult calculate(Salvo mySalvo, Fleet enemyFleet, Salvo salvoFromEnemy ) {
        List<Integer> resultingPositions = new ArrayList<>(mySalvo.getSalvoPositions());
        resultingPositions.retainAll(enemyFleet.getAllPositions());
        return new SalvoResult(resultingPositions, salvoFromEnemy.getSalvoPositions(), GameResult.NONE);
    }
}
