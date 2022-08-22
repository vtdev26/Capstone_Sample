package capstone.project.Demo_Project.errorhandling;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@EqualsAndHashCode(callSuper = true)
@Data
public class DemoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5048054610805580510L;

    ErrorType error;
    List<String> errorParams;
    List<ValidationError> validationErrors;
    int status;
    public DemoException(Throwable cause) {
        this(cause, CommonErrorTypes.INTERNAL_SERVER_ERROR, CommonErrorTypes.INTERNAL_SERVER_ERROR.getStatusCode());
    }
    public DemoException(Throwable cause, ErrorType error, String... parameters) {
        this(cause, error, error.getStatusCode(), parameters);
    }
    public DemoException(Throwable cause, ErrorType error, int status, String... parameters) {
        this(cause, error, status, Collections.emptyList(), parameters);
    }
    public DemoException(ErrorType error, String... parameters) {
        this(error, error.getStatusCode(), parameters);
    }
    public DemoException(ErrorType error, int status, String... parameters) {
        this(null, error, status, Collections.emptyList(), parameters);
    }
    public DemoException(List<ValidationError> validationErrors) {
        this(validationErrors, CommonErrorTypes.VALIDATION_ERROR.getStatusCode());
    }
    public DemoException(List<ValidationError> validationErrors, int status) {
        this(null, CommonErrorTypes.VALIDATION_ERROR, status, validationErrors);
    }
    public DemoException(Throwable cause, ErrorType error, int status, List<ValidationError> validationErrors, String... parameters) {
        super(cause);
        this.error = error;
        this.errorParams = Stream.of(parameters).filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        this.validationErrors = validationErrors;
        this.status = status;
    }
}
