package com.example.android.scouting;

import android.util.Log;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class FilterMatchList {
    private String teamFilter, tournamentFilter; //Filters
    private ArrayList<MatchInfo> baseMatchInfoList = new ArrayList<MatchInfo>();

    public FilterMatchList(String teamFilterIn, String tournamentFilterIn, ArrayList<MatchInfo> baseMatchInfoListIn) {
        teamFilter = teamFilterIn;//teamFilterIn.trim();
        tournamentFilter = tournamentFilterIn;//tournamentFilterIn.trim();
        baseMatchInfoList = baseMatchInfoListIn;
    }

    public ArrayList<MatchInfo> filter() {
        ArrayList<MatchInfo> filteredMatchInfoList = new ArrayList<MatchInfo>();
        Log.v("FilterMatchList", "Enter filter func");

        if (teamFilter == null && tournamentFilter == null) {
            Log.v("FilterMatchList", "Filters are Empty - Both Null");
            return baseMatchInfoList;
        }

        if (teamFilter.isEmpty() && tournamentFilter.isEmpty()) {
            Log.v("FilterMatchList", "Filters are Empty");
            return baseMatchInfoList;
        } else {
            Log.v("FilterMatchList", "Filters are NOT Empty");

            int teamFilterInt;
            if (teamFilter == null) {
                teamFilterInt = 0;
            }
            else {
                try {
                    teamFilterInt = Integer.parseInt(teamFilter);
                }
                catch(NumberFormatException nfe) {
                    teamFilterInt = 0;
                }
            }

            if (tournamentFilter == null)
                tournamentFilter = " ";

            for (MatchInfo matchInfo : baseMatchInfoList) {
                if (teamFilterInt == matchInfo.team || tournamentFilter.equals(matchInfo.tournament))
                    filteredMatchInfoList.add(matchInfo);
            }
            return filteredMatchInfoList;
        }
    }
}


