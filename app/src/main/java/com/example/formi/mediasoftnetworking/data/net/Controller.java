package com.example.formi.mediasoftnetworking.data.net;

import com.example.formi.mediasoftnetworking.data.net.callback.VkApiIdSearch;
import com.example.formi.mediasoftnetworking.other.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    public static VkApiIdSearch getApi(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.VkApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        VkApiIdSearch vkApi = retrofit.create(VkApiIdSearch.class);

        return vkApi;
    }
}
