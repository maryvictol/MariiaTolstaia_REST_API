import static org.assertj.core.api.Assertions.assertThat;
import static service.enums.Options.IGNORE_DIGITS;

import dto.SpellerDto;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import service.RestSpellerAssertions;
import service.RestSpellerSteps;
import service.Uri;
import service.enums.Parameters;

import java.io.IOException;
import java.util.HashMap;

public class TestsForCheckTextMethod {

    @Test(description = "Check incorrect lang parameter")
    void checkIncorrectLangParameter() throws IOException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(Parameters.LANGUAGE.getParameter(), "ch");

        //Check status code 400
        assertThat(new RestSpellerSteps()
                .getStatusCode(Uri.SINGLE_TEXT, parameters, "text"))
                .isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Check multiple results",
            dataProviderClass = DataProviders.class,
            dataProvider = "checkMultipleResponse")
    void checkMultipleResults(String testText, String... expectedWords) throws IOException {
        SpellerDto[][] spellerDtos = new RestSpellerSteps()
                .getSpellerDtoWithoutParams(Uri.SINGLE_TEXT, testText);
        new RestSpellerAssertions(spellerDtos[0]).verifyWords(expectedWords);
    }

    @Test(description = "Check IGNORE_DIGITS option",
            dataProviderClass = DataProviders.class,
            dataProvider = "checkIgnoreDigits")
    void checkIgnoreDigitsOption(String testText, String expectedWord, int pos, int code) throws IOException {
        HashMap<String, Object> parameters = new HashMap<>();

        //Options=DEFAULT: Words with digits aren't returned as error word

        //todo напишу тут, хотя касается впринципе подхода. У тебя есть степы, где и должны прикидваться урлы
        // SINGLE_TEXT в тесте писать не стоит, вынеси на уровень степов. т.е. метод был бы таким
        //getCheckText(testText) ну или просто checkText(testText)
        //getCheckTexts(testText) ну или просто checkTexts(testText)
        //getCheckTextWithParam(testText, params) ну или просто checkTexts(testText)
        //
        SpellerDto[][] spellerDtos = new RestSpellerSteps()
                .getSpellerDtoWithParams(Uri.SINGLE_TEXT, parameters, testText);
        new RestSpellerAssertions(spellerDtos[0])
                .verifyWord(0, expectedWord)
                .verifyErrorCode(0, code)
                .verifyPos(0, pos);

        //Options=IGNORE_DIGITS: Words with digits aren't returned as error word
        parameters.put(Parameters.OPTIONS.getParameter(), IGNORE_DIGITS.getOption());
        spellerDtos = new RestSpellerSteps()
                .getSpellerDtoWithParams(Uri.SINGLE_TEXT, parameters, testText);
        new RestSpellerAssertions(spellerDtos[0])
                .verifyEmptyResponse();
    }
}
