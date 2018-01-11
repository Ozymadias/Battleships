package battleships.communication;

/**
 * This interface is used to convert Messageable object to String object.
 */
public interface Marshaller {

  <T> String writeValueAsString(T value);

}
