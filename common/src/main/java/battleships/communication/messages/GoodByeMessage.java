package battleships.communication.messages;

import battleships.communication.Messegable;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "GoodByeMessage")
public class GoodByeMessage implements Messegable{

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
