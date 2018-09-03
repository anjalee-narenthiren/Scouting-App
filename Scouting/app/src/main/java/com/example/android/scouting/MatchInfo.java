package com.example.android.scouting;

public class MatchInfo {
    public int match, team;
    public boolean haveAuto;
    public String tournament, otherNotes;

    public String userName;

    public String deviceIp;

    public MatchInfo() {

    }

    public MatchInfo(String tournamentIn, int matchIn, int teamIn, boolean haveAutoIn, String otherNotesIn, String nameIn, String deviceIpIn) {
        tournament = tournamentIn;
        match=matchIn;
        team=teamIn;
        haveAuto=haveAutoIn;
        otherNotes=otherNotesIn;

        userName = nameIn;

        deviceIp = deviceIpIn;
    }
}
