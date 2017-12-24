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

    public void publish(Messagable event){
        log.info("DataBus is publishing " + event.getClass());
        listeners.forEach(listener -> listener.accept(event));
        //if is an instance of Fleet or Salvo -> to odbierać powinien server
        //if is an instance of SalvoFromEnemy -> to obierać powinnien PlayerBoardViewController
        //if is an instance of SalvoResult -> to odbierać powninien OpponentBoardViewConroller
    }

    public void sendRequest(Messagable event){
        for(Publisher publisher : publishers){
            Messagable replay = publisher.processRequest(event);
            publish(replay);
        }
    }

    public void subscribeMember(Member member){
        this.listeners.add(member);
    }

    public void subscribePublisher(Publisher publisher){
        this.publishers.add(publisher);
    }

    public void unsubscribe(Member member){
        this.listeners.remove(member);
    }
}
