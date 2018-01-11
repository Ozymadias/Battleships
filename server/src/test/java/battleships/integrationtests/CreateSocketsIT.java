package battleships.integrationtests;

import battleships.communication.Server;
import battleships.communication.ServerBuilder;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreateSocketsIT {
  @Test
  public void whenServerIsCreatedOnLocalHostWithFirstFreePort_expectServerReturnsListOfTwoSockets() throws Exception {
    //given
    final String LOCALHOST = "127.0.0.1";
    Server server = Mockito.spy(ServerBuilder.withPort(0).openServerSocket().build());
    //when
    createSocketWithWaitTime(LOCALHOST, server.getServerSocketPort());
    createSocketWithWaitTime(LOCALHOST, server.getServerSocketPort());
    List<Socket> createdSockets = server.createSockets();
    //then
    assertThat(createdSockets.size()).isEqualTo(2);
  }

  private void createSocketWithWaitTime(String IP, int port) {
    new Thread(() ->
    {
      try {
        new Socket(IP, port);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }
}