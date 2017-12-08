package battleships;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static battleships.LanguageLoadOption.EN;
import static battleships.LanguageLoadOption.PL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LanguageVersionTest {
    private LanguageVersion languageVersion;

    @DataProvider(name = "englishData")
    private static Object[][] englishTestData() {

        return new Object[][]{
                {Translation.ENGLISH,"English"},
                {Translation.POLISH,"Polski"},
                {Translation.LOG_IN,"Log in"},
                {Translation.PLAYER_NAME,"Player name:"},
                {Translation.RANDOM_SHIPS,"Random ship placement"},
                {Translation.SERVER_IP,"Server ip address:"},
                {Translation.SERVER_PORT,"Server port:"},
        };
    }

    @DataProvider(name = "polishData")
    private static Object[][] polishTestData() {

        return new Object[][]{
                {Translation.ENGLISH,"English"},
                {Translation.POLISH,"Polski"},
                {Translation.LOG_IN,"Zaloguj sie"},
                {Translation.PLAYER_NAME,"Imie gracza:"},
                {Translation.RANDOM_SHIPS,"Losowe rozmieszczenie statkow"},
                {Translation.SERVER_IP,"Adres IP serwera:"},
                {Translation.SERVER_PORT,"Port serwera:"},
        };
    }

    @Test(dataProvider = "englishData")
    public void shouldPassWhenTranslationMapProvidesCorrectTranslationStringInEnglish(Translation translation,String expectedString) throws Exception {
        //Given
        languageVersion = new LanguageVersion(EN);
        //Then
        assertThat(languageVersion.provideTranslation(translation)).isEqualTo(expectedString);
    }

    @Test(dataProvider = "polishData")
    public void shouldPassWhenTranslationMapProvidesCorrectTranslationStringInPolish(Translation translation,String expectedString) throws Exception {
        //Given
        languageVersion = new LanguageVersion(PL);
        //Then
        assertThat(languageVersion.provideTranslation(translation)).isEqualTo(expectedString);
    }
}