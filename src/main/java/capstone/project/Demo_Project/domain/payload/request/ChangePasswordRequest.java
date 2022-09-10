package capstone.project.Demo_Project.domain.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ChangePasswordRequest {
    @NotBlank
    @JsonProperty("oldPassword")
    private String oldPassword;

    @NotBlank
    @JsonProperty("newPassword")
    private String newPassword;
}
