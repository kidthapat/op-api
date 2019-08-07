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
}
