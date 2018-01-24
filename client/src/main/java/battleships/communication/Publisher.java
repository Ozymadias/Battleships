package battleships.communication;

import battleships.AlertWithProgressIndicator;

/**
 * Publishers are receiving requests from DataBus.
 */
public interface Publisher {

  /**
   * Implementation of handling request should be provided in this method.
   * @param event event to which answer is expected
   * @param alert instance of Alert which will be showed while waiting for response
   */
  void processRequest(Messageable event, AlertWithProgressIndicator alert);
}
