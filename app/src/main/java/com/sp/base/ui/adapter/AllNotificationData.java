package com.sp.base.ui.adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllNotificationData {
    @SerializedName("firstName")
    @Expose
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
