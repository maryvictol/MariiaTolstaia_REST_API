package service.enums;


import lombok.*;

@Getter
@AllArgsConstructor
public enum Options {
    DEFAULT(0),
    IGNORE_DIGITS(2),
    IGNORE_URLS(4),
    FIND_REPEAT_WORDS(8),
    IGNORE_CAPITALIZATION(512);

    private Integer option;
}
