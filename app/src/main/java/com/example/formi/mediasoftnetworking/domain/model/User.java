package com.example.formi.mediasoftnetworking.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    @SerializedName("response")
    @Expose
    private List<Response> user = new ArrayList<>();

    public List<Response> getUser() {
        return user;
    }

    public void setUser(List<Response> user) {
        this.user = user;
    }
}
