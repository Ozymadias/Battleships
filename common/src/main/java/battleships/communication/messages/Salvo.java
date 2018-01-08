package battleships.communication.messages;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents Salvo message that can be send from a client to a server.
 */
public class Salvo implements Messageable {
  private final List<Integer> salvoPositions;

  /**
   * @param positions vararg of positions where salvo is shoted
   * @return new instance of Salvo object.
   */
  public static Salvo createForPositions(Integer... positions) {
    return new Salvo(Arrays.stream(positions).collect(Collectors.toList()));
  }

  /**
   * This is a constructor for Salvo. It is used by Jackson library as a property based creator.
   *
   * @param salvoPositions list of integers that specifies salvo positions.
   */
  @JsonCreator
  public Salvo(
      @JsonProperty("salvoPositions") List<Integer> salvoPositions) {
    this.salvoPositions = salvoPositions;
  }

  /**
   * It returns a list of salvo positions.
   */
  public List<Integer> getSalvoPositions() {
    return salvoPositions;
  }
}
