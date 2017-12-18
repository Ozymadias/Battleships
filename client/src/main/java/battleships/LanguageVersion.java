package battleships;

import battleships.logging.LanguageLoadOption;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * LanguageVersion class is used to provide translations all over the GUI client side
 * It accepts LanguageLoadOption as a parameter in constructor to specify with language should be used
 */
public class LanguageVersion {
    private static final String NOT_FOUND = "Did not found string";
    private static final String FILE_EXTENSION = ".properties";
    private Map<LocalizationStringMarker, String> localization;

    /**
     * It takes LanguageLoadOption as a parameter in constructor to specify witch language should be used.
     */
    public LanguageVersion(LanguageLoadOption languageLoadOption) {
        this.localization = LanguageVersionBuilder.build(languageLoadOption);
    }

    /**
     * It takes LocalizationStringMarker enum as a parameter and returns desired String representations of this values.
     */
    public String provideTranslation(LocalizationStringMarker localizationStringMarker) {
        return localization.get(localizationStringMarker).isEmpty() ? NOT_FOUND : localization.get(localizationStringMarker);
    }

    /**
     * LanguageVersionBuilder builds EnumMap of LocalizationStringMarker and Strings.
     */
    private static class LanguageVersionBuilder {

        /**
         * Loads LocalizationStringMarker and String representation from correct *.property file.
         */
        static Map<LocalizationStringMarker, String> build(LanguageLoadOption fileName) {
            Properties properties = new Properties();
            Map<LocalizationStringMarker, String> translationStringEnumMap = new EnumMap<>(LocalizationStringMarker.class);

            try (InputStream is = ClassLoader.getSystemResourceAsStream(fileName + FILE_EXTENSION)) {
                properties.load(is);

                //Calls stream(); set of keys on the property list than collects it to enumMap of LocalizationStringMarker.
                translationStringEnumMap = properties.stringPropertyNames()
                        .stream()
                        .collect(Collectors.toMap(LocalizationStringMarker::valueOf, properties::getProperty,
                                (a, b) -> b, () -> new EnumMap<>(LocalizationStringMarker.class)));

            } catch (IOException e) {
                //TODO:Add logger here when we will have it in common libraries!!!
                e.printStackTrace();
            }
            return translationStringEnumMap;
        }
    }
}