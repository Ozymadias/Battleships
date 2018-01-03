package battleships.communication.jsonhandlers;

import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageableMapperBuilderTest {

    @Test
    public void whenBuildingMessageableMapperWithVisibilityOfNonPrivateAccessors_expectVisibilityOfGetterIsNonPrivate(){
        MessageableMapper messageableMapper = new MessageableMapperBuilder()
                .withObjectMapper()
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessageableMapper = messageableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessageableMapper.toString()).contains("getter=NON_PRIVATE");
    }

    @Test
    public void whenBuildingMessageableMapperWithVisibilityOfNonPrivateAccessors_expectVisibilityOfSetterIsNonPrivate(){
        MessageableMapper messageableMapper = new MessageableMapperBuilder()
                .withObjectMapper()
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessageableMapper = messageableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessageableMapper.toString()).contains("setter=NON_PRIVATE");
    }

}