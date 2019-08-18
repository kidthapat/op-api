package com.op.user;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

//    @NotEmpty
//    private String first_name;
//    @NotEmpty
//    private String last_name;
//    @NotEmpty
//    private String phone_on;
//    @NotEmpty
//    private String role;

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

//    public String getFirst_name() {
//        return first_name;
//    }
//
//    public void setFirst_name(String first_name) {
//        this.first_name = first_name;
//    }
//
//    public String getLast_name() {
//        return last_name;
//    }
//
//    public void setLast_name(String last_name) {
//        this.last_name = last_name;
//    }
//
//    public String getPhone_on() {
//        return phone_on;
//    }
//
//    public void setPhone_on(String phone_on) {
//        this.phone_on = phone_on;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
}
