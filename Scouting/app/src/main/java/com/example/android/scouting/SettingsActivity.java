package com.example.android.scouting;

import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v14.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import static com.example.android.scouting.BaseActivity.sDatabase;
import static com.example.android.scouting.BaseActivity.sMatchRef;
import static com.example.android.scouting.MenuActivity.SHARED_PREFS_KEY;
import static com.example.android.scouting.MenuActivity.matchInfoList;


public class SettingsActivity extends AppCompatActivity {
    public static String sUserName;
    public static String sTeamNum;
    public static boolean sEraseData, sResyncData, sSignOut;

    public static String teamFilter;
    public static String tournamentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
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
        // When the home button is pressed, take the user back to the PreviousActivity
        switch (item.getItemId()) {
            case android.R.id.home:
                updateSettingsVariables();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateSettingsVariables() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //sUserName = sharedPreferences.getString("user_name", "Aiden");
        //sTeamNum = sharedPreferences.getString("team_num", "5225");
        sSignOut = sharedPreferences.getBoolean("sign_out", false);
        sEraseData = sharedPreferences.getBoolean("erase_data", false);
        sResyncData = sharedPreferences.getBoolean("resync_data", false);

        teamFilter = sharedPreferences.getString("team_filter", "").trim();
        tournamentFilter = sharedPreferences.getString("tournament_filter", "").trim();

        Log.v("SettingsActivity", "General Settings. User: "+sUserName+" Team: "+sTeamNum+" Erase Data? "+sEraseData);
        Log.v("SettingsActivity", "Filter Settings. teamFilter: "+teamFilter+" tournamentFilter: "+tournamentFilter);
        if (sEraseData) {
            Log.v("SettingsActivity", "Erase Data");
            SharedPreferences arrSharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            arrSharedPreferences.edit().remove(SHARED_PREFS_KEY).apply();

            matchInfoList.clear();
            matchInfoList = new ArrayList<MatchInfo>();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("erase_data", false).commit();
            sEraseData = false;
        }
        if (sSignOut) {
            Log.v("SettingsActivity", "Sign out");
            AuthUI.getInstance().signOut(this);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("sign_out", false).commit();
            sSignOut = false;
        }
        if (sResyncData) {
            DataSnapshot dataSnapshot = sDatabase.getReference().;


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("resync_data", false).commit();
            sResyncData = false;
        }
        Log.v("SettingsActivity", "User:" + sUserName + " Team:" + sTeamNum);
    }
}
