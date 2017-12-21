package battleships.communication;

import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private Server server;

    @DataProvider(name = "clientPool")
    private static Object[][] clientPool() {

        return new Object[][]{
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
    public void shouldPassWhenServerReturnsCorrectListOfSockets(int count, boolean expectedResult) throws Exception {
        assertThat(server.createSockets().size() == count).isEqualTo(expectedResult);
    }


}