package com.example.rlarl.chat_room.Data;

public class ChatRoomData {
    private String chatRoomlist;
    public String key;

    public ChatRoomData() {
    }

    public ChatRoomData(String chatRoomlist) {
        this.chatRoomlist = chatRoomlist;
    }

    public String getChatRoomlist() {
        return chatRoomlist;
    }

    public void setChatRoomlist(String chatRoomlist) {
        this.chatRoomlist = chatRoomlist;
    }
}
