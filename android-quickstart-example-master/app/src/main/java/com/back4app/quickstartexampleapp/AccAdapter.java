package com.back4app.quickstartexampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AccAdapter extends RecyclerView.Adapter<AccAdapter.MyViewHolder> {
    private List<AccommodationClass> accClassList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, note;
        public TextView checkin, checkout, price, reservNr;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            checkin = (TextView) view.findViewById(R.id.checkin);
            checkout = (TextView) view.findViewById(R.id.checkout);
            note = (TextView) view.findViewById(R.id.note);
            reservNr = (TextView) view.findViewById(R.id.reservNr);
            price = (TextView) view.findViewById(R.id.price);
        }
    }


    public AccAdapter(List<AccommodationClass> accClassList) {
        this.accClassList = accClassList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acc_list_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AccommodationClass accClass = accClassList.get(position);
        holder.name.setText(accClass.getName());
        holder.address.setText(accClass.getAddress());
        holder.note.setText(accClass.getNote());
        holder.checkin.setText(accClass.getCheckin());
        holder.checkout.setText(accClass.getCheckout());
        holder.reservNr.setText(accClass.getReservNr());
        holder.price.setText(accClass.getPrice());

    }

    @Override
    public int getItemCount() {
        return accClassList.size();
    }
}
