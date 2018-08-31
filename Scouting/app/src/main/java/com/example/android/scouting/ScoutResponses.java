package com.example.android.scouting;

public class ScoutResponses {
    public int tournament, match, team;
    public boolean haveAuto;
    public String otherNotes;

    ScoutResponses(int tournamentIn, int matchIn, int teamIn, boolean haveAutoIn, String otherNotesIn)
    {
        tournament = tournamentIn;
        match=matchIn;
        team=teamIn;
        haveAuto=haveAutoIn;
        otherNotes=otherNotesIn;
    }
}
