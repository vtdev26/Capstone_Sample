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
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found.", 404),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", "Role not found.", 404),
    USER_WITH_NAME_ALREADY_EXISTS("USER_WITH_NAME_ALREADY_EXISTS",
            "User with name already exists", 400),
    PASSWORD_IS_INCORRECT("PASSWORD_IS_INCORRECT", "Password is incorrect", 400);

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
