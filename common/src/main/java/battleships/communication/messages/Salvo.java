package battleships.communication.messages;

import battleships.communication.Messagable;

import java.util.List;

public class Salvo implements Messagable{
    private List<Integer> salvoPositions;

    public Salvo(List<Integer> salvoPositions) {
        this.salvoPositions = salvoPositions;
    }

}
