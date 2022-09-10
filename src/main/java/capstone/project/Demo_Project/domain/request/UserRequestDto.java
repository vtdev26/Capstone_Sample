package capstone.project.Demo_Project.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserRequestDto {
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

    @JsonProperty("updatedDate")
    private Date updatedDate;

}
