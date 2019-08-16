package com.op.order;

import com.op.merchant.Merchant;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

public class Order {

    @Id
    private ObjectId _id;
    @NotEmpty
    private String  due_date;
    @NotEmpty
    private int     copy;
    @NotEmpty
    private String  status;
    @NotEmpty
    private String  paper;

    private String  cancle;

    public Order(){

    }

    public String get_id() {

        return _id.toHexString();
    }

    public void set_id(ObjectId id) {

        this._id = id;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getCancle() {
        return cancle;
    }

    public void setCancle(String cancle) {
        this.cancle = cancle;
    }
}
