package battleships.communication.databus.data;

import battleships.communication.databus.DataType;
import battleships.communication.databus.DataTypeVisitor;
import battleships.game.SalvoCount;

public class SalvoCountAdapter implements DataType {

    private SalvoCount salvoCount;

    public SalvoCount getSalvoCount() {
        return salvoCount;
    }

    public void setSalvoCount(SalvoCount salvoCount) {
        this.salvoCount = salvoCount;
    }

    @Override
    public void acceptVisitor(DataTypeVisitor messagableVisitor) {
        messagableVisitor.visit(this);
    }
}
