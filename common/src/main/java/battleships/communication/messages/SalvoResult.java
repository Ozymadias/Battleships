package battleships.communication.messages;

import battleships.communication.Messagable;

import java.util.List;

public class SalvoResult implements Messagable {

    private List<Integer> resultList;

    public SalvoResult(List<Integer> resultList) {


        this.resultList = resultList;
    }

    public List<Integer> getResultList() {
        return resultList;
    }
}
