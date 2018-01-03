package battleships.communication;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
  private Server server;

  @DataProvider(name = "clientPool")
  private static Object[][] clientPool() {

    return new Object[][] {
        {2, true},
        {4, false},
        {0, false}

    };
  }

  @BeforeTest
  private void setUp() throws IOException {
    ServerSocket serverSocket = mock(ServerSocket.class);
    when(serverSocket.accept()).thenReturn(new Socket());
    server = new Server(serverSocket);
  }

  @Test(dataProvider = "clientPool")
  public void whenServerIsCreatingSockets_expectItCreates2Sockets(int count, boolean expectedResult) throws Exception {
    //when
    List<Socket> sockets = server.createSockets();
    int sizeOfSockets = sockets.size();
    //then
    assertThat(sizeOfSockets == count).isEqualTo(expectedResult);
  }


}