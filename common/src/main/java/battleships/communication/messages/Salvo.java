package battleships.communication.messages;

import battleships.communication.Messagable;

import java.util.List;

public class Salvo implements Messagable {
    private List<Integer> salvoPositions;

    public Salvo() {
    }

    public Salvo(List<Integer> salvoPositions) {
        this.salvoPositions = salvoPositions;
    }

    public void setSalvoPositions(List<Integer> salvoPositions) {
        this.salvoPositions = salvoPositions;
    }

    public List<Integer> getSalvoPositions() {
        return salvoPositions;
    }

    @Override
    public String toString() {
        return salvoPositions.toString();
    }
}
