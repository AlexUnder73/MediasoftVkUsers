package com.example.formi.mediasoftnetworking.data.net.callback;

import com.example.formi.mediasoftnetworking.domain.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkApiIdSearch {
    @GET("/method/users.get")
    Call<User> getUser(@Query("user_ids") String usrId, @Query("v") String version, @Query("access_token") String access_token, @Query("fields") String fields);
}
