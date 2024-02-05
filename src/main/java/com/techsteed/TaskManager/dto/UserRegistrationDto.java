package com.techsteed.TaskManager.dto;

import com.techsteed.TaskManager.model.Role;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserRegistrationDto {
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Password should not be empty")
    private String password;


    public UserRegistrationDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserRegistrationDto() {

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
