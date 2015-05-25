package com.meuuh.dev.chatapp2.views;

/**
 * Created by Edouard on 22/05/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.meuuh.dev.chatapp2.R;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.presenters.RoomListPresenter;
import com.meuuh.dev.chatapp2.views.adapters.RoomListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RoomListFragment extends Fragment
implements IRoomListView, View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ListView lvRoomList;
    private Button btCreateRoom;

    RoomListPresenter mRoomListPresenter;
    private List<Room> mRooms;
    private RoomListAdapter mAdapter;

    public RoomListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_list, container, false);

        lvRoomList = (ListView) rootView.findViewById(R.id.lvRoom);
        btCreateRoom = (Button) rootView.findViewById(R.id.btCreateRoom);
        btCreateRoom.setOnClickListener(this);

        mRoomListPresenter = RoomListPresenter.getInstance();
        mRoomListPresenter.setRoomListView(this);

        mRooms = new ArrayList<Room>();
        mAdapter = new RoomListAdapter(getActivity(), mRoomListPresenter, mRooms);
        lvRoomList.setAdapter(mAdapter);
        mRoomListPresenter.refreshRooms();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        String roomName = "Home";
        ((ChatActivity) activity).onSectionAttached(roomName);

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RoomListFragment newInstance() {
        RoomListFragment fragment = new RoomListFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCreateRoom:
                ShowAlertDialogCreateRoom(new ShowAlertDialogCreateRoomCallback() {
                    @Override
                    public void done(String name) {
                        /*mRoomListPresenter.getRooms().size() + 1;
                        FragmentManager fragmentManager = getFragmentManager();*/
                        mRoomListPresenter.createRoom(name);
                    }
                });
                break;
        }
    }

    @Override
    public void refreshRooms(List<Room> rooms) {
        mRooms.clear();
        mRooms.addAll(rooms);
        mAdapter.notifyDataSetChanged();
        lvRoomList.invalidate();
    }

    // Show Dialog for create room

    private interface ShowAlertDialogCreateRoomCallback {
        void done(String name);
    }

    private void ShowAlertDialogCreateRoom(final ShowAlertDialogCreateRoomCallback callback) {
        final EditText input = new EditText(getActivity());
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Create room")
                .setMessage("What's the room name ?")
                .setView(input)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callback.done(input.getText().toString());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

}
