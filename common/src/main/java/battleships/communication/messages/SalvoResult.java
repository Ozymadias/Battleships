package battleships.communication.messages;

import battleships.communication.Messagable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SalvoResult implements Messagable {

    private final List<Integer> resultList;
    private final List<Integer> salvoPositions;

    @JsonCreator
    public SalvoResult(
            @JsonProperty("resultList") List<Integer> resultList,
            @JsonProperty("salvoPositions") List<Integer> salvoPositions) {

        this.resultList = resultList;
        this.salvoPositions = salvoPositions;
    }

    public List<Integer> getResultList() {
        return resultList;
    }

    public List<Integer> getSalvoPositions() {
        return salvoPositions;
    }
}
