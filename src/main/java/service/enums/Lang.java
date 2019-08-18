package service.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Lang {
    RUSSIAN("ru"),
    UKRAINIAN("uk"),
    ENGLISH("en");

    private String lang;
}
