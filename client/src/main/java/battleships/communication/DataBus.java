package battleships.communication;

import battleships.logger.BattleshipLog;

import java.util.HashSet;
import java.util.Set;

public class DataBus {

  private final BattleshipLog log = BattleshipLog.provideLogger(DataBus.class);

  private static final DataBus INSTANCE = new DataBus();

  private Set<Member> listeners = new HashSet<>();
  private Set<Publisher> publishers = new HashSet<>();

  public static DataBus getInstance() {
    return INSTANCE;
  }

  public void publish(Messageable event) {
    log.info("DataBus is publishing " + event.getClass());
    listeners.forEach(listener -> listener.accept(event));
  }

  /**
   * Distribute requested messageable.
   *
   * @param event accepts instance of Messageable as a parameter.
   */
  public void publishRequest(Messageable event) {
    for (Publisher publisher : publishers) {
      Messageable replay = publisher.processRequest(event);
      publish(replay);
    }
  }

  public void subscribeMember(Member member) {
    this.listeners.add(member);
  }

  public void subscribePublisher(Publisher publisher) {
    this.publishers.add(publisher);
  }

  public void unsubscribe(Member member) {
    this.listeners.remove(member);
  }
}
