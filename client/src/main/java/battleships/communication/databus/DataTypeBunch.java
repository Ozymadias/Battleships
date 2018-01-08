package battleships.communication.databus;

import battleships.communication.databus.data.FleetAdapter;
import battleships.communication.databus.data.SalvoAdapter;
import battleships.communication.databus.data.SalvoCountAdapter;
import battleships.communication.databus.data.SalvoResultAdapter;

public class DataTypeBunch implements DataType {

    DataType[] dataTypes;

    public DataTypeBunch(){
        dataTypes = new DataType[] {
                new SalvoAdapter(),
                new SalvoResultAdapter(),
                new SalvoCountAdapter(),
                new FleetAdapter()
        };
    }

    @Override
    public void acceptVisitor(DataTypeVisitor messagableVisitor) {
        for(DataType dataType : dataTypes) {
            dataType.acceptVisitor(messagableVisitor);
        }
    }

}
