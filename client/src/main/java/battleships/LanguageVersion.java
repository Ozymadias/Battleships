package battleships;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * LanguageVersion class is used to provide translations of communicates all over the GUI client side
 * It accepts LanguageLoadOption as a parameter in constructor to specify with language should be used
 */
public class LanguageVersion {
    private static final String NOT_FOUND = "Did not found string";
    private static final String FILE_EXTENSION = ".properties";
    private Map<Translation, String> translationMap;

    /**
     * It accepts LanguageLoadOption as a parameter in constructor to specify with language should be used
     * and than uses inner static class to load correct values to inner private enum map.
     */
    public LanguageVersion(LanguageLoadOption languageLoadOption) {
        this.translationMap = LanguageVersionBuilder.readFromFile(languageLoadOption.toString());
    }

    /**
     * It accepts Translation enum as a parameter and uses it to get desired value from inner map of Translation and
     * String representations of this values.
     */
    public String provideTranslation(Translation translation) {
        return translationMap.get(translation).isEmpty() ? NOT_FOUND : translationMap.get(translation);
    }

    /**
     * LanguageVersionBuilder class is used to create EnumMap of Translation and Strings.
     */
    private static class LanguageVersionBuilder {

        /**
         * Loads Translation and String representation that it read from correct *.property file it needs.
         */
        static Map<Translation, String> readFromFile(String fileName) {
            Properties properties = new Properties();
            Map<Translation, String> readerMap = new EnumMap<>(Translation.class);

            try (InputStream is = ClassLoader.getSystemResourceAsStream(fileName + FILE_EXTENSION)) {
                properties.load(is);

                //Calls stream(); set of keys on the property list than collects it to enumMap of Translation.
                readerMap = properties.stringPropertyNames()
                        .stream()
                        .collect(Collectors.toMap(Translation::valueOf, properties::getProperty,
                                (a, b) -> b, () -> new EnumMap<>(Translation.class)));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return readerMap;
        }
    }
}