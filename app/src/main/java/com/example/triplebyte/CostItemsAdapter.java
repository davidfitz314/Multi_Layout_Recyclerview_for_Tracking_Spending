package com.example.triplebyte;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CostItemsAdapter extends RecyclerView.Adapter {
    private static final String TAG = CostItemsAdapter.class.getSimpleName();

    private List<Object> costItemsList;
    private LayoutInflater inflater;
    private static final int costDetailsInt = 0, costHeaderInt = 1;

    public CostItemsAdapter(Context context, List<Object> costItems){
        inflater = LayoutInflater.from(context);
        costItemsList = costItems;
    }

    @Override
    public int getItemViewType(int position) {
        if (this.costItemsList.get(position) instanceof CostDetails){
            return costDetailsInt;
        } else if (this.costItemsList.get(position) instanceof String){
            return costHeaderInt;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        if (i == costDetailsInt){

                itemView = inflater.inflate(R.layout.cost_detail_layout, viewGroup, false);
                return new CostDetailsHolder(itemView);
        } else if (i == costHeaderInt){
                itemView = inflater.inflate(R.layout.cost_headers, viewGroup, false);
                return new CostHeaderViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()){
            case costDetailsInt:
                CostDetailsHolder detailsHolder = (CostDetailsHolder) viewHolder;
                CostDetails itemDetails = (CostDetails) this.costItemsList.get(i);
                TextView summaryView = detailsHolder.getSummaryTextView();
                TextView dateView = detailsHolder.getDateTextView();
                TextView costView = detailsHolder.getCostTextView();

                summaryView.setText(itemDetails.getSummary());
                dateView.setText(itemDetails.getDate());
                costView.setText(itemDetails.getCost());
                break;
            case costHeaderInt:
                CostHeaderViewHolder headerViewHolder = (CostHeaderViewHolder) viewHolder;
                String header = (String) this.costItemsList.get(i);
                TextView headerView = headerViewHolder.getHeaderView();
                headerView.setText(header);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (this.costItemsList != null)
            return this.costItemsList.size();
        return 0;
    }
}
