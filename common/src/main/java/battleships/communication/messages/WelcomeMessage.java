package battleships.communication.messages;

import battleships.communication.Messagable;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class WelcomeMessage implements Messagable {

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
