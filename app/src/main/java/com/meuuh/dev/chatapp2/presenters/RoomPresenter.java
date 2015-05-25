package com.meuuh.dev.chatapp2.presenters;

import com.meuuh.dev.chatapp2.models.IRoomModel;
import com.meuuh.dev.chatapp2.models.RoomModel;
import com.meuuh.dev.chatapp2.models.com.parse.Message;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.navigation.Navigator;
import com.meuuh.dev.chatapp2.views.IRoomView;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Edouard on 22/05/2015.
 */
public class RoomPresenter {
    final private RoomPresenter self = this;
    private IRoomView roomView;
    private IRoomModel roomModel;


    public RoomPresenter(IRoomView view) {
        this.roomView = view;
        roomModel = new RoomModel();
    }

    public void setRoomPosition(Integer position) {
        roomModel.setPosition(position);
        fetchRoom();
    }

    public void fetchRoom() {
        roomModel.fetchRoom(new RoomModel.RoomModelFetchRoomCallback() {
            @Override
            public void done(RoomModel roomModel) {
                self.roomModel = roomModel;
                refreshMessages();
            }
        });
    }

    public void sendMessage(String message) {
        roomModel.addMessage(message);
        // Todo : dev un callback quand la room est save
        roomModel.saveRoom();
    }

    public void refreshMessages() {
        roomModel.fetchMessages(new RoomModel.RoomModelFetchMessageCallback() {
            @Override
            public void done(List<Message> messages) {
                roomView.refreshMessage(messages);
            }
        });
    }
}
