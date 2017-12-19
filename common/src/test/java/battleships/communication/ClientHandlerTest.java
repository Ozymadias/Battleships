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

    @BeforeTest
    public void setUp() throws Exception {
        messageSenderMock = mock(MessageSender.class);
        messageReceiverMock = mock(MessageReceiver.class);
    }

    @Test
    public void shouldPassWhenHandlerCorrectlySendsMessage() throws Exception {
        ClientHandler clientHandler = new ClientHandler(messageSenderMock, messageReceiverMock);
        clientHandler.sendMessage(new GoodByeMessage());
        assertThat(mockingDetails(messageSenderMock).getInvocations().size()).isEqualTo(1);
    }

    @Test
    public void shouldPassWhenHandlerCorrectlyRecieveMessage() throws Exception {
        ClientHandler clientHandler = new ClientHandler(messageSenderMock, messageReceiverMock);
        when(messageReceiverMock.receiveMessageString()).thenReturn("{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}");
        clientHandler.receiveMessage();
        assertThat(mockingDetails(messageReceiverMock).getInvocations().size()).isEqualTo(1);
    }
}