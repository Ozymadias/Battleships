package battleships.communication;

import java.util.Optional;

public interface MessageManager {

    String toMessage(Messagable messegable);
    Optional<Messagable> fromMessage(String message);
}
