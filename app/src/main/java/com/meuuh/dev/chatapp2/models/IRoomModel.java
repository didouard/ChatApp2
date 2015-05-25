package com.meuuh.dev.chatapp2.models;


import com.meuuh.dev.chatapp2.models.com.parse.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edouard on 22/05/2015.
 */
public interface IRoomModel {
    void setName(String name);
    String getName();
    void setId(String id);
    String getId();
    void setMessages(List<Message> messages);
    List<Message> getMessages();
    void setPosition(Integer position);
    Integer getPosition();

    void fetchRoom(final RoomModel.RoomModelFetchRoomCallback callback);
    void saveRoom();
    void addMessage(String message);
    void fetchMessages(final RoomModel.RoomModelFetchMessageCallback callback);
}
