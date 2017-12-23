package battleships.gameplay;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class SalvoProcessor {
    public List<SalvoResult> process(List<Salvo> salvos, List<Fleet> fleets) {
        Collections.reverse(salvos);

        List<SalvoResult> salvoResults = new ArrayList<>();

        IntStream.range(0, fleets.size())
                .forEach(p -> salvoResults.add(calculate(salvos.get(p), fleets.get(p))));

        return salvoResults;
    }

    private SalvoResult calculate(Salvo salvo, Fleet fleet) {
        List<Integer> resultingPositions = new ArrayList<>(salvo.getSalvoPositions());
        resultingPositions.retainAll(fleet.getAllPositions());
        return new SalvoResult(resultingPositions, salvo.getSalvoPositions());
    }
}
