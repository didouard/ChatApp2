package com.meuuh.dev.chatapp2;

import android.app.Application;

import com.meuuh.dev.chatapp2.models.com.parse.Message;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Edouard on 22/05/2015.
 */
public class ChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Room.class);
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
    }
}
