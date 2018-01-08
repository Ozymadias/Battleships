package battleships.communication.databus;

public interface DataType {
    void acceptVisitor(DataTypeVisitor messagableVisitor);
}
