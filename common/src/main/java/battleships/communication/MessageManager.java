package battleships.communication;

import java.util.Optional;

public interface MessageManager {
    public String toStringMessage(Messagable messegable);
    public Optional<Messagable> fromStringMessage(String message);
}
