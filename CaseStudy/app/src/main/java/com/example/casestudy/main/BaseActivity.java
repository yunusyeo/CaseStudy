package com.example.casestudy.main;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.example.casestudy.constants.Constants;

public class BaseActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public void addFavoriteListItem(String key, Integer value) {
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void removeFavoriteListItem(String key) {
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public Integer retrieveFavoriteListItem(String key) {
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        return sharedPreferences.getInt(key, Constants.ZERO);
    }
}
