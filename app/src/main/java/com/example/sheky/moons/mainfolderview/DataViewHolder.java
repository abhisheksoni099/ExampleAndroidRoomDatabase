package com.example.sheky.moons.mainfolderview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sheky.moons.R;

public class DataViewHolder extends RecyclerView.ViewHolder {
    private TextView dataTextView;

    public DataViewHolder(View itemView) {
        super(itemView);
        dataTextView = itemView.findViewById(R.id.dataTextView);
    }

    public void bindData(String data) {
        dataTextView.setText(data);
    }
}
