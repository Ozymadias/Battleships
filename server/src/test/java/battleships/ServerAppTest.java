package battleships;

import org.apache.log4j.Appender;
import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggingEvent;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ServerAppTest {

  private Appender mockAppender = mock(Appender.class);
  @Captor
  private ArgumentCaptor<LoggingEvent> captorLoggingEvent = ArgumentCaptor.forClass(LoggingEvent.class);

  @BeforeClass
  public void setUp(){
    LogManager.getRootLogger().addAppender(mockAppender);
  }

  @AfterClass
  public void tearDown(){
    LogManager.getRootLogger().removeAppender(mockAppender);
  }

  @Test
  public void whenServerAppRunWithWrongPortNumber_expectedLogWithInformationAboutInvalidPortNumber() throws UnsupportedEncodingException {
    System.setProperty("port", "xxx");
    String[] args = new String[0];
    ServerApp.main(args);

    verify(mockAppender).doAppend(captorLoggingEvent.capture());
    LoggingEvent loggingEvent = captorLoggingEvent.getValue();

    assertThat(loggingEvent.getRenderedMessage()).containsPattern("is not valid port number");
  }

}