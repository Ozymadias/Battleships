package battleships.communication;

import battleships.communication.jsonhandlers.JsonMarshaller;
import battleships.communication.jsonhandlers.JsonUnmarshaller;

import java.net.Socket;

/**
 * This is a builder for ClientHandler class.
 */
public class ClientHandlerBuilder {
  private MessageReceiver messageReceiver;
  private MessageSender messageSender;
  private Socket socket;

  /**
   * This method should be used as first in ClientHandlerBuilder chain.
   *
   * @param socket Socket used by MessageReceiverBuilder and MessageSenderBuilder.
   * @return instance of ClientHandlerBuilder.
   */
  public ClientHandlerBuilder setSocket(Socket socket) {
    this.socket = socket;
    return this;
  }

  /**
   * Add message receiver to the builder.
   *
   * @return instance of ClientHandlerBuilder.
   */
  public ClientHandlerBuilder addMessageReceiver() {
    messageReceiver = new MessageReceiverBuilder().addSocket(socket).build();
    return this;
  }

  /**
   * Add message sender to the builder.
   * @return instance of ClientHandlerBuilder.
   */
  public ClientHandlerBuilder addMessageSender() {
    messageSender = new MessageSenderBuilder().addSocket(socket).build();
    return this;
  }

  /**
   * It returns new instance of ClientHandler class.
   * @return new instance of ClientHandler class
   */
  public ClientHandler build() {
    return new ClientHandler(
        messageSender,
        messageReceiver,
        JsonMarshaller.newInstance(),
        JsonUnmarshaller.newInstance());
  }
}
