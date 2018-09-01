package com.example.android.scouting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private void setData(ScrollablePanelAdapter scrollablePanelAdapter) {
        List<String> descriptionInfoList = new ArrayList();
        descriptionInfoList.add("Tournament");
        descriptionInfoList.add("Match");
        descriptionInfoList.add("Team");
        descriptionInfoList.add("Auto");
        descriptionInfoList.add("Notes");
        scrollablePanelAdapter.setDescriptionInfoList(descriptionInfoList);

        Calendar calendar = Calendar.getInstance();
        /*List<MatchInfo> matchInfoList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            String date = MONTH_DAY_FORMAT.format(calendar.getTime());
            matchInfoList.add(new MatchInfo(date, i, 2, true, "notes"));
            Log.v("MatchInfo", "Add to matchInfoList match:" + matchInfoList.get(i).otherNotes);
        }*/
        scrollablePanelAdapter.setMatchList(matchInfoList);
    }

}
