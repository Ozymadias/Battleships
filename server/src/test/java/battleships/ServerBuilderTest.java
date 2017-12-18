package battleships;

import battleships.communication.Server;
import battleships.communication.ServerBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.BindException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class ServerBuilderTest {

//    @Test
//    public void givenServerBuilder_whenSettingPortNumberTo0_thenServerSocketShouldBeBindWithSomeAvailableSocket() throws IOException {
//        Server server = new ServerBuilder().setPort(0).openServerSocket().build();
//        assertTrue(server.serverSocket.isBound());
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void givenServerBuilder_whenSettingPortNumberToNegativeNumber_thenIllegalArgumentExceptionExpected() throws IOException {
//        new ServerBuilder().setPort(-1).openServerSocket().build();
//    }
//
//    @Test(expectedExceptions = BindException.class)
//    public void givenServerBuilder_whenSettingRestrictedPortNumber_thenBindExceptionExpected() throws IOException {
//        new ServerBuilder().setPort(110).openServerSocket().build();
//    }

    @Test
    public void testStart() throws Exception {
//        final ServerSocket serverSocket = mock(ServerSocket.class);
//        serverSocket.setReuseAddress(true);
//        Server testServer = new Server(serverSocket);
//        Socket mockSocket1 = mock(Socket.class);
//        mockSocket1.connect();
//        Socket mockSocket2 = mock(Socket.class);
//        mockSocket1.connect();
//        when(serverSocket.accept()).thenReturn(mockSocket1);
//        when(serverSocket.accept()).thenReturn(mockSocket2);
//        //assertThat(testServer.acceptClients().size()).isEqualTo(2);

    }

    @Test
    public void testAcceptClient() throws Exception {
//        ServerSocket serverSocket = mock(ServerSocket.class);
//        when(serverSocket.accept()).thenReturn()
    }
}