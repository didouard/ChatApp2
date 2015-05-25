package com.meuuh.dev.chatapp2.presenters;

import com.meuuh.dev.chatapp2.models.IRoomListModel;
import com.meuuh.dev.chatapp2.models.IRoomModel;
import com.meuuh.dev.chatapp2.models.RoomListModel;
import com.meuuh.dev.chatapp2.models.RoomModel;
import com.meuuh.dev.chatapp2.models.com.parse.Message;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.views.INavigationDrawerView;
import com.meuuh.dev.chatapp2.views.IRoomListView;
import com.meuuh.dev.chatapp2.views.IRoomView;
import com.meuuh.dev.chatapp2.views.NavigationDrawerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edouard on 22/05/2015.
 */
public class RoomListPresenter {
    private static RoomListPresenter self = null;

    private List<Room> rooms;

    private IRoomListView roomListView = null;
    private INavigationDrawerView navigationDrawerView = null;

    private IRoomListModel roomListModel;

    public RoomListPresenter() {
        roomListModel = new RoomListModel();
    }

    public static RoomListPresenter getInstance() {
        if (self == null) {
            self = new RoomListPresenter();
        }
        return self;
    }

    public void setRoomListView(IRoomListView roomListView) {
        this.roomListView = roomListView;
    }

    public void setNavigationDrawerView(INavigationDrawerView navigationDrawerView) {
        this.navigationDrawerView = navigationDrawerView;
    }

    public void createRoom(String name) {
        roomListModel.addRoom(name);
    }

    public List<Room> getRooms() {
        return rooms;
    }


    public void refreshRooms() {
        roomListModel.fetchRooms(new RoomListModel.RoomListModelCallback() {
            @Override
            public void done(List<Room> rooms) {
                self.rooms = rooms;
                if (roomListView != null) roomListView.refreshRooms(rooms);
                if (navigationDrawerView != null) navigationDrawerView.refreshRooms(rooms);
            }
        });
    }
}
