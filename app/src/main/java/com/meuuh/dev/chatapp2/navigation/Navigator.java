package com.meuuh.dev.chatapp2.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.meuuh.dev.chatapp2.R;
import com.meuuh.dev.chatapp2.views.ChatActivity;
import com.meuuh.dev.chatapp2.views.RoomFragment;
import com.meuuh.dev.chatapp2.views.RoomListFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Edouard on 25/05/2015.
 */
@Singleton
public class Navigator {

    private ChatActivity chatActivity;

    @Inject
    public void Navigator() {
        //empty
    }

    public void setChatActivity(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
    }

    public void navigateToRoom(String roomId) {
        FragmentManager fragmentManager = chatActivity.getSupportFragmentManager();
        if (roomId == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, RoomListFragment.newInstance())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, RoomFragment.newInstance(roomId))
                    .commit();
        }
    }
}
