package com.example.triplebyte;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CostDetails {
    private String summary;
    private String date;
    private String cost;
    private String category;

    public CostDetails(String summaryIn, String dateIn, String costIn, String categoryIn){
        String newDate = dateIn;

        Date addedDate = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm/DD/yyyy");
            addedDate = dateFormat.parse(newDate);
            this.date = dateFormat.format(addedDate);
        } catch (Exception e){
            e.printStackTrace();
            this.date = addedDate.toString();
        }
        
        this.summary = summaryIn;

        this.cost = costIn;
        this.category = categoryIn;
    }

    public String getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }

    public String getCategory() {
        return category;
    }
}
