package service;

import com.google.gson.Gson;
import dto.SpellerDto;

import java.io.IOException;
import java.util.Map;

import static service.Uri.*;

public class RestSpellerSteps {
    public SpellerDto[][] checkText(Map<String, Object> parameters,
                                    String... rows) throws IOException {
        return new SpellerDto[][]{new Gson().fromJson(new RestSpellerService()
                .getResponseWithParams(SINGLE_TEXT, parameters, rows)
                .getBody().asString(), SpellerDto[].class)};
    }

    public SpellerDto[][] checkText(String... rows) throws IOException {
        return new SpellerDto[][]{new Gson().fromJson(new RestSpellerService()
                .getResponseWithoutParams(SINGLE_TEXT, rows)
                .getBody().asString(), SpellerDto[].class)};
    }

    public SpellerDto[][] checkTexts(Map<String, Object> parameters,
                                    String... rows) throws IOException {
        return new Gson().fromJson(new RestSpellerService()
                .getResponseWithParams(MULTI_TEXTS, parameters, rows)
                .getBody().asString(), SpellerDto[][].class);
    }

    public SpellerDto[][] checkTexts(String... rows) throws IOException {
        return new Gson().fromJson(new RestSpellerService()
                .getResponseWithoutParams(MULTI_TEXTS, rows)
                .getBody().asString(), SpellerDto[][].class);
    }

    public int getCheckTextStatusCode(Map<String, Object> params, String line) throws IOException {
        return
                new RestSpellerService()
                        .getResponseWithParams(SINGLE_TEXT, params, line).getStatusCode();
    }

    public int getCheckTextsStatusCode(Map<String, Object> params, String line) throws IOException {
        return
                new RestSpellerService()
                        .getResponseWithParams(MULTI_TEXTS, params, line).getStatusCode();
    }
}
