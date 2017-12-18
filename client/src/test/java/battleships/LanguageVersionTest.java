package battleships;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static battleships.logging.LanguageLoadOption.EN;
import static battleships.logging.LanguageLoadOption.PL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LanguageVersionTest {
    private LanguageVersion languageVersion;

    @DataProvider(name = "englishData")
    private static Object[][] englishTestData() {

        return new Object[][]{
                {LocalizationStringMarker.LANG_ENGLISH,"English"},
                {LocalizationStringMarker.LANG_POLISH,"Polski"},
                {LocalizationStringMarker.LOG_IN,"Log in"},
                {LocalizationStringMarker.PLAYER_NAME,"Player name:"},
                {LocalizationStringMarker.RANDOM_SHIPS,"Random ship placement"},
                {LocalizationStringMarker.SERVER_IP,"Server ip address:"},
                {LocalizationStringMarker.SERVER_PORT,"Server port:"},
        };
    }

    @DataProvider(name = "polishData")
    private static Object[][] polishTestData() {

        return new Object[][]{
                {LocalizationStringMarker.LANG_ENGLISH,"English"},
                {LocalizationStringMarker.LANG_POLISH,"Polski"},
                {LocalizationStringMarker.LOG_IN,"Zaloguj sie"},
                {LocalizationStringMarker.PLAYER_NAME,"Imie gracza:"},
                {LocalizationStringMarker.RANDOM_SHIPS,"Losowe rozmieszczenie statkow"},
                {LocalizationStringMarker.SERVER_IP,"Adres IP serwera:"},
                {LocalizationStringMarker.SERVER_PORT,"Port serwera:"},
        };
    }

    @Test(dataProvider = "englishData")
    public void shouldPassWhenTranslationMapProvidesCorrectTranslationStringInEnglish(LocalizationStringMarker localizationStringMarker, String expectedString) throws Exception {
        //Given
        languageVersion = new LanguageVersion(EN);
        //Then
        assertThat(languageVersion.provideTranslation(localizationStringMarker)).isEqualTo(expectedString);
    }

    @Test(dataProvider = "polishData")
    public void shouldPassWhenTranslationMapProvidesCorrectTranslationStringInPolish(LocalizationStringMarker localizationStringMarker, String expectedString) throws Exception {
        //Given
        languageVersion = new LanguageVersion(PL);
        //Then
        assertThat(languageVersion.provideTranslation(localizationStringMarker)).isEqualTo(expectedString);
    }
}