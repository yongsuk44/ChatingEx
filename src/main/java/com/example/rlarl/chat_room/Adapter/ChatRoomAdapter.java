package com.example.rlarl.chat_room.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rlarl.chat_room.Data.ChatRoomData;
import com.example.rlarl.chat_room.R;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.Viewholder>{

    ArrayList<ChatRoomData> list;
    Context context;

    // recyclerview에 clickevent interface
    itemClick itemClick;

    public interface itemClick {
        void itemClick (View view, int postion);
    }
    public void setItemClick(itemClick itemClick){
        this.itemClick = itemClick;
    }
    //  --------------------------------

    public ArrayList<ChatRoomData> getList() {
        return list;
    }

    public ChatRoomAdapter() {

    }
    public void setList(ArrayList<ChatRoomData> list) {
        this.list = list;
        //this.notifyDataSetChanged();
    }

    public ChatRoomAdapter(ArrayList<ChatRoomData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chatlist, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        final ChatRoomData item = list.get(position);
        holder.chatRoomList.setText(item.getChatRoomlist());
        holder.chatRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null){
                    itemClick.itemClick(v, position);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView chatRoomList;

        public Viewholder(View itemView) {
            super(itemView);
            chatRoomList = itemView.findViewById(R.id.row_chatroom_list);
        }

    }
}
