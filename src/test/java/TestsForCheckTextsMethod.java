import dto.SpellerDto;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import service.RestSpellerAssertions;
import service.RestSpellerSteps;
import service.Uri;
import service.enums.Parameters;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static service.enums.Options.IGNORE_DIGITS;

public class TestsForCheckTextsMethod {
    @Test(description = "Check incorrect lang parameter")
    void checkIncorrectLangParameter() throws IOException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(Parameters.LANGUAGE.getParameter(), "ch");

        //Check status code 400
        assertThat(new RestSpellerSteps()
                .getStatusCode(Uri.MULTI_TEXTS, parameters, "text"))
                .isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Check multiple results for multilines text",
            dataProviderClass = DataProviders.class,
            dataProvider = "checkMultipleResponseForMultilineText")
    void checkMultipleResults(String[] testText, String... expectedWords) throws IOException {
        SpellerDto[][] spellerDtos = new RestSpellerSteps()
                .getSpellerDtoWithoutParams(Uri.MULTI_TEXTS, testText);
        for (int i=0; i<spellerDtos.length; i++) {
            new RestSpellerAssertions(spellerDtos[i])
                    .verifyWords(expectedWords[i]);
        }
    }

    @Test(description = "Check IGNORE_DIGITS option",
            dataProviderClass = DataProviders.class,
            dataProvider = "checkIgnoreDigits")
    void checkIgnoreDigitsOption(String testText, String expectedWord, int pos, int code) throws IOException {
        HashMap<String, Object> parameters = new HashMap<>();

        //Options=DEFAULT: Words with digits aren't returned as error word
        SpellerDto[][] spellerDtos = new RestSpellerSteps()
                .getSpellerDtoWithParams(Uri.MULTI_TEXTS, parameters, testText);
        for (int i=0; i<spellerDtos.length; i++) {
            new RestSpellerAssertions(spellerDtos[i])
                    .verifyWord(0, expectedWord)
                    .verifyErrorCode(0, code)
                    .verifyPos(0, pos);
        }

        //Options=IGNORE_DIGITS: Words with digits aren't returned as error word
        parameters.put(Parameters.OPTIONS.getParameter(), IGNORE_DIGITS.getOption());
        spellerDtos = new RestSpellerSteps()
                .getSpellerDtoWithParams(Uri.MULTI_TEXTS, parameters, testText);
        for (int i=0; i<spellerDtos.length; i++) {
            new RestSpellerAssertions(spellerDtos[i])
                    .verifyEmptyResponse();
        }
    }
}
