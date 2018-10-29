package com.example.rlarl.chat_room.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rlarl.chat_room.Adapter.ChatAdatper;
import com.example.rlarl.chat_room.Data.ChatData;
import com.example.rlarl.chat_room.Data.ChatRoomData;
import com.example.rlarl.chat_room.R;
import com.example.rlarl.chat_room.SharedprefernceUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView lv;
    EditText et;
    Button bt;
    String userName, chatName, res, chatroomlist, chatusername;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ChatAdatper chatadapter;
    ChatData chatData;
    ChatRoomData chatRoomData;
    ArrayList<ChatData> chating;

    private String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        lv = findViewById(R.id.lv1);
        et = findViewById(R.id.et1);
        bt = findViewById(R.id.bt1);

        if (userName != null && chatName != null){
            userName = chatData.getUserName();
            chatName = chatRoomData.getChatRoomlist();
        }
        chatData = new ChatData();
        chating = new ArrayList<>();

        chatroomlist = SharedprefernceUtil.getChatname(getApplicationContext());
        chatusername = SharedprefernceUtil.getUsername(getApplicationContext());

        chatadapter = new ChatAdatper(chating,getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(false);
        lv.setLayoutManager(layoutManager);
        lv.setAdapter(chatadapter);
        bt.setOnClickListener(this);

        //getchat(chatRoomData.getChatRoomlist(),chatData.getUserName());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                // push() -> list type으로 data를 추가
                res = et.getText().toString();
                chatData.setChat(res);
                chatData.setUserName(chatusername);
                chating.add(chatData);
                chatadapter.setList(chating);

                databaseReference.child(chatroomlist).child(chatusername).push().setValue(res);
                et.setText("");
        }
    }
}
