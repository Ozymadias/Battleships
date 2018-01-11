package databus;

import java.util.HashSet;
import java.util.Set;

public class DataBus {

  private static final DataBus INSTANCE = new DataBus();

  private Set<DataTypeMember> members = new HashSet();

  public static DataBus getInstance() {
    return INSTANCE;
  }

  public void subscribe(final DataTypeMember member) {
    this.members.add(member);
  }

  public void unsubscribe(final DataTypeMember member) {
    this.members.remove(member);
  }

  public void publish(final DataType data) {
    members.forEach(member -> data.acceptMemeber(member));
  }

}
