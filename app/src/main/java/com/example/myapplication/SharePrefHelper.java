package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefHelper {

    private Context mContext = null;

    private static final String SHARED_PREF_NAME = "Settings";

    private SharedPreferences sharedPreferences = null;

    public SharePrefHelper(Context context){
        mContext = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getGameSize(){
        return sharedPreferences.getString("gameSize", "4");
    }

    public void setGameSize(String size){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("gameSize", size);
        editor.apply();
    }

    public String getStartingWait(){
        return sharedPreferences.getString("startingWait", "5");
    }

    public void setStartingWait(String wait){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("startingWait", wait);
        editor.apply();
    }

    public String getNumberRange(){
        return sharedPreferences.getString("numberRange", "100");
    }

    public void setNumberRange(String numberRange){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("numberRange", numberRange);
        editor.apply();
    }

    public Boolean getTimer(){
        return sharedPreferences.getBoolean("timer", true);
    }

    public void setTimer(Boolean timer){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("timer", timer);
        editor.apply();
    }


}
