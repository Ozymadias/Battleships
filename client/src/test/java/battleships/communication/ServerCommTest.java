package battleships.communication;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.WelcomeMessage;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class ServerCommTest {

    ClientHandler clientHandler;
    ServerComm serverComm;

    @BeforeMethod
    public void beforeTest(){
        clientHandler = mock(ClientHandler.class);
        serverComm = new ServerComm(clientHandler);
    }

    @Test
    public void givenFleet_whenAcceptingFleetByServerComm_clientHandlerShouldInvokeSendMessage(){
        //given
        Messagable messagable = new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4)));
        //when
        serverComm.accept(messagable);
        //then
        verify(clientHandler, times(1)).sendMessage(messagable);
    }

    @Test
    public void givenSalvo_whenAcceptingSalvoByServerComm_clientHandlerShouldInvokeSendMessage(){
        //given
        Messagable messagable = Salvo.createForPositions(1, 2, 3, 4);
        //when
        serverComm.accept(messagable);
        //then
        verify(clientHandler, times(1)).sendMessage(messagable);
    }

    @Test
    public void givenWelcomeMessage_whenAcceptingWelcomeMessageByServerComm_clientHandlerShouldNotInvokeSendMessage(){
        //given
        Messagable messagable = new WelcomeMessage("hello!");
        //when
        serverComm.accept(messagable);
        //then
        verify(clientHandler, times(0)).sendMessage(messagable);
    }

    @Test
    public void givenSalvo_whenInvokingProcessRequestOnServerComm_clientHandlerShouldInvokeSendMessage(){
        //given
        Messagable messagable = Salvo.createForPositions(1, 2, 3, 4);
        //when
        serverComm.processRequest(messagable);
        //then
        verify(clientHandler, times(1)).sendMessage(messagable);
    }

    @Test
    public void  givenSalvo_whenInvokingProcessRequestOnServerComm_clientHandlerShouldInvokeReceiveMessage(){
        //given
        Messagable messagable = Salvo.createForPositions(1, 2, 3, 4);
        //when
        serverComm.processRequest(messagable);
        //then
        verify(clientHandler, times(1)).receiveMessage();
    }

    @Test
    public void givenServerComm_whenInvokingInit_clientHandlerShouldInvokeReceiveMessage(){
        serverComm.init();
        verify(clientHandler, times(1)).receiveMessage();
    }
}