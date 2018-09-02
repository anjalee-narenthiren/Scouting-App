package com.example.android.scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.android.scouting.MenuActivity.SHARED_PREFS_KEY;
import static com.example.android.scouting.MenuActivity.matchInfoList;

import static java.lang.Integer.parseInt;

public class ScoutActivity extends BaseActivity {
    //public static ArrayList<MatchInfo> matchInfoList = new ArrayList<MatchInfo>(10);
    public static final SimpleDateFormat MONTH_DAY_FORMAT = new SimpleDateFormat("MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set Views
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout);

        //Initialize Variables
        //EditText tournament = findViewById(R.id.scout_tournament_et);
        EditText match = findViewById(R.id.scout_match_et);
        EditText team = findViewById(R.id.scout_team_et);
        CheckBox autoCB = findViewById(R.id.scout_auto_cb);
        EditText otherNotes = findViewById(R.id.scout_notes_et);

        //Handle Submit Button Press
        Button submit_bttn = findViewById(R.id.scout_submit_bttn);
        submit_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isEmpty(R.id.scout_match_et) || isEmpty(R.id.scout_team_et) ) {
                    Toast.makeText(ScoutActivity.this, "Enter match & team number", Toast.LENGTH_SHORT).show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    String tournament = MONTH_DAY_FORMAT.format(calendar.getTime());
                    int match = ETToInt(R.id.scout_match_et);
                    int team = ETToInt(R.id.scout_team_et);
                    boolean haveAuto = ((CheckBox)findViewById(R.id.scout_auto_cb)).isChecked();
                    String notes = ETToString(R.id.scout_notes_et);

                    MatchInfo newMatchInfo = new MatchInfo(tournament, match, team, haveAuto, notes);
                    sMatchRef.push().setValue(newMatchInfo); //save new matchInfo to Firebase
                    saveDataLocal(newMatchInfo); //save matchInfoList Locally

                    Toast.makeText(ScoutActivity.this, "Successful Submit- Match: "+newMatchInfo.match+" Team: "+newMatchInfo.team, Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }

    public void saveDataLocal(MatchInfo matchInfo){
        //Append new MatchInfo Object to array
        matchInfoList.add(matchInfo);
        Log.v("ScoutActivity", "Submitted Data. Match: "+matchInfo.match+" Team: "+matchInfo.team);
        Intent submitMatchInfos = new Intent(ScoutActivity.this, MenuActivity.class);
        startActivity(submitMatchInfos);

        //Update the array of matchInfo objects stored in sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(matchInfoList);
        editor.putString(SHARED_PREFS_KEY, json);
        editor.apply();
    }

    private boolean isEmpty(int id)
    {
        EditText et = (EditText)findViewById(id);
        if (et.getText().toString().trim().length() > 0)
            return false;
        else
            return true;
    }
    private boolean isEmpty(EditText et)
    {
        if (et.getText().toString().trim().length() > 0)
            return false;
        else
            return true;
    }

    public String ETToString(int id)
    {
        EditText et = (EditText)findViewById(id);
        return et.getText().toString().trim();
    }

    public int ETToInt(int id)
    {
        return (parseInt(ETToString(id)));
    }
}
