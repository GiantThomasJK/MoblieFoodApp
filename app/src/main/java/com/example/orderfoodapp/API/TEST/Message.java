package com.example.orderfoodapp.API.TEST;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("success")
    String success;

    public Message(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
