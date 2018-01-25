package battleships.communication.jsonhandlers;

import battleships.communication.MessageSender;
import battleships.communication.Messageable;
import battleships.communication.Unmarshaller;
import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.util.Optional;

/**
 * This class is a Json implementation of Unmarshaller.
 * Its main responsibility is to turn a Json String object
 * into its Messageable representation using MessageableMapper class.
 */
public class JsonUnmarshaller implements Unmarshaller {
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);
  private final MessageableMapper messageableMapper;

  private JsonUnmarshaller(MessageableMapper messageableMapper) {
    this.messageableMapper = messageableMapper;
  }

  /**
   * @param message - Json String to be converted to Messageable object
   * @return Optional of Messageable that is representation of the Json String object.
    Optional can be empty if IO exception is thrown.
   */
  @Override
  public Optional<Messageable> toMessageable(String message) {
    try {
      return Optional.of(this.messageableMapper.readValue(message, Messageable.class));
    } catch (IOException ex) {
      log.error(ex.getMessage());
      return Optional.empty();
    }
  }

  /**
   * This returns a new instance of JsonUnmarshaller.
   * @return a new instance of JsonUnmarshaller
   */
  public static JsonUnmarshaller newInstance() {
    return new JsonUnmarshaller(MessageableMapper.newInstance());
  }
}
