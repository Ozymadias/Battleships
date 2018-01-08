package battleships.communication;

import java.util.Optional;

/**
 * This interface is used to convert String object to Optional of Messageable object.
 */
public interface Unmarshaller {

  Optional<Messageable> toMessageable(String message);

}
