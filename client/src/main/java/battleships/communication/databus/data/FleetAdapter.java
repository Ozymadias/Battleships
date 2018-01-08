package battleships.communication.databus.data;

import battleships.communication.databus.DataType;
import battleships.communication.databus.DataTypeVisitor;
import battleships.ships.Fleet;

public class FleetAdapter implements DataType {

    private Fleet fleet;

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

    @Override
    public void acceptVisitor(DataTypeVisitor messagableVisitor) {
        messagableVisitor.visit(this);
    }

}
