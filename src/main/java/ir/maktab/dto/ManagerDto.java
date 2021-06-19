package ir.maktab.dto;


import ir.maktab.service.validation.ValidPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ManagerDto {
    private Integer id;
    @NotBlank(message = "userName")
    @Size(min = 2, max = 10, message = "userName")
    private String userName;
    @ValidPassword(message = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public ManagerDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ManagerDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ManagerDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
