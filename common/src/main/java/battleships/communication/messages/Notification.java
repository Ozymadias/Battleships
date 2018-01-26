package battleships.communication.messages;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents notification sent by server to client
 * including information about game flow, such as:
 * one client disconnect, everything is fine, etc.
 */
public class Notification implements Messageable {

  private final FlowState flowState;

  /**
   * This is a constructor for Notification. It is used by Jackson library as a property based creator.
   */
  @JsonCreator
  public Notification(
      @JsonProperty("flowState") FlowState flowState) {
    this.flowState = flowState;
  }

  /**
   * It returns a including information about game flow.
   */
  public FlowState getFlowState() {
    return flowState;
  }

}
