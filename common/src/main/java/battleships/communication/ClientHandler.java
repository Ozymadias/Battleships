package battleships.communication;

import battleships.communication.jsonHandlers.JsonMarshaller;
import battleships.communication.jsonHandlers.JsonUnmarshaller;
import battleships.communication.jsonHandlers.MessagableMapperBuilder;

import java.util.Optional;

public class ClientHandler {

    private final MessageReceiver messageReceiver;
    private final MessageSender messageSender;
    private final JsonMarshaller jsonMarshaller;
    private final JsonUnmarshaller jsonUnmarshaller;


    public ClientHandler(MessageSender messageSender, MessageReceiver messageReceiver) {
        this.messageSender = messageSender;
        this.messageReceiver = messageReceiver;
        this.jsonMarshaller = new JsonMarshaller(new MessagableMapperBuilder().withObjectMapper().build());
        this.jsonUnmarshaller = new JsonUnmarshaller(new MessagableMapperBuilder().withObjectMapper().build());
    }

    public void sendMessage(Messagable message) {
        String s = jsonMarshaller.toString(message);
        messageSender.sendMessageString(s);
    }

    public Messagable receiveMessage() {
        String s = messageReceiver.receiveMessageString();
        Optional<Messagable> m = jsonUnmarshaller.toMessagable(s);
        return m.get();
    }

}
