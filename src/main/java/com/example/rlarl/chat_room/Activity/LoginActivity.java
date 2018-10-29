package com.example.rlarl.chat_room.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rlarl.chat_room.Adapter.ChatAdatper;
import com.example.rlarl.chat_room.Adapter.ChatRoomAdapter;
import com.example.rlarl.chat_room.Data.ChatData;
import com.example.rlarl.chat_room.Data.ChatRoomData;
import com.example.rlarl.chat_room.GlobalVariables;
import com.example.rlarl.chat_room.R;
import com.example.rlarl.chat_room.SharedprefernceUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView chatroom_List;
    EditText chatroom_Name, chatroom_Username;
    Button chatroom_Create;

    ChatRoomAdapter chatRoomAdapter;
    ArrayList<ChatRoomData> listchatName;
    ArrayList<ChatData> listuserName;
    ChatData chatData;
    ChatRoomData chatRoomData;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    GlobalVariables globalVariables;
    final static String TAG = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        getDatabase();
        itmeClick();
    }

    public void init(){
        chatroom_List = findViewById(R.id.chatroom_list);
        chatroom_Name = findViewById(R.id.chatroom_name);
        chatroom_Username = findViewById(R.id.chatroom_username);
        chatroom_Create = findViewById(R.id.chatroom_create);
        chatroom_Create.setOnClickListener(this);

        listchatName = new ArrayList<>();
        listuserName = new ArrayList<>();
        chatRoomData = new ChatRoomData();
        chatData = new ChatData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(false);
        chatroom_List.setLayoutManager(layoutManager);
        chatRoomAdapter = new ChatRoomAdapter(listchatName,getApplicationContext());
        chatroom_List.setAdapter(chatRoomAdapter);
    }

    public void itmeClick(){
        chatRoomAdapter.setItemClick(new ChatRoomAdapter.itemClick() {
            @Override
            public void itemClick(View view, int postion) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("list",postion);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.chatroom_create:
                chatRoomData.setChatRoomlist(chatroom_Name.getText().toString());
                chatData.setUserName(chatroom_Username.getText().toString());
                SharedprefernceUtil.setChatname(getApplicationContext(), chatroom_Name.getText().toString());
                SharedprefernceUtil.setUsername(getApplicationContext(), chatroom_Username.getText().toString());
                if (chatroom_Name.length() == 0 && chatroom_Username.length() == 0){
                    Log.e(TAG,"edit값이 없음");
                }else{
                    listchatName.add(chatRoomData);
                    listuserName.add(chatData);
                    chatRoomAdapter.setList(listchatName);
                }
                chatroom_Name.setText("");
                chatroom_Username.setText("");

                setDatabase(chatRoomData.getChatRoomlist(), chatData.getUserName());
                break;
        }
    }
    public void setDatabase(String chatroomname, String username){
        databaseReference.child(chatroomname).child(username).push().setValue("");
    }

    public void getDatabase(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String key = snapshot.getKey();
                    chatRoomData = snapshot.getValue(ChatRoomData.class);
                    chatRoomData.key = key;

                }
                chatRoomAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"DB 읽기 실패");
            }
        });
    }
}
