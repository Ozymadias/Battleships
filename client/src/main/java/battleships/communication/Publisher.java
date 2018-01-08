package battleships.communication;

/**
 * Publishers are receiving requests from DataBus.
 */
public interface Publisher {

  /**
   * Implementation of handling request should be provided in this method.
   * @param event event to which answer is expected
   * @return data as an instance of class implementing Messagable
   */
  Messageable processRequest(Messageable event);
}
