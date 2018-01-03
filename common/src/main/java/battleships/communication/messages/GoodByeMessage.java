package battleships.communication.messages;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GoodByeMessage implements Messageable {

  private final String body;

  @JsonCreator
  public GoodByeMessage(
      @JsonProperty("body") final String body) {
    this.body = body;
  }

  String getBody() {
    return this.body;
  }

}
