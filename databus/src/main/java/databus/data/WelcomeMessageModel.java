package databus.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import databus.DataType;
import databus.DataTypeMember;

/**
 * This class represents welcome message that is send from a server to clients when they connect to the server.
 */
public class WelcomeMessageModel implements DataType {

  private final String body;

  /**
   * This is a constructor for WelcomeMessageModel. It is used by Jackson library as a property based creator.
   *
   * @param body string message to be sent
   */
  @JsonCreator
  public WelcomeMessageModel(
      @JsonProperty("body") final String body) {
    this.body = body;
  }

  public String getBody() {
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
    if (!(obj instanceof WelcomeMessageModel)) {
      return false;
    }

    WelcomeMessageModel other = (WelcomeMessageModel) obj;
    return body.equals(other.body);
  }

  /**
   * This is needed for Mockito testing purpose.
   */
  @Override
  public int hashCode() {
    return body.hashCode();
  }

  @Override
  public void acceptMemeber(DataTypeMember dataTypeMember) {
    dataTypeMember.visit(this);
  }
}
