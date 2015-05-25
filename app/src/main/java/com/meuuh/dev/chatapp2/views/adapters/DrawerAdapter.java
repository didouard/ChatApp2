package com.meuuh.dev.chatapp2.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meuuh.dev.chatapp2.R;
import com.meuuh.dev.chatapp2.models.com.parse.Room;
import com.meuuh.dev.chatapp2.navigation.Navigator;
import com.meuuh.dev.chatapp2.presenters.RoomListPresenter;

import java.util.List;

/**
 * Created by Edouard on 25/05/2015.
 */
public class DrawerAdapter extends ArrayAdapter<Room> {
    public final static String EXTRA_ROOM_OBJECTID = "EXTRA_ROOM_OBJECTID";
    final private Navigator navigator;

    public DrawerAdapter(Context context, Navigator navigator, List<Room> rooms) {
        super(context, 0, rooms);
        this.navigator = navigator;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(android.R.layout.simple_list_item_activated_1, parent, false);
            final ViewHolder holder = new ViewHolder();
            holder.tvRoom = (TextView)convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        }
        final Room room = getItem(position);
        final ViewHolder holder = (ViewHolder)convertView.getTag();

        holder.tvRoom.setText(room.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomId = room.getObjectId();
                navigator.navigateToRoom(roomId);
            }
        });
        return convertView;
    }

    final class ViewHolder {
        public TextView tvRoom;
    }

}
