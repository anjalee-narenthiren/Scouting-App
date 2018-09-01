package com.kelin.scrollablepanel.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kelin.scrollablepanel.library.ScrollablePanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final SimpleDateFormat MONTH_DAY_FORMAT = new SimpleDateFormat("MM-dd");
    public static final SimpleDateFormat WEEK_FORMAT = new SimpleDateFormat("EEE", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ScrollablePanel scrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
        //final TestPanelAdapter scrollablePanelAdapter = new TestPanelAdapter();
        final ScrollablePanelAdapter scrollablePanelAdapter = new ScrollablePanelAdapter();
        generateTestData(scrollablePanelAdapter);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
    }

    private void generateTestData(ScrollablePanelAdapter scrollablePanelAdapter) {
        List<String> descriptionInfoList = new ArrayList();
        descriptionInfoList.add("team");
        descriptionInfoList.add("match");
        descriptionInfoList.add("a");
        descriptionInfoList.add("b");
        descriptionInfoList.add("auto");
        descriptionInfoList.add("notes");
        scrollablePanelAdapter.setDescriptionInfoList(descriptionInfoList);

        List<DateInfo> dateInfoList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 14; i++) {
            DateInfo dateInfo = new DateInfo();
            String date = MONTH_DAY_FORMAT.format(calendar.getTime());
            String week = WEEK_FORMAT.format(calendar.getTime());
            dateInfo.setDate(date);
            dateInfo.setWeek(week);
            dateInfoList.add(dateInfo);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        scrollablePanelAdapter.setDateInfoList(dateInfoList);

        List<MatchInfo> matchInfoList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            String date = MONTH_DAY_FORMAT.format(calendar.getTime());
            matchInfoList.add(new MatchInfo(date, i, 2, true, "notes"));
            Log.v("MatchInfo", "Add to matchInfoList match:" + matchInfoList.get(i).otherNotes);
        }
        scrollablePanelAdapter.setMatchList(matchInfoList);
    }


}
