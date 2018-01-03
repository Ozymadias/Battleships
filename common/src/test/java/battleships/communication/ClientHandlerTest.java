package battleships.communication;

import battleships.communication.messages.GoodByeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

public class ClientHandlerTest {
  private MessageSender messageSenderMock;
  private MessageReceiver messageReceiverMock;
  private ClientHandler clientHandler;

  @BeforeTest
  public void setUp() throws Exception {
    messageSenderMock = mock(MessageSender.class);
    messageReceiverMock = mock(MessageReceiver.class);
    clientHandler = new ClientHandler(messageSenderMock, messageReceiverMock);
  }

  @Test
  public void whenClientHandlerSendMessage_expectMessageSenderInvokeMethodOnce() throws Exception {
    //when
    clientHandler.sendMessage(new GoodByeMessage("good bye!"));
    //then
    assertThat(mockingDetails(messageSenderMock).getInvocations().size()).isEqualTo(1);
  }

  @Test
  public void whenClientHandlerReceivesMessage_expectMessageReceiverInvokeMethodOnce() throws Exception {
    //when
    when(messageReceiverMock.receiveMessageString()).thenReturn("{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}");
    clientHandler.receiveMessage();
    //then
    assertThat(mockingDetails(messageReceiverMock).getInvocations().size()).isEqualTo(1);
  }
}