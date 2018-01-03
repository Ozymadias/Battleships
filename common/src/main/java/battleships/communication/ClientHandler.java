package battleships.communication;

import battleships.communication.jsonhandlers.JsonMarshaller;
import battleships.communication.jsonhandlers.JsonUnmarshaller;
import battleships.communication.jsonhandlers.MessageableMapperBuilder;
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
    this.jsonMarshaller = new JsonMarshaller(new MessageableMapperBuilder()
        .withObjectMapper()
        .build());
    this.jsonUnmarshaller = new JsonUnmarshaller(new MessageableMapperBuilder()
        .withObjectMapper()
        .build());
  }

  public void sendMessage(Messageable message) {
    String s = jsonMarshaller.toString(message);
    messageSender.sendMessageString(s);
  }

  /**
   * Receives message from Receiver than converts it Messageable.
   *
   * @return optional Messageable received by MessageReceiver and converted by jsonUnmarshaller.
   */
  public Messageable receiveMessage() {
    String s = messageReceiver.receiveMessageString();
    Optional<Messageable> m = jsonUnmarshaller.toMessageable(s);
    return m.orElseGet(() -> new WelcomeMessage("Something went wrong"));
  }
}
