package ir.maktab.dto;

import ir.maktab.data.enums.UserRole;
import ir.maktab.data.enums.UserSituation;
import ir.maktab.service.validation.ChangePasswordValidation;
import ir.maktab.service.validation.LoginValidation;
import ir.maktab.service.validation.RegisterValidation;
import ir.maktab.service.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class UserDto {
    private Integer id;
    @NotBlank(message = "name", groups = {RegisterValidation.class})
    @Size(min = 2, max = 10, message = "name.size", groups = {RegisterValidation.class})
    private String name;
    @NotBlank(message = "last.name", groups = {RegisterValidation.class})
    @Size(min = 2, max = 15, message = "last.name.size", groups = {RegisterValidation.class})
    private String lastName;
    @Email(groups = {LoginValidation.class, RegisterValidation.class})
    @NotBlank(message = "email", groups = {LoginValidation.class, RegisterValidation.class})
    private String email;
    @ValidPassword(message = "password",groups = {LoginValidation.class, RegisterValidation.class, ChangePasswordValidation.class})
    private String password;
    private UserSituation userSituation;
    private Date date;
    private UserRole userRole;
    private Double credit;

    public UserDto() {
        this.credit = 0.0;
        this.userSituation = UserSituation.New;
    }

    public Integer getId() {
        return id;
    }

    public UserRole getRole() {
        return userRole;
    }

    public UserDto setRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserSituation getSituation() {
        return userSituation;
    }

    public UserDto setSituation(UserSituation userSituation) {
        this.userSituation = userSituation;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public UserDto setDate(Date date) {
        this.date = date;
        return this;
    }

    public Double getCredit() {
        return credit;
    }

    public UserDto setCredit(Double credit) {
        this.credit = credit;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto dto = (UserDto) o;
        return getId().equals(dto.getId()) && getName().equals(dto.getName()) && getLastName().equals(dto.getLastName()) && getEmail().equals(dto.getEmail()) && getPassword().equals(dto.getPassword()) && userRole == dto.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(), getEmail(), getPassword(), userRole);
    }
}
