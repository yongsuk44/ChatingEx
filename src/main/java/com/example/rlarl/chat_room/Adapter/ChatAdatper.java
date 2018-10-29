package com.example.rlarl.chat_room.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rlarl.chat_room.Data.ChatData;
import com.example.rlarl.chat_room.GlobalVariables;
import com.example.rlarl.chat_room.R;
import com.example.rlarl.chat_room.SharedprefernceUtil;

import java.util.ArrayList;

public class ChatAdatper extends RecyclerView.Adapter<ChatAdatper.Viewholder>{
    private ArrayList<ChatData> list;
    Context con;
    public ChatAdatper() {
    }

    public ChatAdatper(ArrayList<ChatData> list, Context context) {
        this.list = list;
        this.con = context;
    }

    public ArrayList<ChatData> getList() {
        return list;
    }

    public void setList(ArrayList<ChatData> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ChatData array = list.get(position);

        holder.list_time.setText(GlobalVariables.getTime());
        holder.list_name.setText(array.getUserName());
        holder.list_chat.setText(array.getChat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        public TextView list_name, list_time, list_chat;

        public Viewholder(View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_name);
            list_time = itemView.findViewById(R.id.list_time);
            list_chat = itemView.findViewById(R.id.list_chat);
        }
    }


}
