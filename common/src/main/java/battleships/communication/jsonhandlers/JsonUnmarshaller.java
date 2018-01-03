package battleships.communication.jsonhandlers;

import battleships.communication.Messageable;
import battleships.communication.MessageSender;
import battleships.communication.Unmarshaller;
import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.util.Optional;

public class JsonUnmarshaller implements Unmarshaller {
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);
  private final MessageableMapper messageableMapper;

  public JsonUnmarshaller(MessageableMapper messageableMapper) {
    this.messageableMapper = messageableMapper;
  }

  @Override
  public Optional<Messageable> toMessageable(String message) {
    try {
      return Optional.of(this.messageableMapper.readValue(message, Messageable.class));
    } catch (IOException e) {
      log.error(e.getMessage());
      return Optional.empty();
    }
  }
}
