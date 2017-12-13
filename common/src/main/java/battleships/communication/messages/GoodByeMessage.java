package battleships.communication.messages;

import battleships.communication.Messagable;
import battleships.utils.BattleshipUtils;

public class GoodByeMessage implements Messagable {

    private String body;

    public GoodByeMessage(){
        this.body = BattleshipUtils.provideEmptyString();
    }

    public GoodByeMessage(final String body){
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

}
