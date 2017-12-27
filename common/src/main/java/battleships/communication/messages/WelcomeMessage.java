package battleships.communication.messages;

import battleships.communication.Messagable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WelcomeMessage implements Messagable {

    private final String body;

    @JsonCreator
    public WelcomeMessage(
            @JsonProperty("body") final String body){
        this.body = body;
    }

    String getBody() {
        return this.body;
    }

}
