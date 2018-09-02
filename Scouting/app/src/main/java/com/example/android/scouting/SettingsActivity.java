package com.example.android.scouting;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

import static com.example.android.scouting.MenuActivity.SHARED_PREFS_KEY;
import static com.example.android.scouting.MenuActivity.matchInfoList;

public class SettingsActivity extends AppCompatActivity {
    public static String userName;
    public static String teamNum;
    public static boolean eraseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = this.getSupportActionBar();

        //Set the action bar back button to look like an up button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // When the home button is pressed, take the user back to the VisualizerActivity
        switch (item.getItemId()) {
            case android.R.id.home:
                updateVariables();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateVariables() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userName = sharedPreferences.getString("user_name", "Aiden");
        teamNum = sharedPreferences.getString("team_num", "5225");
        eraseData = sharedPreferences.getBoolean("erase_data", false);
        Log.v("SettingsActivity", "EraseData"+eraseData);
        if (eraseData)
        {
            Log.v("SettingsActivity", "Erase Data");
            SharedPreferences arrSharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            arrSharedPreferences.edit().remove(SHARED_PREFS_KEY).apply();

            matchInfoList.clear();
            matchInfoList = new ArrayList<MatchInfo>();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("erase_data", false).commit();
            eraseData = false;
        }
        Log.v("SettingsActivity", "User:" + userName + " Team:" + teamNum);
    }
}
