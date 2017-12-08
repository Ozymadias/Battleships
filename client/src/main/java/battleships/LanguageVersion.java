package battleships;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;

public class LanguageVersion {
    private static final String NOT_FOUND = "Did not found string";
    private static final String FILE_EXTENSION = ".properties";
    private Map<Translation, String> translationMap;

    public LanguageVersion(LanguageLoadOption languageLoadOption) {
        this.translationMap = LanguageVersionBuilder.readFromFile(languageLoadOption.toString());
    }

    public String provideTranslation(Translation translation) {
        return translationMap.get(translation).isEmpty() ? NOT_FOUND : translationMap.get(translation);
    }

    private static class LanguageVersionBuilder {

        private LanguageVersionBuilder() {
        }

        static Map<Translation, String> readFromFile(String fileName) {
            Properties properties = new Properties();
            Map<Translation, String> readerMap = new EnumMap<>(Translation.class);

            try (InputStream is = ClassLoader.getSystemResourceAsStream(fileName + FILE_EXTENSION)) {
                properties.load(is);
                for (final String name : properties.stringPropertyNames()) {
                    readerMap.put(Translation.valueOf(name), properties.getProperty(name));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return readerMap;
        }
    }
}