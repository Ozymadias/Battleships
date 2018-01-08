package battleships.communication.databus;

import battleships.logger.BattleshipLog;

import java.util.HashSet;
import java.util.Set;

/**
 * Class responsible for sending messages (described here as data) among different components
 * without needing them to know about each other.
 * It allows to publish message (as an instance of class implementing Messageable interface).
 * When an object is registered as member it starts to get all of messages.
 * It's a members responsibility to decide about the type of message they want to handle.
 * It also allows to request an answer from objects registered as publishers.
 */
public class DataBus {

  private final BattleshipLog log = BattleshipLog.provideLogger(DataBus.class);

  private static final DataBus INSTANCE = new DataBus();

  private Set<DataTypeVisitor> listeners = new HashSet<>();
  private Set<Publisher> publishers = new HashSet<>();

  /**
   * Returns an instance of DataBus.
   */
  public static DataBus getInstance() {
    return INSTANCE;
  }

  /**
   * Using this method allows to distribute some data along all of members.
   * @param data instance of class implementing Messageable to be published
   */
  public void publish(DataType data) {
    log.info("DataBus is publishing " + data.getClass());
    listeners.forEach(listener -> data.acceptVisitor(listener));
  }

  /**
   * Send requests to all of publishers and then publishes response from them to all of members.
   * @param data instance of class implementing Messageable for which answer is requested
   */
  public void publishRequest(DataType data) {
//    for (Publisher publisher : publishers) {
//      Messageable replay = publisher.processRequest(data);
//      publish(replay);
//    }
  }

  /**
   * Register a member with data-bus.
   * When a member is registered it starts receiving data.
   * @param member instance of a class implementing interface Member to be registered
   */
  public void subscribeMember(DataTypeVisitor member) {
    this.listeners.add(member);
  }

  /**
   * Register a publisher with data-bus.
   * When a publisher is registered, it means that he can be expected to give an answer for request,
   * which is distributed along all members.
   * @param publisher instance of a class implementing interface Publisher to be registered
   */
  public void subscribePublisher(Publisher publisher) {
    this.publishers.add(publisher);
  }

  /**
   * Allows to remove particular object from the list of members.
   * @param member instance of a class implementing interface Member to be unregistered
   */
  public void unsubscribe(DataTypeVisitor member) {
    this.listeners.remove(member);
  }

  /**
   * Allows to remove particular object from the list of publishers.
   * @param publisher instance of a class implementing interface Publisher to be unregistered
   */
  public void unsubscribe(Publisher publisher) {
    this.publishers.remove(publisher);
  }
}
