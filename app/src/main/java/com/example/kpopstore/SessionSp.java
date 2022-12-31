package com.example.kpopstore;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionSp {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    private static  final String PREF_NAME="latihan";
    int private_mode = 0;
    public SessionSp(Context contex){
        this.context = context;
        pref = contex.getSharedPreferences(PREF_NAME, private_mode);
        editor = pref.edit();
    }
    public void setKalimat(String kalimat){
        editor.putString("kalimat", kalimat);
        editor.commit();
    }
    public String getKalimat(){
        return pref.getString("kalimat", null);


    }
}
