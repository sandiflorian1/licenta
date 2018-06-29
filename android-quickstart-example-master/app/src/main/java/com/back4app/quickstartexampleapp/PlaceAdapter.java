package com.back4app.quickstartexampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {
    private List<PlaceClass> placeClassList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.namePlace);

        }
    }


    public PlaceAdapter(List<PlaceClass> placeClassList) {
        this.placeClassList = placeClassList;
    }

    @Override
    public PlaceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_list_row, parent, false);

        return new PlaceAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceAdapter.MyViewHolder holder, int position) {
        PlaceClass placeClass = placeClassList.get(position);
        holder.name.setText(placeClass.getName());
    }

    @Override
    public int getItemCount() {
        return placeClassList.size();
    }
}
