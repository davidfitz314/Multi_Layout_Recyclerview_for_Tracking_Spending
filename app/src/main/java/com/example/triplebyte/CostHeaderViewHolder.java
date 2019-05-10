package com.example.triplebyte;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CostHeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView headerView;

    public CostHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        headerView = itemView.findViewById(R.id.costHeaderView);
    }

    public TextView getHeaderView() {
        return headerView;
    }
}
