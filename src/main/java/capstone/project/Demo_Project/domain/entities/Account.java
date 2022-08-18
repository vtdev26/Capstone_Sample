package capstone.project.Demo_Project.domain.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "address")
    @NotEmpty(message = "{account.address.empty}")
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "email")
    @NotEmpty(message = "{account.email.empty}")
    private String email;

    @Column(name = "full_name")
    @NotEmpty(message = "{account.fullName.empty}")
    private String fullName;

    @Column(name = "gender")
    @NotEmpty(message = "{account.gender.empty}")
    @Pattern(regexp = "M|F", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String gender;

    @Column(name = "identity_card")
    @NotEmpty(message = "{account.identityCard.empty}")
    private String identityCard;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    @NotEmpty(message = "{account.password.empty}")
    private String password;

    @Column(name = "phone_number")
    @NotEmpty(message = "{account.phoneNumber.empty}")
    private String phoneNumber;

    @Column(name = "register_date", columnDefinition = "DATE")
    private Date registerDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "username", unique = true, nullable = false)
    @NotEmpty(message = "{account.userName.empty}")
    private String userName;
}