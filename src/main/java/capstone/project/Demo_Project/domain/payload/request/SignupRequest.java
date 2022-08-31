package capstone.project.Demo_Project.domain.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Email
    @JsonProperty("email")
    private String email;

    @JsonProperty("roles")
    private Set<String> roles;

    @NotBlank
    @JsonProperty("password")
    private String password;
}
