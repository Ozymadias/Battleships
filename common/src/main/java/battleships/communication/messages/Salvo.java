package battleships.communication.messages;

import battleships.communication.Messagable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Salvo implements Messagable {
    private List<Integer> salvoPositions;

    public Salvo() {
    }

    public static Salvo createForPositions(Integer... positions) {
        return new Salvo(Arrays.stream(positions).collect(Collectors.toList()));
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
