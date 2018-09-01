package com.example.android.testpanelview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kelin.scrollablepanel.library.ScrollablePanel;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ScrollablePanel scrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
        final TestPanelAdapter scrollablePanelAdapter = new TestPanelAdapter();
        //generateTestData(scrollablePanelAdapter);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
    }
}
