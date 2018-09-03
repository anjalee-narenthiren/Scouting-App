package com.example.android.scouting;

import android.util.Log;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class FilterMatchList {
    private String teamFilter, tournamentFilter; //Filters
    private ArrayList<MatchInfo> baseMatchInfoList = new ArrayList<MatchInfo>();
    private static final int TEAM_SEARCH = 0;
    private static final int TOURN_SEARCH = 1;
    private static final int TEAM_TOURN_SEARCH = 2;

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
            int searchType = TEAM_TOURN_SEARCH;
            int teamFilterInt;
            if (teamFilter == null) {
                teamFilterInt = 0;
                searchType = TOURN_SEARCH;
            }
            else {
                try {
                    teamFilterInt = Integer.parseInt(teamFilter);
                }
                catch(NumberFormatException nfe) {
                    teamFilterInt = 0;
                    searchType = TOURN_SEARCH;
                }
            }

            if (tournamentFilter == null || tournamentFilter.isEmpty()) {
                tournamentFilter = " ";
                searchType = TEAM_SEARCH;
            }

            for (MatchInfo matchInfo : baseMatchInfoList) {
                Log.v("FilterMatchList", "SearchType:"+searchType);
                switch(searchType)
                {
                    case TEAM_SEARCH:
                        if (teamFilterInt == matchInfo.team)
                            filteredMatchInfoList.add(matchInfo);
                        break;
                    case TOURN_SEARCH:
                        if (tournamentFilter.equals(matchInfo.tournament))
                            filteredMatchInfoList.add(matchInfo);
                        break;
                    case TEAM_TOURN_SEARCH:
                        if (teamFilterInt == matchInfo.team && tournamentFilter.equals(matchInfo.tournament))
                            filteredMatchInfoList.add(matchInfo);
                        break;
                }
            }
            return filteredMatchInfoList;
        }
    }
}


