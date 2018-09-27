package com.example.user.kansaidialect.storages;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;

/**
 * Created by user on 2018/05/27.
 */

public class Setting {
    // 設定値 String を保存
    public static void savePlays(Context ctx, String key, int val) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, val);
        editor.commit();
    }

    // 設定値 String を取得
    public static int loadPlays(Context ctx, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return prefs.getInt(key, 0); // 第２引数はkeyが存在しない時に返す初期値
    }

    // 設定値をすべて取得
    public static Map<String, ?> getAllPreferences(Context ctx) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Map<String, ?> map = prefs.getAll();
        Log.d("プリファレンス", map.toString());
        return  map;
    }
}
