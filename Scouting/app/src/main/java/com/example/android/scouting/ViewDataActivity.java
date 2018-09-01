package com.example.android.scouting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

public class ViewDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        HorMatchAdapter horMatchAdapter = new HorMatchAdapter(this, ScoutActivity.gScoutResponses);
        ListView horLV = (ListView)findViewById(R.id.data_lv);
        horLV.setAdapter(horMatchAdapter);

    }


}
