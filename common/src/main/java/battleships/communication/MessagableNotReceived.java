package battleships.communication;

public class MessagableNotReceived extends RuntimeException {
  public MessagableNotReceived(String message) {
    super(message);
  }
}
