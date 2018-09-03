package com.example.android.scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kelin.scrollablepanel.library.ScrollablePanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.android.scouting.MenuActivity.matchInfoList;
import static com.example.android.scouting.MenuActivity.matchInfoFilteredList;
import static com.example.android.scouting.SettingsActivity.teamFilter;
import static com.example.android.scouting.SettingsActivity.tournamentFilter;

public class ViewDataActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

        sMatchRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Toast.makeText(ViewDataActivity.this, "Refresh "+getMatchKey(), Toast.LENGTH_SHORT).show();
                                                Log.v("ViewDataActivity", "Refresh "+getMatchKey());
                                                boolean appendMatch;
                                                if (getMatchKey().equals("empty")||matchInfoList.isEmpty())
                                                    appendMatch = true;
                                                else
                                                    appendMatch = false;

                                                for (DataSnapshot matchSnapshot : dataSnapshot.getChildren()){
                                                    if (appendMatch) {
                                                        MatchInfo matchObject = matchSnapshot.getValue(MatchInfo.class);
                                                        saveDataLocal(matchObject);
                                                        updateDataView();
                                                        setMatchKey(matchSnapshot.getKey()); //Store last grabbed key
                                                    }
                                                    else if (matchSnapshot.getKey() == getMatchKey())
                                                        appendMatch = true;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Toast.makeText(ViewDataActivity.this, "Failed to fetch new data", Toast.LENGTH_SHORT).show();
                                            }
                                        });
        updateDataView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    //Implement onSharedPrefenceChangeListener
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("team_filter") || key.equals("tournament_filter")) {
            Log.v("ViewDataActivity", "Preferences Changed - Update Data");
            teamFilter = sharedPreferences.getString("team_filter", "").trim();
            tournamentFilter = sharedPreferences.getString("tournament_filter", "").trim();
            Log.v("ViewDataActivity", "Preferences Changed- teamFilter: "+teamFilter+" tournamentFilter: "+tournamentFilter);
            updateDataView();
        }
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

        FilterMatchList filteredMatchList = new FilterMatchList(teamFilter, tournamentFilter, matchInfoList);
        //boolean rand = teamFilter.isEmpty();
        matchInfoFilteredList = filteredMatchList.filter();
        scrollablePanelAdapter.setMatchList(matchInfoFilteredList);
    }

    private void updateDataView(){
        setContentView(R.layout.activity_view_data);
        
        final ScrollablePanel scrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
        //final TestPanelAdapter scrollablePanelAdapter = new TestPanelAdapter();
        final ScrollablePanelAdapter scrollablePanelAdapter = new ScrollablePanelAdapter();
        setData(scrollablePanelAdapter);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
    }

}
