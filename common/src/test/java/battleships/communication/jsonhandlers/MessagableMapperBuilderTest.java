package battleships.communication.jsonhandlers;

import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessagableMapperBuilderTest {

    @Test
    public void whenBuildingMessagableMapperWithVisibilityOfNonPrivateAccessors_expectVisibilityOfGetterIsNonPrivate(){
        MessagableMapper messagableMapper = new MessagableMapperBuilder()
                .withObjectMapper()
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessagableMapper = messagableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessagableMapper.toString()).contains("getter=NON_PRIVATE");
    }

    @Test
    public void whenBuildingMessagableMapperWithVisibilityOfNonPrivateAccessors_expectVisibilityOfSetterIsNonPrivate(){
        MessagableMapper messagableMapper = new MessagableMapperBuilder()
                .withObjectMapper()
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessagableMapper = messagableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessagableMapper.toString()).contains("setter=NON_PRIVATE");
    }

}