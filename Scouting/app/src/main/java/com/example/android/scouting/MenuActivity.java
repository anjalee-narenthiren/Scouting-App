package com.example.android.scouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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




}
