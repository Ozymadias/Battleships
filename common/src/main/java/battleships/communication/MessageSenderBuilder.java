package battleships.communication;


import battleships.logger.BattleshipLog;

import java.io.*;
import java.net.Socket;

public class MessageSenderBuilder {
  private ObjectOutputStream oos;
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);

  public MessageSender build() {
    return new MessageSender(oos);
  }

  public MessageSenderBuilder addSocket(Socket socket) {
    OutputStream os;
    try {
      os = socket.getOutputStream();
      oos = new ObjectOutputStream(os);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return this;
  }
}