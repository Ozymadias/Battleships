package battleships.communication.messages;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents welcome message that is send from a server to clients when they connect to the server.
 */
public class WelcomeMessage implements Messageable {

  private final String body;

  /**
   * This is a constructor for WelcomeMessage. It is used by Jackson library as a property based creator.
   *
   * @param body string message to be sent
   */
  @JsonCreator
  public WelcomeMessage(
      @JsonProperty("body") final String body) {
    this.body = body;
  }

  String getBody() {
    return this.body;
  }

  /**
   * This is needed for Mockito testing purpose.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof WelcomeMessage)) {
      return false;
    }

    WelcomeMessage other = (WelcomeMessage) obj;
    return body.equals(other.body);
  }

  /**
   * This is needed for Mockito testing purpose.
   */
  @Override
  public int hashCode() {
    return body.hashCode();
  }
}
