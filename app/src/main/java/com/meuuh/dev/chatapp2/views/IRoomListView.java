package com.meuuh.dev.chatapp2.views;

import com.meuuh.dev.chatapp2.models.com.parse.Message;
import com.meuuh.dev.chatapp2.models.com.parse.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edouard on 22/05/2015.
 */
public interface IRoomListView {
    void  refreshRooms(List<Room>rooms);
}
