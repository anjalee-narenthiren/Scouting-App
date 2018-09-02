package com.example.android.scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MenuActivity extends BaseActivity {

    public final static String SHARED_PREFS_KEY = "match list";
    public static ArrayList<MatchInfo> matchInfoList;
    public static ArrayList<MatchInfo> matchInfoFilteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        loadData(); //update the matchInfoList

        Button scout_bttn = findViewById(R.id.scout_bttn);
        scout_bttn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.v("MenuActivity", "Btn Pressed - Enter ScoutActivity");
                    Intent openScoutActivity = new Intent(MenuActivity.this, ScoutActivity.class);
                    startActivity(openScoutActivity);
                }
            }
        );

        Button view_data_bttn = findViewById(R.id.view_data_bttn);
        view_data_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.v("MenuActivity", "Btn Pressed - Enter ViewDataActivity");
                Intent openViewDataActivity = new Intent(MenuActivity.this, ViewDataActivity.class);
                startActivity(openViewDataActivity);
            }
        });

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


}
