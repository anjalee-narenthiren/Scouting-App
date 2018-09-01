package com.kelin.scrollablepanel.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kelin.scrollablepanel.library.PanelAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kelin on 16-11-18.
 */

public class ScrollablePanelAdapter extends PanelAdapter {
    private static final int TITLE_TYPE = 0;
    private static final int DESCRIPTION_TYPE = 1;
    private static final int MATCH_TYPE = 2;

    private List<String> descriptionInfoList=new ArrayList<>();
    private List<DateInfo> dateInfoList = new ArrayList<>();

    private List<MatchInfo> matchList = new ArrayList<>(10);
    //private List<List<MatchInfo>> matchList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return descriptionInfoList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return matchList.size() + 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case DESCRIPTION_TYPE:
                setDescriptionView(row, (DescriptionViewHolder) holder);
                break;
            case MATCH_TYPE:
                setMatchView(row, column, (MatchViewHolder) holder);
                break;
            case TITLE_TYPE:
                break;
            default:
                setMatchView(row, column, (MatchViewHolder) holder);
        }
    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return DESCRIPTION_TYPE;
        }
        return MATCH_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DESCRIPTION_TYPE:
                return new DescriptionViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_description_info, parent, false));
            case MATCH_TYPE:
                return new MatchViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_match_info, parent, false));
            case TITLE_TYPE:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_title, parent, false));
            default:
                break;
        }
        return new MatchViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_match_info, parent, false));
    }

    private void setDescriptionView(int pos, DescriptionViewHolder viewHolder) {
        String descriptionInfo = descriptionInfoList.get(pos);
        if (descriptionInfo != null && pos > 0) {
            viewHolder.descriptionTypeTextView.setText(descriptionInfo);
        }
    }

    private void setMatchView(final int row, final int column, MatchViewHolder viewHolder) {
        final MatchInfo matchInfo = matchList.get(column - 1);
        String val;
        if (matchInfo != null && column >= 0) {

            switch(row)
            {
                case 0:
                    val = String.valueOf(matchInfo.tournament);
                    break;
                case 1:
                    val = String.valueOf(matchInfo.match);
                    break;
                case 2:
                    val = String.valueOf(matchInfo.team);
                    break;
                case 3:
                    val = String.valueOf(matchInfo.haveAuto);
                    break;
                case 4:
                    val = matchInfo.otherNotes;
                    break;
                default:
                    val = matchInfo.tournament;

            }
            viewHolder.matchTextView.setText(val);
        }
    }



    private static class DescriptionViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionTypeTextView;
        public TextView descriptionNameTextView;

        public DescriptionViewHolder(View view) {
            super(view);
            this.descriptionTypeTextView = (TextView) view.findViewById(R.id.description_type);
            this.descriptionNameTextView = (TextView) view.findViewById(R.id.description_name);
        }
    }

    private static class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView matchTextView;
        public View view;

        public MatchViewHolder(View view) {
            super(view);
            this.view = view;
            this.matchTextView = (TextView) view.findViewById(R.id.match_tv);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }

    public void setDescriptionInfoList(List<String> descriptionInfoList) {
        this.descriptionInfoList = descriptionInfoList;
    }

    public void setDateInfoList(List<DateInfo> dateInfoList) {
        this.dateInfoList = dateInfoList;
    }

    public void setMatchList(List<MatchInfo> matchList) {
        this.matchList = matchList;
    }
}
