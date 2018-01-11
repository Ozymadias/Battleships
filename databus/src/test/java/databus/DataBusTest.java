package databus;

import databus.data.SalvoModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DataBusTest {

  private DataTypeMember member;
  private SalvoModel salvo;

  @BeforeMethod
  public void setUp() {
    member = mock(DataTypeMember.class);
    salvo = mock(SalvoModel.class);
  }

  @Test
  public void whenPublishingSalvo_exceptAcceptMemeberToBeInvokedOnSalvoObject() {
    //given
    DataBus dataBus = DataBus.getInstance();
    dataBus.subscribe(member);
    //when
    dataBus.publish(salvo);
    //then
    verify(salvo).acceptMemeber(member);
  }

  @Test
  public void whenPublishingEvent_exceptUnsubscribedMemberNotToReceiveData() {
    //given
    DataBus dataBus = DataBus.getInstance();
    dataBus.unsubscribe(member);
    //when
    dataBus.publish(salvo);
    //then
    verify(member, times(0)).visit(salvo);
  }

}