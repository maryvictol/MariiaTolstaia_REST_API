package service;

import static io.restassured.RestAssured.given;

import Utils.TestProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Map;

public class RestSpellerService {
    private RequestSpecification REQUEST_SPECIFICATION;

    public RestSpellerService() throws IOException {
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .setBaseUri(TestProperties.getProperty("baseUrl"))
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public Response getResponseWithParams(String uri,
                                          Map<String, Object> parameters,
                                          String... rows) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);

        for (String row : rows) {
            specification.param("text", row);
        }

        return specification.params(parameters)
                .get(uri);
        //todo а можно вот так specification.get(uri, parameters)??
    }

    public Response getResponseWithoutParams(String uri, String... rows) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);
        for (String row : rows) {
            specification.param("text", row);
        }
        return specification.get(uri);
    }
}


