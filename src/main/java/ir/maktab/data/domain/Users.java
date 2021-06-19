package ir.maktab.data.domain;

import ir.maktab.data.enums.UserRole;
import ir.maktab.data.enums.UserSituation;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserSituation userSituation;
    @Column
    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;
    @Column
    private Double credit;

    public Users() {
    }

    public Double getCredit() {
        return credit;
    }

    public Users setCredit(Double credit) {
        this.credit = credit;
        return this;
    }

    public UserRole getRole() {
        return userRole;
    }

    public Users setRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Users setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Users setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Users setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserSituation getSituation() {
        return userSituation;
    }

    public Users setSituation(UserSituation userSituation) {
        this.userSituation = userSituation;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Users setDate(Date date) {
        this.date = date;
        return this;
    }
}
