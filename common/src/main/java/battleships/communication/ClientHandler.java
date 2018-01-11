package battleships.communication;

import battleships.communication.jsonhandlers.JsonMarshaller;
import battleships.communication.jsonhandlers.JsonUnmarshaller;

import java.util.Optional;

/**
 * This class is used by a server and a client to send and receive messages.
 */
public class ClientHandler {

  private final MessageReceiver messageReceiver;
  private final MessageSender messageSender;
  private final JsonMarshaller jsonMarshaller;
  private final JsonUnmarshaller jsonUnmarshaller;

  ClientHandler(MessageSender messageSender,
                MessageReceiver messageReceiver,
                JsonMarshaller jsonMarshaller,
                JsonUnmarshaller jsonUnmarshaller
                ) {
    this.messageSender = messageSender;
    this.messageReceiver = messageReceiver;
    this.jsonMarshaller = jsonMarshaller;
    this.jsonUnmarshaller = jsonUnmarshaller;
  }

  /**
   * It first converts message to the Json format and then send it to receiver.
   *
   * @param message Messageable object to be converted to Json format and then sent by message sender.
   */
  public void sendMessage(Messageable message) {
    String messageString = jsonMarshaller.writeValueAsString(message);
    messageSender.sendMessageString(messageString);
  }

  /**
   * Receives message from Receiver than converts it Messageable.
   *
   * @return optional Messageable received by MessageReceiver and converted by jsonUnmarshaller.
   */
  public Messageable receiveMessage() {
    String messageString = messageReceiver.receiveMessageString();
    Optional<Messageable> messageableOptional = jsonUnmarshaller.readValue(messageString, Messageable.class);
    Messageable messageable = messageableOptional.get();
    return messageable;
  }
}
