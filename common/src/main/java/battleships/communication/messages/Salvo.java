package battleships.communication.messages;

import battleships.communication.Messagable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Salvo implements Messagable {
  private final List<Integer> salvoPositions;

  public static Salvo createForPositions(Integer... positions) {
    return new Salvo(Arrays.stream(positions).collect(Collectors.toList()));
  }

  @JsonCreator
  public Salvo(
      @JsonProperty("salvoPositions") List<Integer> salvoPositions) {
    this.salvoPositions = salvoPositions;
  }

  public List<Integer> getSalvoPositions() {
    return salvoPositions;
  }
}
