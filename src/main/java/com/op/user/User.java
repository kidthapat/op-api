package com.op.user;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class User {

    @Id
    private ObjectId _id;

    @Email
    @Length(min = 1, max = 100)
    @NotEmpty
    private String email;

    @Length(min = 1, max = 100)
    private String password;

    public User(){}

    public User(String email){
        this.email = email;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId id) {
        this._id = id;
    }

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
