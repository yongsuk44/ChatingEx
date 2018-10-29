package com.example.rlarl.chat_room.Data;

public class ChatData {
    private String userName;
    private String chat;


    public ChatData() { }

    public ChatData(String userName, String chat) {
        this.userName = userName;
        this.chat = chat;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
