package capstone.project.Demo_Project.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {
    private static final String ID = "id";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String EMAIL = "email";
    private static final String USER_NAME = "user_name";
    private static final String GENDER = "gender";
    private static final String IMAGE = "image";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String REGISTER_DATE = "register_date";
    private static final String DISABLE = "disable";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "gender")
    private String gender;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "register_date", columnDefinition = "DATE")
    private Date registerDate;

    @Column(name = "disable")
    private boolean disable = false;

    @Column(name = "status")
    private String status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String userName, String email, String password) {
        this.username = userName;
        this.email = email;
        this.password = password;
    }
}