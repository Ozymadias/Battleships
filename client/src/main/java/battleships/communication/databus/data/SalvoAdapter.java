package battleships.communication.databus.data;

import battleships.communication.databus.DataType;
import battleships.communication.databus.DataTypeVisitor;
import battleships.communication.messages.Salvo;

public class SalvoAdapter implements DataType {

    private Salvo salvo;

    public void setSalvo(Salvo salvo) {
        this.salvo = salvo;
    }

    public Salvo getSalvo() {
        return salvo;
    }

    @Override
    public void acceptVisitor(DataTypeVisitor messagableVisitor) {
        messagableVisitor.visit(this);
    }

}
