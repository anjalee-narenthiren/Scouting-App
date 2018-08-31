package com.example.android.scouting;

public class ScoutResponse {
    public int match, team;
    public boolean haveAuto;
    public String tournament, otherNotes;

    ScoutResponse(String tournamentIn, int matchIn, int teamIn, boolean haveAutoIn, String otherNotesIn)
    {
        tournament = tournamentIn;
        match=matchIn;
        team=teamIn;
        haveAuto=haveAutoIn;
        otherNotes=otherNotesIn;
    }
}
