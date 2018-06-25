package com.back4app.quickstartexampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.MyViewHolder> {
    private List<TransportClass> transClassList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, arrStation, note;
        public TextView depStation, depDate, arrDate, price, reservNr;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            depStation = (TextView) view.findViewById(R.id.depStation);
            arrStation = (TextView) view.findViewById(R.id.arrStation);
            depDate = (TextView) view.findViewById(R.id.depDate);
            arrDate = (TextView) view.findViewById(R.id.arrDate);
            note = (TextView) view.findViewById(R.id.note);
            reservNr = (TextView) view.findViewById(R.id.reservNr);
            price = (TextView) view.findViewById(R.id.price);
        }
    }


    public TransAdapter(List<TransportClass> transClassList) {
        this.transClassList = transClassList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_list_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TransportClass transClass = transClassList.get(position);
        holder.name.setText(transClass.getName());
        holder.depStation.setText(transClass.getDepStation());
        holder.arrStation.setText(transClass.getArrStation());
        holder.note.setText(transClass.getNote());
        holder.arrDate.setText(transClass.getArrDate());
        holder.depDate.setText(transClass.getDepDate());
        holder.price.setText(transClass.getPrice());
        holder.reservNr.setText(transClass.getReservNr());

    }

    @Override
    public int getItemCount() {
        return transClassList.size();
    }
}
