package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.Unmarshaller;

import java.io.IOException;
import java.util.Optional;

class JsonUnmarshaller implements Unmarshaller {

    private final MessagableMapper messagableMapper;

    JsonUnmarshaller(MessagableMapper messagableMapper) {
        this.messagableMapper = messagableMapper;
    }

    @Override
    public Optional<Messagable> toMessagable(String message) {
        try {
            return Optional.of(this.messagableMapper.readValue(message, Messagable.class));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
