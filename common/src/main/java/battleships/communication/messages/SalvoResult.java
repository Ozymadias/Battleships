package battleships.communication.messages;

import battleships.communication.Messagable;

import java.util.List;

public class SalvoResult implements Messagable {

    private List<Integer> resultList;
    private final List<Integer> salvoPositions;


    public SalvoResult(List<Integer> resultList, List<Integer> salvoPositions) {

        this.resultList = resultList;
        this.salvoPositions = salvoPositions;
    }

    public List<Integer> getResultList() {
        return resultList;
    }

    public List<Integer> getSalvoPositions() {
        return resultList;
    }

    @Override
    public String toString() {
        return resultList.toString() + salvoPositions.toString();
    }
}
