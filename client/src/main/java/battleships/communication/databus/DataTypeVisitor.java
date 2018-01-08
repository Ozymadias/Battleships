package battleships.communication.databus;

import battleships.communication.databus.data.FleetAdapter;
import battleships.communication.databus.data.SalvoAdapter;
import battleships.communication.databus.data.SalvoCountAdapter;
import battleships.communication.databus.data.SalvoResultAdapter;

//aka member
public interface DataTypeVisitor {
    void visit(SalvoAdapter salvoAdapter);

    void visit(SalvoCountAdapter salvoCountAdapter);

    void visit(SalvoResultAdapter salvoResultAdapter);

    void visit(FleetAdapter fleetAdapter);
}
