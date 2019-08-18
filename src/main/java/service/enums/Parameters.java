package service.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Parameters {
    LANGUAGE("lang"),
    OPTIONS("options"),
    FORMAT("format");

    private String parameter;
}
