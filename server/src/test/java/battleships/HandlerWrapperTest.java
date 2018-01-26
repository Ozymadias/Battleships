package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.messages.WelcomeMessage;
import com.sun.nio.sctp.IllegalReceiveException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HandlerWrapperTest {
  private ClientHandler clientHandlerMock;

  @BeforeMethod
  public void setUp() {
    clientHandlerMock = mock(ClientHandler.class);
  }

  @Test
  public void whenHandlerWrapperReceivesMessage_expectClientHandlerMockReceiveMessageMethodIsRunOnce() throws IOException, ClassNotFoundException {
    //given
    HandlerWrapper handlerWrapper = new HandlerWrapper(clientHandlerMock);
    //when
    handlerWrapper.receiveMessage();
    //then
    verify(clientHandlerMock, times(1)).receiveMessage();
  }

  @Test
  public void whenHandlerWrapperSendsMessage_expectClientHandlerMockSendMessageMethodIsRunOnce() {
    //given
    HandlerWrapper handlerWrapper = new HandlerWrapper(clientHandlerMock);
    //when
    handlerWrapper.sendMessage(new WelcomeMessage("hello"));
    //then
    verify(clientHandlerMock, times(1)).sendMessage(any(WelcomeMessage.class));
  }

  @Test(expectedExceptions = IllegalReceiveException.class)
  public void whenClientHandlerThrowsIOException_expectReceiveMessageMethodToThrowIllegalReceiveException()
      throws IOException, ClassNotFoundException {
    doThrow(new IOException()).when(clientHandlerMock).receiveMessage();
    HandlerWrapper handlerWrapper = new HandlerWrapper(clientHandlerMock);
    handlerWrapper.receiveMessage();
  }
}