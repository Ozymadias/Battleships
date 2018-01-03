package battleships.communication;

import java.util.Optional;

public interface Unmarshaller {

  Optional<Messageable> toMessageable(String message);

}
