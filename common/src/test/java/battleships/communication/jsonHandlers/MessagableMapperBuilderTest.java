package battleships.communication.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessagableMapperBuilderTest {

    @Test
    public void givenObjectMapper_whenBuildingMessagableMapper_visibilityCheckerOfObjectMapperAndVisibilityCheckerOfMessagableMapper_shouldEquals(){
        ObjectMapper objectMapper = new ObjectMapper();
        VisibilityChecker visibilityOfObjectMapper = objectMapper.getVisibilityChecker();
        MessagableMapper messagableMapper = new MessagableMapperBuilder()
                .withObjectMapper(objectMapper)
                .build();
        VisibilityChecker visibilityOfMessagableMapper = messagableMapper.getVisibilityChecker();
        assertThat(visibilityOfObjectMapper).isEqualTo(visibilityOfMessagableMapper);
    }

    @Test
    public void givenObjectMapper_whenBuildingMessagableMapperWithVisibilityOfNonPrivateAccessors_thenVisibilityOfGetterIsNonPrivate(){
        MessagableMapper messagableMapper = new MessagableMapperBuilder()
                .withObjectMapper(new ObjectMapper())
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessagableMapper = messagableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessagableMapper.toString()).contains("getter=NON_PRIVATE");
    }

    @Test
    public void givenObjectMapper_whenBuildingMessagableMapperWithVisibilityOfNonPrivateAccessors_thenVisibilityOfSetterIsNonPrivate(){
        MessagableMapper messagableMapper = new MessagableMapperBuilder()
                .withObjectMapper(new ObjectMapper())
                .buildWithVisibilityOfNonPrivateAccessors();
        VisibilityChecker visibilityOfMessagableMapper = messagableMapper.getVisibilityChecker();
        assertThat(visibilityOfMessagableMapper.toString()).contains("setter=NON_PRIVATE");
    }

}