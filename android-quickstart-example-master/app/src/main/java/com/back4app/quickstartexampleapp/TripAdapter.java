package com.back4app.quickstartexampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {
    private List<TripClass> tripClassList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, endDateTrip, year;
        public TextView startDateTrip;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            startDateTrip = (TextView) view.findViewById(R.id.startDateTrip);
            endDateTrip = (TextView) view.findViewById(R.id.endDateTrip);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public TripAdapter(List<TripClass> tripClassList) {
        this.tripClassList = tripClassList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_list_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TripClass tripClass = tripClassList.get(position);
        holder.title.setText(tripClass.getTitle());
        holder.startDateTrip.setText(tripClass.getStartDateTrip());
        holder.endDateTrip.setText(tripClass.getEndDateTrip());
        holder.year.setText(tripClass.getYear());
    }

    @Override
    public int getItemCount() {
        return tripClassList.size();
    }
}
