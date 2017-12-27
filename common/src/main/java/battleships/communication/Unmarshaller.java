package battleships.communication;

import java.util.Optional;

public interface Unmarshaller {

  public Optional<Messagable> toMessagable(String message);

}
