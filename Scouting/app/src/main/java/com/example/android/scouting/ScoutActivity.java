package com.example.android.scouting;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class ScoutActivity extends AppCompatActivity {
    public static ArrayList<MatchInfo> matchInfoList = new ArrayList<MatchInfo>(10);
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

                    matchInfoList.add(new MatchInfo(tournament, match, team, haveAuto, notes));
                    MatchInfo lstResponse = matchInfoList.get(matchInfoList.size()-1);
                    Log.v("ScoutActivity", "Submitted Data. Match:"+lstResponse.match+" Team: "+lstResponse.team);
                    Intent submitMatchInfos = new Intent(ScoutActivity.this, MenuActivity.class);
                    startActivity(submitMatchInfos);

                    Toast.makeText(ScoutActivity.this, "Successful Submit- Match:"+lstResponse.match+" Team: "+lstResponse.team, Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
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
