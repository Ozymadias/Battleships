package battleships.communication;

import java.util.List;

public interface MessageManager {

    String toMessage(Messagable messegable);
    List<Messagable> fromMessage(String message);
}
