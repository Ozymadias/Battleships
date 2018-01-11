package battleships.communication;

/**
 * Members are receiving messages from the DataBus.
 */
public interface Member {

  /**
   * Implementation of handling received messages should be provided in this method.
   * @param data message delivered to member from DataBus
   */
  void accept(Messageable data);
}
