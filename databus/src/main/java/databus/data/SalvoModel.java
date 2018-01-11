package databus.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import databus.DataType;
import databus.DataTypeMember;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents SalvoModel message that can be send from a client to a server.
 */
public class SalvoModel implements DataType {
  private final List<Integer> salvoPositions;

  /**
   * @param positions vararg of positions where salvo is shoted
   * @return new instance of SalvoModel object.
   */
  public static SalvoModel createForPositions(Integer... positions) {
    return new SalvoModel(Arrays.stream(positions).collect(Collectors.toList()));
  }

  /**
   * This is a constructor for SalvoModel. It is used by Jackson library as a property based creator.
   *
   * @param salvoPositions list of integers that specifies salvo positions.
   */
  @JsonCreator
  public SalvoModel(
      @JsonProperty("salvoPositions") List<Integer> salvoPositions) {
    this.salvoPositions = salvoPositions;
  }

  /**
   * It returns a list of salvo positions.
   */
  public List<Integer> getSalvoPositions() {
    return salvoPositions;
  }

  @Override
  public void acceptMemeber(DataTypeMember dataTypeMember) {
    dataTypeMember.visit(this);
  }
}
