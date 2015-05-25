package com.meuuh.dev.chatapp2.views;

import com.meuuh.dev.chatapp2.models.com.parse.Room;

import java.util.List;

/**
 * Created by Edouard on 25/05/2015.
 */
public interface INavigationDrawerView {
    void refreshRooms(List<Room> rooms);
}
