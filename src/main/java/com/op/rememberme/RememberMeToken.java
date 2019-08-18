package com.op.rememberme;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;

public class RememberMeToken extends PersistentRememberMeToken {
    @Id
    private ObjectId _id;


    public RememberMeToken(ObjectId id, String username, String series, String tokenValue, Date date) {
        super(username, series, tokenValue, date);
        this._id = id;
    }

    public ObjectId get_id() {
        return _id;
    }
}
