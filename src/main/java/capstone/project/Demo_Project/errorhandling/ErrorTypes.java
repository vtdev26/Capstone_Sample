package capstone.project.Demo_Project.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ErrorTypes implements ErrorType {
    INVALID_REQUEST("INVALID_REQUEST", "Invalid request", 400),
    ACCOUNT_NOT_FOUND("ACCOUNT_NOT_FOUND", "Account not found.", 404),
    ACCOUNT_WITH_NAME_ALREADY_EXISTS("ACCOUNT_WITH_NAME_ALREADY_EXISTS",
            "Account with name already exists", 400);

    private final static List<ErrorType> VALUES = Collections.unmodifiableList(Arrays.asList(ErrorTypes.values()));
    final String errorCode;
    String errorDetail;
    int statusCode;

    public static ErrorType getByErrorCode(String errorCode) {
        return VALUES.stream().filter(e -> e.getErrorCode().equalsIgnoreCase(errorCode)).findFirst().orElse(CommonErrorTypes.INTERNAL_SERVER_ERROR);
    }

    public static void initialize() {
        ErrorTypeFactory.addTypes(VALUES);
    }
}
