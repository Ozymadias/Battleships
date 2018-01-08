package battleships.communication;

import battleships.communication.jsonhandlers.JsonMarshaller;
import battleships.communication.jsonhandlers.JsonUnmarshaller;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientHandlerTest {
  private MessageSender messageSenderMock;
  private MessageReceiver messageReceiverMock;
  private JsonMarshaller jsonMarshaller;
  private JsonUnmarshaller jsonUnmarshaller;
  private ClientHandler clientHandler;

  @BeforeTest
  public void setUp() throws Exception {
    messageSenderMock = mock(MessageSender.class);
    messageReceiverMock = mock(MessageReceiver.class);
    jsonMarshaller = mock(JsonMarshaller.class);
    jsonUnmarshaller = mock(JsonUnmarshaller.class);
    clientHandler = new ClientHandler(messageSenderMock, messageReceiverMock, jsonMarshaller, jsonUnmarshaller);
  }

  @Test
  public void whenClientHandlerSendMessage_expectMessageIsMarshalledAndSend() throws Exception {
    //when
    String jsonWelcomeMessage = "Json {Hello}";
    when(jsonMarshaller.toString(new WelcomeMessage("Hello"))).thenReturn(jsonWelcomeMessage);
    clientHandler.sendMessage(new WelcomeMessage("Hello"));
    //then
    verify(jsonMarshaller).toString(new WelcomeMessage("Hello"));
    verify(messageSenderMock).sendMessageString(jsonWelcomeMessage);
  }

  @Test
  public void whenClientHandlerReceivesMessage_expectMessageIsReceivedAndUnmarshalled() throws Exception {
    //when
    String jsonWelcomeMessage = "Json {Hello}";
    Messageable expectedMessage = new WelcomeMessage("Hello");
    when(messageReceiverMock.receiveMessageString()).thenReturn(jsonWelcomeMessage);
    when(jsonUnmarshaller.toMessageable(jsonWelcomeMessage)).thenReturn(Optional.of(expectedMessage));
    Messageable message = clientHandler.receiveMessage();
    //then
    verify(messageReceiverMock).receiveMessageString();
    verify(jsonUnmarshaller).toMessageable(jsonWelcomeMessage);
    assertThat(message).isEqualTo(expectedMessage);
  }
}