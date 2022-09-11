package capstone.project.Demo_Project.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleRequestDTO {

    @JsonProperty("roleName")
    private String roleName;
}
