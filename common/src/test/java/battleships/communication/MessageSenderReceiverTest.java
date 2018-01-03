package battleships.communication;

import org.testng.annotations.Test;

import java.io.*;

import static org.testng.Assert.*;

public class MessageSenderReceiverTest {
  @Test
  public void whenMessageSenderSendsMessageString_expectItCanBeReceivedByMessageReceiver() throws IOException {
    String expectedValue = "test message";
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    MessageSender messageSender = new MessageSender(new ObjectOutputStream(baos));
    messageSender.sendMessageString(expectedValue);
    byte[] byteArray = baos.toByteArray();
    ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
    MessageReceiver messageReceiver = new MessageReceiver(new ObjectInputStream(bais));
    String actualValue = messageReceiver.receiveMessageString();
    assertEquals(actualValue, expectedValue);
  }
}
