package capstone.project.Demo_Project.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponseDto {
    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("roleName")
    private String roleName;
}
