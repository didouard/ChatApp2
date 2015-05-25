package com.meuuh.dev.chatapp2.models.com.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Edouard on 22/05/2015.
 */
@ParseClassName("Room")
public class Room extends ParseObject {
    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        return getString("name");
    }

    public void setId(String id) {
        setObjectId(id);
    }

    public String getId() {
        return getObjectId();
    }

    public void setPosition(Integer position) {
        put("position", position);
    }

    public Integer getPosition() {
        return getInt("position");
    }
}
