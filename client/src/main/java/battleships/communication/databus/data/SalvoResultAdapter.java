package battleships.communication.databus.data;

import battleships.communication.databus.DataType;
import battleships.communication.databus.DataTypeVisitor;
import battleships.communication.messages.SalvoResult;

public class SalvoResultAdapter implements DataType{

    private SalvoResult salvoResult;

    public SalvoResult getSalvoResult() {
        return salvoResult;
    }

    public void setSalvoResult(SalvoResult salvoResult) {
        this.salvoResult = salvoResult;
    }

    @Override
    public void acceptVisitor(DataTypeVisitor messagableVisitor) {
        messagableVisitor.visit(this);
    }

}
