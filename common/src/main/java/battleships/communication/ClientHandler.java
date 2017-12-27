package battleships.communication;

import battleships.communication.jsonhandlers.JsonMarshaller;
import battleships.communication.jsonhandlers.JsonUnmarshaller;
import battleships.communication.jsonhandlers.MessagableMapperBuilder;
import battleships.communication.messages.WelcomeMessage;

import java.util.Optional;

public class ClientHandler {

  private final MessageReceiver messageReceiver;
  private final MessageSender messageSender;
  private final JsonMarshaller jsonMarshaller;
  private final JsonUnmarshaller jsonUnmarshaller;


  ClientHandler(MessageSender messageSender, MessageReceiver messageReceiver) {
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
    return m.orElseGet(() -> new WelcomeMessage("Something WentWrong"));
  }
}
