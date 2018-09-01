package com.kelin.scrollablepanel.sample;

public class MatchInfo {
    public int match, team;
    public boolean haveAuto;
    public String tournament, otherNotes;

    public MatchInfo(String tournamentIn, int matchIn, int teamIn, boolean haveAutoIn, String otherNotesIn)
    {
        tournament = tournamentIn;
        match=matchIn;
        team=teamIn;
        haveAuto=haveAutoIn;
        otherNotes=otherNotesIn;
    }
}
