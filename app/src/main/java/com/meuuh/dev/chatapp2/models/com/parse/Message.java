package com.meuuh.dev.chatapp2.models.com.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Edouard on 22/05/2015.
 */
@ParseClassName("Message")
public class Message extends ParseObject {
    public String getUserId() {
        return getString("userId");
    }

    public String getBody() {
        return getString("body");
    }

    public String getRoomId() {
        return getString("roomId");
    }

    public void setUserId(String userId) {
        put("userId", userId);
    }

    public void setBody(String body) {
        put("body", body);
    }

    public void setRoomId(String roomId) {
        put("roomId", roomId);
    }
}
