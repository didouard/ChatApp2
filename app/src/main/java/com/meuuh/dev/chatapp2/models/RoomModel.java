package com.meuuh.dev.chatapp2.models;

import android.util.Log;

import com.meuuh.dev.chatapp2.models.IRoomModel;
import com.meuuh.dev.chatapp2.models.com.parse.Message;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.views.adapters.RoomAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edouard on 22/05/2015.
 */
public class RoomModel implements IRoomModel {
    final RoomModel self = this;
    private Room room;
    List<Message> messages;

    public RoomModel() {
        room = new Room();
        messages = new ArrayList<Message>();
    }

    @Override
    public void setName(String name) {
        room.setName(name);
    }

    @Override
    public String getName() {
        return room.getName();
    }

    @Override
    public void setId(String id) {
        room.setId(id);
    }

    @Override
    public String getId() {
        return room.getObjectId();
    }

    @Override
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void setPosition(Integer position) {
        room.setPosition(position);
    }

    @Override
    public Integer getPosition() {
        return null;
    }

    public interface RoomModelFetchRoomCallback {
        void done(RoomModel room);
    }

    @Override
    public void fetchRoom(final RoomModelFetchRoomCallback callback) {
        ParseQuery<Room> query = ParseQuery.getQuery(Room.class);
        query.setLimit(1);
        query.whereContains("position", room.getPosition().toString());
        query.findInBackground(new FindCallback<Room>() {
            public void done(List<Room> rooms, ParseException e) {
                if (e == null) {
                    self.room = rooms.get(0);
                    callback.done(self);
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void saveRoom() {
        // call callback when saved
    }

    @Override
    public void addMessage(String message) {
        // do parse save    void setId(String id);
    }

    public interface RoomModelFetchMessageCallback {
        public void done(List<Message> messages);
    }

    @Override
    public void fetchMessages(final RoomModelFetchMessageCallback callback) {
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        query.setLimit(50);
        query.whereContains("roomId", room.getObjectId());
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<Message>() {
            public void done(List<Message> messages, ParseException e) {
                if (e == null) {
                    callback.done(messages);
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }
}
