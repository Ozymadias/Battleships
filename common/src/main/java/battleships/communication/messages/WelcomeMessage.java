package battleships.communication.messages;

import battleships.communication.Messagable;
import battleships.utils.BattleshipUtils;

public class WelcomeMessage implements Messagable {

    private String body;

    public WelcomeMessage(){
        this.body = BattleshipUtils.provideEmptyString();
    }

    public WelcomeMessage(final String body){
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
