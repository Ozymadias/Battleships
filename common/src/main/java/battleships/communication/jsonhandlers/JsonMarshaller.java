package battleships.communication.jsonhandlers;

import battleships.communication.Marshaller;
import battleships.communication.Messagable;
import battleships.utils.BattleshipUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonMarshaller implements Marshaller {

    private final MessagableMapper messagableMapper;

    public JsonMarshaller(MessagableMapper messagableMapper) {
        this.messagableMapper = messagableMapper;
    }

    @Override
    public String toString(Messagable messagable) {
        try {
            return this.messagableMapper.writeValueAsString(messagable);
        } catch (JsonProcessingException e) {
            return BattleshipUtils.provideEmptyString();
        }
    }
}
