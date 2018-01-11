package databus;

import databus.data.SalvoModel;
import databus.data.SalvoResultModel;
import databus.data.WelcomeMessageModel;

public interface DataTypeMember {
  void visit(SalvoModel salvo);
  void visit(SalvoResultModel salvoResult);
  void visit(WelcomeMessageModel welcomeMessage);
}
