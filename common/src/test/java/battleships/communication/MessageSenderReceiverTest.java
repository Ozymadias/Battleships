package battleships.communication;
import org.testng.annotations.Test;
import java.io.*;
import static org.testng.Assert.*;
public class MessageSenderReceiverTest {
    @Test
    public void testIfMessageSendIsReceivedByReceiver()  throws IOException {
        String expectedValue = "test message";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        MessageSender messageSender = new MessageSender(oos);
        messageSender.sendMessageString(expectedValue);
        byte [] byteArray = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MessageReceiver messageReceiver = new MessageReceiver(ois);
        String actualValue = messageReceiver.receiveMessageString();
        assertEquals(actualValue, expectedValue);
    }
}
