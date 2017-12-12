package battleships.communication.messages;

import battleships.communication.Messagable;

public class GoodByeMessage implements Messagable {

    private String body;

    public GoodByeMessage(){
        this.body = "";
    }

    public GoodByeMessage(String body){
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
