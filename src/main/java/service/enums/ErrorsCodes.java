package service.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorsCodes {
    ERROR_UNKNOWN_WORD(1),
	ERROR_REPEAT_WORD(2),
	ERROR_CAPITALIZATION(3),
	ERROR_TOO_MANY_ERRORS(4);

    private int code;
}
