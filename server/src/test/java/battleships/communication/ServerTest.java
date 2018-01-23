package battleships.communication;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Test
public class ServerTest {
  private ServerSocket serverSocket = mock(ServerSocket.class);
  private Server server = new Server(serverSocket);

  public void shouldOnlyCreateTwoSockets() throws IOException {
    // given
    final int thereCanBeOnlyTwo = 2;
    when(serverSocket.accept()).thenReturn(new Socket());
    // when
    List<Socket> sockets = server.createSockets();
    // then
    assertThat(sockets.size()).as("sockets # == players #, that is 2.").isEqualTo(11);
  }

}