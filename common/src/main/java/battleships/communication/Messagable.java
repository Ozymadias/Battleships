package battleships.communication;

import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.communication.messages.WelcomeMessage;
import battleships.ships.Fleet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This interface is just a signature informing
 * that for class implementing it, conversion to/from JSONString
 * should be provided by registering subtype using @JsonSubTypes annotation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes( {
    @JsonSubTypes.Type(value = GoodByeMessage.class, name = "GoodByeMessage"),
    @JsonSubTypes.Type(value = WelcomeMessage.class, name = "WelcomeMessage"),
    @JsonSubTypes.Type(value = Fleet.class, name = "Fleet"),
    @JsonSubTypes.Type(value = Salvo.class, name = "Salvo"),
    @JsonSubTypes.Type(value = SalvoResult.class, name = "SalvoResult")}
)
public interface Messagable {
}
