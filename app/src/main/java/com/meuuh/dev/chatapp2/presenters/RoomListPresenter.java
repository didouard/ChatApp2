package com.meuuh.dev.chatapp2.presenters;

import com.meuuh.dev.chatapp2.models.IRoomListModel;
import com.meuuh.dev.chatapp2.models.IRoomModel;
import com.meuuh.dev.chatapp2.models.RoomListModel;
import com.meuuh.dev.chatapp2.models.RoomModel;
import com.meuuh.dev.chatapp2.models.com.parse.Message;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.views.IRoomListView;
import com.meuuh.dev.chatapp2.views.IRoomView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edouard on 22/05/2015.
 */
public class RoomListPresenter {
    private static RoomListPresenter self = null;
    private IRoomListView roomListView;
    private IRoomListModel roomListModel;
    private List<Room> rooms;

    public RoomListPresenter(IRoomListView view) {
        this.roomListView = view;
        roomListModel = new RoomListModel();
    }

    public static RoomListPresenter getInstance(IRoomListView view) {
        if (self == null) {
            if (view == null) return null;
            self = new RoomListPresenter(view);
        }
        return self;
    }

    public void createRoom(String name) {
        roomListModel.addRoom(name);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public interface RoomListPresenterCallback {
        void done(List<Room> rooms);
    }

    public void refreshRoom(final RoomListPresenterCallback callback) {
        roomListModel.fetchRooms(new RoomListModel.RoomListModelCallback() {
            @Override
            public void done(List<Room> rooms) {
                self.rooms = rooms;
                callback.done(rooms);
            }
        });
    }
}
