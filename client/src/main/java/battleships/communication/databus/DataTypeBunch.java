package battleships.communication.databus;

import battleships.communication.databus.data.SalvoAdapter;

public class DataTypeBunch implements DataType {

    DataType[] dataTypes;

    public DataTypeBunch(){
        dataTypes = new DataType[] { new SalvoAdapter()};
    }

    @Override
    public void acceptVisitor(DataTypeVisitor messagableVisitor) {
        for(DataType dataType : dataTypes) {
            dataType.acceptVisitor(messagableVisitor);
        }
    }
}
