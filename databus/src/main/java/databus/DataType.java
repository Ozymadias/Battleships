package databus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import databus.data.SalvoModel;
import databus.data.SalvoResultModel;
import databus.data.WelcomeMessageModel;

/**
 * This interface is just a signature informing
 * that for class implementing it, conversion to/from JSONString
 * should be provided by registering subtype using @JsonSubTypes annotation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = WelcomeMessageModel.class, name = "WelcomeMessageModel"),
    @JsonSubTypes.Type(value = SalvoModel.class, name = "SalvoModel"),
    @JsonSubTypes.Type(value = SalvoResultModel.class, name = "SalvoResultModel")}
)
public interface DataType {
  void acceptMemeber(DataTypeMember dataTypeMember);
}
