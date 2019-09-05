package com.op.role;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "roles")
public class Role {
    @Id
    private ObjectId _id;

    @NotEmpty
    private String name;

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
