package battleships.communication.jsonhandlers;

import battleships.communication.Messagable;
import battleships.communication.MessageSender;
import battleships.communication.Unmarshaller;
import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.util.Optional;

public class JsonUnmarshaller implements Unmarshaller {
    private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);
    private final MessagableMapper messagableMapper;

    public JsonUnmarshaller(MessagableMapper messagableMapper) {
        this.messagableMapper = messagableMapper;
    }

    @Override
    public Optional<Messagable> toMessagable(String message) {
        try {
            return Optional.of(this.messagableMapper.readValue(message, Messagable.class));
        } catch (IOException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }
}
