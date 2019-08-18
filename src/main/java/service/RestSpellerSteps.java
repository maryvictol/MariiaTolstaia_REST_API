package service;

import com.google.gson.Gson;
import dto.SpellerDto;

import java.io.IOException;
import java.util.Map;

public class RestSpellerSteps {
    public SpellerDto[][] getSpellerDtoWithParams(String uri, Map<String, Object> parameters, String... rows) throws IOException {
        SpellerDto[][] spellerDtos;
        if (uri.equals(Uri.MULTI_TEXTS)) {
            spellerDtos = new Gson().fromJson(new RestSpellerService()
                    .getResponseWithParams(uri, parameters, rows)
                    .getBody().asString(), SpellerDto[][].class);
        } else {
            spellerDtos = new SpellerDto[][]{new Gson().fromJson(new RestSpellerService()
                    .getResponseWithParams(uri, parameters, rows)
                    .getBody().asString(), SpellerDto[].class)};
        }
        return spellerDtos;
    }

    public SpellerDto[][] getSpellerDtoWithoutParams(String uri, String... rows) throws IOException {
        SpellerDto[][] spellerDtos;
        if (uri.equals(Uri.MULTI_TEXTS)) {
            spellerDtos = new Gson().fromJson(new RestSpellerService()
                    .getResponseWithoutParams(uri, rows)
                    .getBody().asString(), SpellerDto[][].class);
        } else {
            spellerDtos = new SpellerDto[][]{new Gson().fromJson(new RestSpellerService()
                    .getResponseWithoutParams(uri, rows)
                    .getBody().asString(), SpellerDto[].class)};
        }
        return spellerDtos;
    }

    public int getStatusCode(String uri, Map<String, Object> params, String line) throws IOException {
        return
                new RestSpellerService()
                        .getResponseWithParams(uri, params, line).getStatusCode();
    }
}
