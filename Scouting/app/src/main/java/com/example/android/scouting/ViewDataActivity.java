package com.example.android.scouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import com.kelin.scrollablepanel.library.ScrollablePanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.android.scouting.MenuActivity.matchInfoList;

public class ViewDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        final ScrollablePanel scrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
        //final TestPanelAdapter scrollablePanelAdapter = new TestPanelAdapter();
        final ScrollablePanelAdapter scrollablePanelAdapter = new ScrollablePanelAdapter();
        setData(scrollablePanelAdapter);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
    }

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

    private void setData(ScrollablePanelAdapter scrollablePanelAdapter) {
        List<String> descriptionInfoList = new ArrayList();
        descriptionInfoList.add("Tournament");
        descriptionInfoList.add("Match");
        descriptionInfoList.add("Team");
        descriptionInfoList.add("Auto");
        descriptionInfoList.add("Notes");
        scrollablePanelAdapter.setDescriptionInfoList(descriptionInfoList);

        Calendar calendar = Calendar.getInstance();

        scrollablePanelAdapter.setMatchList(matchInfoList);
    }

}
