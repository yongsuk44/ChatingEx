package com.example.rlarl.chat_room;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rlarl.chat_room.Data.ChatRoomData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedprefernceUtil {
    final static public String APP_PREFERENCE = "app_preference";
    final static public String USERNAME = "user_name";
    final static public String CHATNAME = "chat_name";
    final static public String CHATROOMNUMBER = "chat_roomnumber";

    static private SharedPreferences getAppPreference(Context context){
        if (context != null){
            return context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        }
        return null;
    }
    static public void setChatroomnumber(Context context, String data){
        SharedPreferences dbpref = getAppPreference(context);
        SharedPreferences.Editor editor = dbpref.edit();
        editor.putString(CHATROOMNUMBER, data);
        editor.apply();
    }

    static public String getChatroomnumber(Context context){
        if (context != null){
            SharedPreferences preferences = getAppPreference(context);
            return preferences.getString(CHATROOMNUMBER, "");
        }
        return null;
    }

    static public void setUsername(Context context, String name){
        SharedPreferences dbpref = getAppPreference(context);
        SharedPreferences.Editor editor = dbpref.edit();
        editor.putString(USERNAME, name);
        editor.apply();
    }

    static public void setChatname(Context context, String name){
        SharedPreferences dbpref = getAppPreference(context);
        SharedPreferences.Editor editor = dbpref.edit();
        editor.putString(CHATNAME, name);
        editor.apply();
    }

    static public String getUsername(Context context){
        if (context != null){
            SharedPreferences preferences = getAppPreference(context);
            return preferences.getString(USERNAME, "");
        }
        return null;
    }

    static public String getChatname(Context context){
        if (context != null){
            SharedPreferences preferences = getAppPreference(context);
            return preferences.getString(CHATNAME, "");
        }
        return null;
    }

}
