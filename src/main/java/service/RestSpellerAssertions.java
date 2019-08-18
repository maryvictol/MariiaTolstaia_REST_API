package service;

import dto.SpellerDto;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class RestSpellerAssertions {
    private SpellerDto[] spellerDtos;

    public RestSpellerAssertions(SpellerDto[] response) {
        this.spellerDtos = response;
    }

    public RestSpellerAssertions verifyWord(int count, String word) {
        assertThat(spellerDtos[count].getWord()).isEqualTo(word);
        return this;
    }

    public RestSpellerAssertions verifyWords(String... words) {
        assertThat(
                stream(spellerDtos).map(SpellerDto::getWord)
                        .collect(toList())
                        .toArray(new String[]{}))
                .isEqualTo(words);
        return this;
    }

    public RestSpellerAssertions verifyErrorCode(int count, int errorCode) {
        assertThat(spellerDtos[count].getCode()).isEqualTo(errorCode);
        return this;
    }

    public RestSpellerAssertions verifyPos(int count, int pos) {
        assertThat(spellerDtos[count].getPos()).isEqualTo(pos);
        return this;
    }

    public RestSpellerAssertions verifyEmptyResponse() {
        assertThat(spellerDtos.length).isEqualTo(0);
        return this;
    }
}
