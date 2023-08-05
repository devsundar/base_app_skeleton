package com.sp.base.ui.model;

import androidx.paging.PagedList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DummyResponse {

    @SerializedName("data")
    @Expose
    public ArrayList<ListData> results;


    public ArrayList<ListData> getResults() {
        return results;
    }

    public void setResults(ArrayList<ListData> results) {
        this.results = results;
    }
}
