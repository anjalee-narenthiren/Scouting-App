package com.example.android.scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.android.scouting.MenuActivity.SHARED_PREFS_KEY;
import static com.example.android.scouting.MenuActivity.matchInfoList;

public class BaseActivity extends AppCompatActivity {
    public final static String SHARED_PREFS_KEY = "match_list";
    public final static String SHARED_PREFS_FIREBASE_KEY = "firebase_key";
    public final static String SHARED_PREFS_IP_KEY = "ip_key";
    public static ArrayList<MatchInfo> matchInfoList;
    public static ArrayList<MatchInfo> matchInfoFilteredList;

    //Set up databse variables
    public static String sIpAddress;
    private static String sMatchKey;
    public static FirebaseDatabase sDatabase;
    public static DatabaseReference sMatchRef;

    public static DatabaseReference sDeletedMatchRef;
    public static ChildEventListener sDeletedMatchListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMyIp();

        sDatabase = FirebaseDatabase.getInstance();
        sMatchRef = sDatabase.getReference().child("match");
        sDeletedMatchRef = sDatabase.getReference().child("deleted_match");

        if (matchInfoList.isEmpty())
            setMatchKey("empty");
    }

    //Implement Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveDataLocal(MatchInfo matchInfo){
        //Append new MatchInfo Object to array
        matchInfoList.add(matchInfo);
        Log.v("ScoutActivity", "Submitted Data. Match: "+matchInfo.match+" Team: "+matchInfo.team);

        //Update the array of matchInfo objects stored in sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(matchInfoList);
        editor.putString(SHARED_PREFS_KEY, json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(SHARED_PREFS_KEY, null);
        Type type = new TypeToken<ArrayList<MatchInfo>>() {}.getType();
        matchInfoList = gson.fromJson(json, type);

        if (matchInfoList == null)
            matchInfoList = new ArrayList<MatchInfo>();
    }

    public void setMatchKey(String input) {
        sMatchKey = input;
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREFS_FIREBASE_KEY, sMatchKey);
        editor.apply();
    }
    public String getMatchKey() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        sMatchKey = sharedPreferences.getString(SHARED_PREFS_FIREBASE_KEY, null);

        if (sMatchKey == null)
            sMatchKey = "empty";

        return sMatchKey;
    }


    public String getMyIp() {
        //Only updates the sIpAddress variable IF it is null - only does it once
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        sIpAddress = sharedPreferences.getString(SHARED_PREFS_IP_KEY, null);

        if (sIpAddress == null) {
            try {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                sIpAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(SHARED_PREFS_IP_KEY, sIpAddress);
                editor.apply();

                Toast.makeText(this, "Set IP:" + sIpAddress, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("BaseActivity", "Set IP failed:");
                Toast.makeText(this, "Set IP failed:", Toast.LENGTH_SHORT).show();

                sIpAddress = null;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(SHARED_PREFS_IP_KEY, sIpAddress);
                editor.apply();
            }
        }

        return sIpAddress;
    }
}
