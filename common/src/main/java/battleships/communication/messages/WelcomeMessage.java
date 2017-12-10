package battleships.communication.messages;

import battleships.communication.Messegable;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "WelcomeMessage")
public class WelcomeMessage implements Messegable{

    private String body;

    public WelcomeMessage(){
        this.body = "";
    }

    public WelcomeMessage(String body){
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
