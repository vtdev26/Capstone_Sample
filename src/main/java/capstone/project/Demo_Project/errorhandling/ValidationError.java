package capstone.project.Demo_Project.errorhandling;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationError implements Serializable {
    private static final long serialVersionUID = -8206164921791642959L;

    String fieldName;
    ErrorType errorCode;
}
