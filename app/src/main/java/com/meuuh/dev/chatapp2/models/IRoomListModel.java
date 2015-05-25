package com.meuuh.dev.chatapp2.models;

import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.presenters.RoomListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edouard on 22/05/2015.
 */
public interface IRoomListModel {
    void setRoomList(List<Room> rooms);
    List<Room> getRoomList();

    void fetchRooms(final RoomListModel.RoomListModelCallback callback);
    void addRoom(String name);
    void deleteRoom(Room room);
}
