package com.op.merchant;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Merchant {
    @Id
    private ObjectId _id;

    @Length(min = 1, max = 100)
    @NotEmpty
    private String name;

    @NotEmpty
    private String  address;

    @NotEmpty
    private String  phoneNo;

    @Email
    @Length(min = 1, max = 100)
    @NotEmpty
    private String  email;

    private String[] imageIds = new String[]{};

    private String imageProfileId;

    public Merchant(){}

    public String get_id() {

        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {

        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getImageIds() {
        return imageIds;
    }

    public void setImageIds(String[] imagePathIds) {
        this.imageIds = imagePathIds;
    }

    public String getImageProfileId() {
        return imageProfileId;
    }

    public void setImageProfileId(String imageProfileId) {
        this.imageProfileId = imageProfileId;
    }
}
