package battleships.communication.messages;

import battleships.communication.Messagable;

import java.util.List;

public class SalvoResult implements Messagable {

    private List<Integer> resultList;
    private List<Integer> salvoPositions;

    public SalvoResult() {
    }

    public SalvoResult(List<Integer> resultList, List<Integer> salvoPositions) {

        this.resultList = resultList;
        this.salvoPositions = salvoPositions;
    }

    public void setResultList(List<Integer> resultList) {
        this.resultList = resultList;
    }

    public void setSalvoPositions(List<Integer> salvoPositions) {
        this.salvoPositions = salvoPositions;
    }

    public List<Integer> getResultList() {
        return resultList;
    }

    public List<Integer> getSalvoPositions() {
        return salvoPositions;
    }
}
