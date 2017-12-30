package battleships.communication;

import java.util.Optional;

public interface Unmarshaller {

  Optional<Messagable> toMessagable(String message);

}
