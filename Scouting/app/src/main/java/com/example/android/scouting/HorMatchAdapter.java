package com.example.android.scouting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HorMatchAdapter extends ArrayAdapter<MatchInfo> {

    public HorMatchAdapter(Context context, ArrayList<MatchInfo> arrayList)
    {
        super(context, R.layout.match_column, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View returnView = convertView;
        if (returnView == null)
            returnView = LayoutInflater.from(getContext()).inflate(R.layout.match_column, parent, false);

        MatchInfo curMatch = getItem(position);

        TextView match = (TextView)returnView.findViewById(R.id.col_match_tv);
        match.setText(curMatch.match);

        TextView notes = (TextView)returnView.findViewById(R.id.col_notes_tv);
        notes.setText(curMatch.otherNotes);

        return returnView;
    }
}
