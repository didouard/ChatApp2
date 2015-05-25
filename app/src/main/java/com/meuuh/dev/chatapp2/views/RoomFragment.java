package com.meuuh.dev.chatapp2.views;

/**
 * Created by Edouard on 22/05/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.meuuh.dev.chatapp2.R;
import com.meuuh.dev.chatapp2.models.com.parse.Message;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.presenters.RoomPresenter;
import com.meuuh.dev.chatapp2.views.adapters.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RoomFragment extends Fragment
implements IRoomView, View.OnClickListener {

    private static final String ARG_ROOM_ID = "section_number";

    private Button btSend;
    RoomPresenter mRoomPresenter;
    private EditText etMessage;

    private ListView lvChat;
    private ArrayList<Message> mMessages;
    private RoomAdapter mAdapter;

    private Room room;

    public RoomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        btSend = (Button) rootView.findViewById(R.id.btSend);
        lvChat = (ListView) rootView.findViewById(R.id.lvChat);
        etMessage = (EditText) rootView.findViewById(R.id.etMessage);

        mRoomPresenter = new RoomPresenter(this);

        btSend.setOnClickListener(this);

        mMessages = new ArrayList<Message>();
        mAdapter = new RoomAdapter(this.getActivity(), mRoomPresenter, mMessages);
        lvChat.setAdapter(mAdapter);

        mRoomPresenter.setRoomPosition(getArguments().getInt(ARG_ROOM_ID));

        //Message are refresh by the presenter
        //refreshMessage();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ChatActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_ROOM_ID));
    }

    public static RoomFragment newInstance(String roomId) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROOM_ID, roomId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSend:
                mRoomPresenter.sendMessage(etMessage.getText().toString());
                break;
        }
    }

    @Override
    public void refreshMessage() {
        mRoomPresenter.refreshMessages();
    }

    @Override
    public void refreshMessage(List<Message> messages) {
        mMessages.clear();
        mMessages.addAll(messages);
        mAdapter.notifyDataSetChanged();
        lvChat.invalidate();
    }
}
