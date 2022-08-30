package capstone.project.Demo_Project.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class UserRequestDto {
    @JsonProperty("userName")
    private String userName;

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

    @JsonProperty("password")
    private String password;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("registerDate")
    private Date registerDate;

    @Column(name = "disable")
    private boolean disable;
}
