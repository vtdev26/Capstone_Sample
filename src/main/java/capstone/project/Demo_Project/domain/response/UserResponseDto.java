package capstone.project.Demo_Project.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("address")
    private String address;

    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;

    @JsonProperty("email")
    private String email;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("image")
    private String image;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("createdDate")
    private Date createdDate;

    @Column(name = "disable")
    private boolean disable;
}
