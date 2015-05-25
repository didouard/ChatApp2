package com.meuuh.dev.chatapp2.presenters;

import android.app.Activity;
import android.view.View;

/**
 * Created by Edouard on 25/05/2015.
 */
public class ChatPresenter {

    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getRoomId(int position) {
        return "";
    }
}
