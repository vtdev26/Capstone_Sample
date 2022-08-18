package capstone.project.Demo_Project.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountRequestDto {
    @JsonProperty("userName")
    private String userName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("identityCard")
    private String identityCard;

    @JsonProperty("image")
    private String image;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("status")
    private Integer status;
}
