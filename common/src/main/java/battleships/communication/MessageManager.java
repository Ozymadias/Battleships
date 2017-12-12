package battleships.communication;

import java.util.Optional;

public interface MessageManager {
    public String toString(Messagable messegable);
    public Optional<Messagable> toMessagable(String message);
}
