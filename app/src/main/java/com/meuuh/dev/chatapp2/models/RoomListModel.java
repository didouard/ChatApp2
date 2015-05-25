package com.meuuh.dev.chatapp2.models;

import android.util.Log;

import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.presenters.RoomListPresenter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edouard on 22/05/2015.
 */
public class RoomListModel implements IRoomListModel {
    List<Room> rooms;

    public RoomListModel() {
        rooms = new ArrayList<Room>();
    }

    @Override
    public void setRoomList(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public List<Room> getRoomList() {
        return rooms;
    }

    @Override
    public void addRoom(String name) {
        Room room = new Room();
        room.setName(name);
        // callback
        room.saveInBackground();
        rooms.add(room);
    }

    @Override
    public void deleteRoom(Room room) {
        rooms.remove(room);
    }

    public interface RoomListModelCallback {
        void done(List<Room> rooms);
    }

    @Override
    public void fetchRooms(final RoomListModelCallback callback) {
        ParseQuery<Room> query = ParseQuery.getQuery(Room.class);
        query.setLimit(100);
        query.orderByAscending("position");
        query.findInBackground(new FindCallback<Room>() {
            @Override
            public void done(List<Room> rooms, ParseException e) {
                if (e == null) {
                    callback.done(rooms);
                } else {
                    Log.d("room", "Error: " + e.getMessage());
                }
            }
        });
    }
}
