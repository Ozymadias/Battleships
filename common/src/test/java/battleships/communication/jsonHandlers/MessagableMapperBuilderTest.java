package battleships.communication.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessagableMapperBuilderTest {

    @Test
    public void givenObjectMapper_whenBuildingMessagableMapperWithVisibilityOfNonPrivateAccessors_thenVisibilityOfGetterIsNonPrivate(){
        MessagableMapper messagableMapper = new MessagableMapperBuilder()
                .withObjectMapper()
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessagableMapper = messagableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessagableMapper.toString()).contains("getter=NON_PRIVATE");
    }

    @Test
    public void givenObjectMapper_whenBuildingMessagableMapperWithVisibilityOfNonPrivateAccessors_thenVisibilityOfSetterIsNonPrivate(){
        MessagableMapper messagableMapper = new MessagableMapperBuilder()
                .withObjectMapper()
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessagableMapper = messagableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessagableMapper.toString()).contains("setter=NON_PRIVATE");
    }

}