package com.op.merchant;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Merchant {
    @Id
    private ObjectId _id;
    @NotEmpty
    private String  address;
    @NotEmpty
    private String  phone_on;
    @Email
    @Length(min = 1, max = 100)
    @NotEmpty
    private String  email;

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_on() {
        return phone_on;
    }

    public void setPhone_on(String phone_on) {
        this.phone_on = phone_on;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
