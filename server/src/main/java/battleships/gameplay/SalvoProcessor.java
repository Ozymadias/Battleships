package battleships.gameplay;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalvoProcessor {
    public List<SalvoResult> process(List<Salvo> salvos, List<Fleet> fleets) {
        Collections.reverse(salvos);
        List<Integer> resultListOfPlayer0 = new ArrayList<>(salvos.get(0).getSalvoPositions());
        List<Integer> resultListOfPlayer1 = new ArrayList<>(salvos.get(1).getSalvoPositions());
        resultListOfPlayer0.retainAll(fleets.get(0).getAllPositions());
        resultListOfPlayer1.retainAll(fleets.get(1).getAllPositions());
        List<SalvoResult> salvoResults = new ArrayList<>();
        salvoResults.add(new SalvoResult(resultListOfPlayer0, salvos.get(0).getSalvoPositions()));
        salvoResults.add(new SalvoResult(resultListOfPlayer1, salvos.get(1).getSalvoPositions()));
        return salvoResults;
    }
}
