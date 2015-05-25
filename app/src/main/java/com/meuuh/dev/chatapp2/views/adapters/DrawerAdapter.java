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
import com.meuuh.dev.chatapp2.presenters.RoomListPresenter;

import java.util.List;

/**
 * Created by Edouard on 25/05/2015.
 */
public class DrawerAdapter extends ArrayAdapter<Room> {
    public final static String EXTRA_ROOM_OBJECTID = "EXTRA_ROOM_OBJECTID";

    public DrawerAdapter(Context context, RoomListPresenter roomListPresenter, List<Room> rooms) {
        super(context, 0, rooms);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_room, parent, false);
            final ViewHolder holder = new ViewHolder();
            holder.ivRoom = (ImageView)convertView.findViewById(R.id.ivRoom);
            holder.tvRoom = (TextView)convertView.findViewById(R.id.tvRoom);
            convertView.setTag(holder);
        }
        Room room = getItem(position);
        final ViewHolder holder = (ViewHolder)convertView.getTag();

        /*Picasso.with(getContext()).load(getProfileUrl(message.getUserId())).into(holder.ivRoom);*/
        holder.tvRoom.setText(room.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra(MainActivity.EXTRA_ROOM_OBJECTID, getItem(position).getObjectId());
                getContext().startActivity(intent);*/
            }
        });
        return convertView;
    }

    final class ViewHolder {
        public ImageView ivRoom;
        public TextView tvRoom;
    }

}
