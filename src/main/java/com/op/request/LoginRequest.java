package com.op.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty
    @Email
    @Length(min = 1 , max = 100)
    private String email;
    @NotEmpty
    @Length(min = 1 , max = 100)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
