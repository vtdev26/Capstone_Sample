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
    public static final String ID = "id";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "user_name";
    public static final String GENDER = "gender";
    public static final String IMAGE = "image";
    public static final String PASSWORD = "password";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String CREATED_DATE = "created_date";
    public static final String UPDATED_DATE = "updated_date";
    public static final String DISABLE = "disable";
    public static final String DISABLE_TRUE = "true";
    public static final String DISABLE_FALSE = "false";

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

    @Column(name = "region")
    private String region;

    @Column(name = "disabled")
    private boolean disabled = false;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}