package com.example.triplebyte;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CostDetailsHolder extends RecyclerView.ViewHolder {
    private TextView summaryTextView;
    private TextView dateTextView;
    private TextView costTextView;

    public CostDetailsHolder(@NonNull View itemView) {
        super(itemView);
        summaryTextView = itemView.findViewById(R.id.summaryView);
        dateTextView = itemView.findViewById(R.id.dateView);
        costTextView = itemView.findViewById(R.id.costView);
    }

    public TextView getCostTextView() {
        return costTextView;
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public TextView getSummaryTextView() {
        return summaryTextView;
    }
}
